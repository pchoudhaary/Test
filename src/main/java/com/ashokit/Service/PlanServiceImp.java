package com.ashokit.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.Entity.Plan;
import com.ashokit.Entity.PlanCategories;
import com.ashokit.repo.PlanCatagoriesRepo;
import com.ashokit.repo.PlanRepo;

@Service
public class PlanServiceImp implements PlanService {

	@Autowired
	private PlanCatagoriesRepo planCategoriesRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	
	@Override
	public Map<Integer, String> getPlanCategories() {
        
		   List<PlanCategories> categories = planCategoriesRepo.findAll();
		
		   Map<Integer,String> categoriesmap = new HashMap<>();
		   
		   categories.forEach(category -> {
			   categoriesmap.put(category.getCategoryId(), category.getCategoryName());
		   });
		   
		
		return categoriesmap;
	}

	@Override
	public boolean savePlan(Plan plan) {

		Plan save = planRepo.save(plan);
		
	    return save.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
      return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
	   
		//optional is class introduced in java 1.8 it is a container this class identify object may availble or may not be availble 
		//to avoid null pointer exception 
	    Optional<Plan> findById = planRepo.findById(planId);

	    if(findById.isPresent()) {
	    	return findById.get();
	    }
	    
	    return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
      planRepo.save(plan);
	   return plan.getPlanId()!=null ;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
         
		boolean status = false;
		
		try {
		planRepo.deleteById(planId);   //these method return type is void so this method may throw an exception 
		status=true;
		}
		catch(Exception e) {
		e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean planStatus(Integer planId, String status) {
              
		          Optional<Plan> findById = planRepo.findById(planId);
		    
		          
		          if(findById.isPresent()) {
		        	  Plan plan = findById.get();     //we want to update only on column if we create plan object then all column 
		        	  plan.setActiveSwitch(status);    //intialize with null value so we can do this to update only one column
		        	  planRepo.save(plan);
		        	  return true;
		          }
		
		    return false;
	}

}
