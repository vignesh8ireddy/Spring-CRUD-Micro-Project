package com.vignesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vignesh.entity.TravelPlan;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan, Integer> {

}
