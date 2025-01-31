package com.vignesh.microservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.entity.TravelPlan;
import com.vignesh.service.ITravelPlanManagementService;


@RestController
@RequestMapping("/travelplan/api")
public class TravelPlanOperationsController {
	
	@Autowired
	private ITravelPlanManagementService planService;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveTravelPlan(@RequestBody TravelPlan plan) {
		try {
			String msg = planService.registerTravelPlan(plan);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<?> showTravelPlanCategories() {
		
		try {
			Map<Integer, String> mapCategories = planService.getTravelPlanCategories();
			return new ResponseEntity<Map<Integer,String>>(mapCategories, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/all")	
	public ResponseEntity<?> showAllTravelPlans() {
		try {
			List<TravelPlan> list = planService.getAllTravelPlans();
			return new ResponseEntity<List<TravelPlan>>(list,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find/{planId}")
	public ResponseEntity<?> showTravelPlanById(@PathVariable Integer planId) {
		try {
			TravelPlan plan = planService.getTravelPlanById(planId);
			return new ResponseEntity<TravelPlan>(plan, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> changeTravelPlan(@RequestBody TravelPlan plan) {
		try {
			String msg = planService.updateTravelPlan(plan);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<?> removeTravelPlanByPlanId(@PathVariable Integer planId) {
		try {
			String msg = planService.deleteTravelPlan(planId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update-status/{planId}/{status}")
	public ResponseEntity<?> changeTravelPlanStatus(@PathVariable Integer planId,
													@PathVariable String status) {
		try {
			String msg = planService.changeTravelPlanStatus(planId, status);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
