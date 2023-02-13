package com.mx.escuela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.escuela.entity.MatterEntity;

@Repository
public interface MatterRepository extends JpaRepository<MatterEntity, Integer> {

}
