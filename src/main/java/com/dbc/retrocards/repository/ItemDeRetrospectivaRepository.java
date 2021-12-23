package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDeRetrospectivaRepository extends JpaRepository<ItemDeRetrospectivaEntity, Integer> {
    //FIXME Evitar usar query nativa
    @Query(value = "SELECT * from item WHERE id_retrospectiva = :id", nativeQuery = true)
    List<ItemDeRetrospectivaEntity> findByIdRetro(Integer id);

}
