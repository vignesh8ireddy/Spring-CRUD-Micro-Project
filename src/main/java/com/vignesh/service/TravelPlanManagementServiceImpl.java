package com.vignesh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vignesh.entity.PlanCatergory;
import com.vignesh.entity.TravelPlan;
import com.vignesh.repository.IPlanCategoryRepository;
import com.vignesh.repository.ITravelPlanRepository;

public class TravelPlanManagementServiceImpl implements ITravelPlanManagementService {

	@Autowired
	private ITravelPlanRepository travelPlanRepository;
	@Autowired
	private IPlanCategoryRepository planCategoryRepository;
	
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		TravelPlan savedPlan = travelPlanRepository.save(plan);
		/*
		 * if(savedPlan.getPlanId()!=null) { return
		 * "TravelPlan is registered with the id value : "+savedPlan.getPlanId(); } else
		 * { return "Problem in registering the TravelPlan. Register Failed!"; }
		 */
		return savedPlan.getPlanId()!=null?"TravelPlan is registered with the id value : "+savedPlan.getPlanId():"Problem in registering the TravelPlan. Register Faild!";
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		List<PlanCatergory> list = planCategoryRepository.findAll();
		Map<Integer, String> categoriesMap = new HashMap<Integer, String>();
		list.forEach(category -> {
			categoriesMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoriesMap;
	}

	@Override
	public List<TravelPlan> getAllTravelPlans() {
		return travelPlanRepository.findAll();
	}

	@Override
	public TravelPlan getTravelPlanById(Integer planId) {
		/*
		 * Optional<TravelPlan> opObj = travelPlanRepository.findById(planId);
		 * if(opObj.isPresent()) { return opObj.get(); } else { throw new
		 * IllegalArgumentException("Plan Id is not found!"); }
		 */
		return travelPlanRepository.findById(planId).orElseThrow(()->new IllegalArgumentException("Plan Id is not found!"));
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(plan.getPlanId());
		if(opObj.isPresent()) {
			TravelPlan updatedPlan = travelPlanRepository.save(plan);
			return "TravelPlan with id : "+updatedPlan.getPlanId()+" is update.";
		}
		else {
			throw new IllegalArgumentException("Update failed! No such Travel Plan is regiestered to update.");
		}
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(planId);
		if(opObj.isPresent()) {
			travelPlanRepository.deleteById(planId);
			return "TravelPlan with id : "+planId+" is deleted.";
		}
		else {
			throw new IllegalArgumentException("Update failed! No such Travel Plan is regiestered to delete.");
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planId, Character status) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(planId);
		if(opObj.isPresent()) {
			TravelPlan plan = opObj.get();
			plan.setActiveSW(status);
			return "Status of TravelPlan with id : "+planId+" is changed to "+status+".";
		}
		else {
			throw new IllegalArgumentException("Status change failed! No such Travel Plan is regiestered in the first place.");
		}
	}
}
