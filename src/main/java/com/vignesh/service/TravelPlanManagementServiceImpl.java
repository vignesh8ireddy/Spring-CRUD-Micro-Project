package com.vignesh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vignesh.config.AppConfigProperties;
import com.vignesh.constants.TravelPlanConstants;
import com.vignesh.entity.PlanCatergory;
import com.vignesh.entity.TravelPlan;
import com.vignesh.repository.IPlanCategoryRepository;
import com.vignesh.repository.ITravelPlanRepository;

@Controller
public class TravelPlanManagementServiceImpl implements ITravelPlanManagementService {

	@Autowired
	private ITravelPlanRepository travelPlanRepository;
	@Autowired
	private IPlanCategoryRepository planCategoryRepository;
	private Map<String,String> messages;
	
	@Autowired
	public TravelPlanManagementServiceImpl(AppConfigProperties props) {
		messages = props.getMessages();
	}
	
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		TravelPlan savedPlan = travelPlanRepository.save(plan);
		/*
		 * if(savedPlan.getPlanId()!=null) { return
		 * "TravelPlan is registered with the id value : "+savedPlan.getPlanId(); } else
		 * { return "Problem in registering the TravelPlan. Register Failed!"; }
		 */
		return savedPlan.getPlanId()!=null?messages.get(TravelPlanConstants.REGISTER_SUCCESS)+savedPlan.getPlanId():messages.get(TravelPlanConstants.REGISTER_FAILURE);
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
		return travelPlanRepository.findById(planId).orElseThrow(()->new IllegalArgumentException(messages.get(TravelPlanConstants.FIND_BY_ID_FAILURE)));
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(plan.getPlanId());
		if(opObj.isPresent()) {
			TravelPlan updatedPlan = travelPlanRepository.save(plan);
			return messages.get(TravelPlanConstants.UPDATE_SUCCESS)+updatedPlan.getPlanId();
		}
		else {
			throw new IllegalArgumentException(messages.get(TravelPlanConstants.UPDATE_FAILURE)+plan.getPlanId());
		}
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(planId);
		if(opObj.isPresent()) {
			travelPlanRepository.deleteById(planId);
			return messages.get(TravelPlanConstants.DELETE_SUCCESS)+planId;
		}
		else {
			throw new IllegalArgumentException(messages.get(TravelPlanConstants.DELETE_FAILURE)+planId);
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opObj = travelPlanRepository.findById(planId);
		if(opObj.isPresent()) {
			TravelPlan plan = opObj.get();
			plan.setActiveSW(status);
			travelPlanRepository.save(plan);
			return messages.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS)+planId;
		}
		else {
			throw new IllegalArgumentException(messages.get(TravelPlanConstants.STATUS_CHANGE_FAILURE)+planId);
		}
	}
}
