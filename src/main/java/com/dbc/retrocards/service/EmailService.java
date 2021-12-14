package com.dbc.retrocards.service;

import com.dbc.retrocards.dto.EmailCreateDTO;
import com.dbc.retrocards.dto.EmailDTO;
import com.dbc.retrocards.dto.RetrospectivaCreateDTO;
import com.dbc.retrocards.dto.RetrospectivaDTO;
import com.dbc.retrocards.entity.EmailEntity;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import com.dbc.retrocards.entity.StatusRetrospectivaEntity;
import com.dbc.retrocards.exceptions.RegraDeNegocioException;
import com.dbc.retrocards.repository.EmailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final ObjectMapper objectMapper;
    private final EmailRepository emailRepository;

    public EmailDTO create(EmailCreateDTO emailCreateDTO) throws RegraDeNegocioException {
        EmailEntity entity = objectMapper.convertValue(emailCreateDTO, EmailEntity.class);
        String string = emailCreateDTO.getEmailDestinatario() + ", ";
        entity.setEmailDestinatario(string);
        EmailEntity emailCriar = emailRepository.save(entity);
        EmailDTO emailDTO = objectMapper.convertValue(emailCriar, EmailDTO.class);
        return emailDTO;
    }
}
