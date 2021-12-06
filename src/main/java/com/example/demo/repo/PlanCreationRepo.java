package com.example.demo.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PlanRegisterEntity;

public interface PlanCreationRepo extends JpaRepository<PlanRegisterEntity, Serializable> {

}
