package com.ashokit.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashokit.Entity.Plan;


public interface PlanService {

	//to display plancategories date in dropdown from database table and we are using map based
	//on id 
	public Map<Integer,String> getPlanCategories();
	  
	//to save a plan data after submit button
	public boolean savePlan(Plan plan);
	
	//for getting all after click on view plans
	public List<Plan> getAllPlans();
	
	//get a particular plan for edit plan
	public Plan getPlanById(Integer planId);
	
   //after updating plan to save a updated plan
	public boolean updatePlan(Plan plan);
	
	//deleting plan based on id (hard delete)
	public boolean deletePlanById(Integer planId);
	
	//for change the status of plan means activate or deactivate plan (soft delete)
	public boolean planStatus(Integer planId, String status);
	
}
