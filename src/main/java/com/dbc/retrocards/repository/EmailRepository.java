package com.dbc.retrocards.repository;

//FIXME Limpar
import com.dbc.retrocards.entity.EmailEntity;
import com.dbc.retrocards.entity.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {
}
