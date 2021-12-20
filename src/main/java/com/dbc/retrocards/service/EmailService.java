package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.EmailCreateDTO;
import com.dbc.retrocards.dto.EmailDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.EmailEntity;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.EmailRepository;
import com.dbc.retrocards.repository.RetrospectivaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Service
@RequiredArgsConstructor
public class EmailService {
    private final ObjectMapper objectMapper;
    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;
    private final RetrospectivaRepository retrospectivaRepository;

    public EmailDTO create(EmailCreateDTO emailCreateDTO, Integer idRetrospectiva) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        EmailEntity entity = objectMapper.convertValue(emailCreateDTO, EmailEntity.class);
        EmailEntity emailCriar = emailRepository.save(entity);
        EmailDTO emailDTO = objectMapper.convertValue(emailCriar, EmailDTO.class);
        Optional<RetrospectivaEntity> retrospectivaEntity = retrospectivaRepository.findById(idRetrospectiva);
        RetrospectivaDTO retrospectivaDTO = objectMapper.convertValue(retrospectivaEntity, RetrospectivaDTO.class);
        emailDTO.setRetrospectivaDTO(retrospectivaDTO);
        enviarEmailComTemplate(emailDTO);
        return emailDTO;
    }

    public EmailDTO enviarEmailComTemplate(EmailDTO emailDTO) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(emailDTO.getEmailDestinatario().split(","));
        helper.setSubject(emailDTO.getAssunto());
        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("id", emailDTO.getRetrospectivaDTO().getIdRetrospectiva());
        dados.put("titulo", emailDTO.getRetrospectivaDTO().getTituloRetrospectiva());
        dados.put("email", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        helper.setText(html, true);
        emailSender.send(mimeMessage);
        return emailDTO;
    }

}
