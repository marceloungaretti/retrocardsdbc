package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.SprintEntity;
import com.dbc.retrocards.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha);
    Optional<UsuarioEntity> findByLogin(String login);

    @Query(value = "select * from usuario u join usuario_grupo ug on (u.id_usuario = ug.id_usuario)  where u.id_usuario = :id",nativeQuery = true)
    List<UsuarioEntity> listarUsuariosPorGrupo (Integer id);
}


