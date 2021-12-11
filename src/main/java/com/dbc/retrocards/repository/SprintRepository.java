package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.SprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SprintRepository  extends JpaRepository<SprintEntity, Integer> {

    @Query(value = "select * from sprint s order by  data_conclusao asc",nativeQuery = true)
    List<SprintEntity> listarSprintPorDataConclusaoDesc ();
}
