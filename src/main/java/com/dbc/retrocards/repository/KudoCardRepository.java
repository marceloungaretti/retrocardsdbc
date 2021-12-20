package com.dbc.retrocards.repository;


import com.dbc.retrocards.entity.KudoCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KudoCardRepository extends JpaRepository<KudoCardEntity, Integer> {

    @Query(value ="select * from kudocard k order by  data_criacao asc",nativeQuery = true)
    List<KudoCardEntity> listarKudoCardPorDataAsc ();

    @Query(value = "SELECT * from kudocard WHERE id_kudobox = :id", nativeQuery = true)
    List<KudoCardEntity> findByIdBox(Integer id);

    @Query(value = "select id_kudocard,de,para,data_criacao,descricao,k.titulo,id_criador,s.id_sprint,k2.status,k2.id_kudobox " +
            "from kudocard k " +
            "JOIN kudobox k2 ON (k.id_kudobox = k2.id_kudobox) " +
            "join sprint s ON(k2.id_sprint = s.id_sprint) where status = '2' order by s.id_sprint ", nativeQuery = true)
    List<KudoCardEntity> findBySprint();

    @Query(value = "select id_kudocard,de,para,data_criacao,descricao,k.titulo,id_criador,s.id_sprint,k2.status,k2.id_kudobox " +
            " from kudocard k " +
            " JOIN kudobox k2 ON (k.id_kudobox = k2.id_kudobox) " +
            " join sprint s ON(k2.id_sprint = s.id_sprint) where s.id_sprint = 1 and k2.status = '2' order by k.data_criacao desc ", nativeQuery = true)
    List<KudoCardEntity> findByIdSprint(Integer id);
}

