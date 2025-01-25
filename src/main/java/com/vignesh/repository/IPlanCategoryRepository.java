package com.vignesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vignesh.entity.PlanCatergory;

public interface IPlanCategoryRepository extends JpaRepository<PlanCatergory, Integer> {

}
