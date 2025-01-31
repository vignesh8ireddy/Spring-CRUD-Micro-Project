package com.vignesh.service;

import java.util.List;
import java.util.Map;

import com.vignesh.entity.TravelPlan;

public interface ITravelPlanManagementService {

	public String registerTravelPlan(TravelPlan plan);
	public Map<Integer, String> getTravelPlanCategories();
	public List<TravelPlan> getAllTravelPlans();
	public TravelPlan getTravelPlanById(Integer planId);
	public String updateTravelPlan(TravelPlan plan);
	public String deleteTravelPlan(Integer planId);
	public String changeTravelPlanStatus(Integer planId, String status);
	
}
