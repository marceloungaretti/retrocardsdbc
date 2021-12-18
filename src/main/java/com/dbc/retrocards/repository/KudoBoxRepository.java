package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.KudoBoxEntity;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KudoBoxRepository extends JpaRepository<KudoBoxEntity, Integer> {
    @Query(value = "SELECT * from kudobox WHERE id_sprint = :id order by data_leitura desc", nativeQuery = true)
    List<KudoBoxEntity> findBoxByIdSprint(Integer id);

    @Query(value = "SELECT * from kudobox WHERE status = '1' order by id_sprint asc , data_leitura", nativeQuery = true)
    List<KudoBoxEntity> findBoxAndamento();
}
