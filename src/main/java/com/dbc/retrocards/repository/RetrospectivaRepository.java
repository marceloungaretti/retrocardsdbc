package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import com.dbc.retrocards.entity.RetrospectivaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public  interface RetrospectivaRepository extends JpaRepository<RetrospectivaEntity, Integer> {
 @Query(value = "SELECT * from retrospectiva WHERE id_sprint = :id", nativeQuery = true)
 List<RetrospectivaEntity> findByIdSprint(Integer id);

}