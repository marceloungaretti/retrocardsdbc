package com.dbc.retrocards.controller;
import com.dbc.retrocards.dto.EmailCreateDTO;
import com.dbc.retrocards.dto.EmailDTO;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.service.EmailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/email")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public EmailDTO create(@RequestBody EmailCreateDTO emailCreateDTO, Integer idRetrospectiva) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        EmailDTO emailDTO = emailService.create(emailCreateDTO, idRetrospectiva);
        return emailDTO;
    }

}
