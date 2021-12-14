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
}
