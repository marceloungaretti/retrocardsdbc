package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.ItemDeRetrospectivaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDeRetrospectivaRepository extends JpaRepository<ItemDeRetrospectivaEntity, Integer> {
}
