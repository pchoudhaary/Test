package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.Entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
