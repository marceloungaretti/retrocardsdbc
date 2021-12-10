package com.dbc.retrocards.repository;

import com.dbc.retrocards.entity.KudoBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KudoBoxRepository extends JpaRepository<KudoBoxEntity, Integer> {
}
