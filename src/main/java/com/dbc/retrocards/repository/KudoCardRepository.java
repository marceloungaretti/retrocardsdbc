package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.KudoCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KudoCardRepository extends JpaRepository<KudoCardEntity, Integer> {
}
