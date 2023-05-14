package com.ashokit.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.Entity.Plan;
import com.ashokit.Service.PlanService;
import com.ashokit.prop.AppProperies;


@RestController
public class PlanRestController {
     
	@Autowired
	private PlanService planService;
	
	@Autowired
	private AppProperies appProp;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories(){                                                                   
		Map<Integer, String> planCategories = planService.getPlanCategories();
		
	  return new ResponseEntity<>(planCategories, HttpStatus.OK);	
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
	    
		String resp="";
		
		boolean savePlan = planService.savePlan(plan);
		
		Map<String, String> map = appProp.getMap();
		
		if(savePlan) {
			
			resp=map.get("plansaved");
			
		}
		else {
			resp=map.get("plannotsaved");
		}
		
		return new ResponseEntity<String>(resp, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/plans")
    public ResponseEntity<List<Plan>> viewPlans(){
    	   
		List<Plan> allPlans = planService.getAllPlans();
		
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }
	
	@GetMapping("/plan/{planid}")
	public ResponseEntity<Plan> editPlan(@PathVariable("planid") Integer planId){
		
		   Plan planById = planService.getPlanById(planId);
		   
		return new ResponseEntity<>(planById, HttpStatus.OK);   
     }
    
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
	     
		String resp="";
		
		boolean updatePlan = planService.updatePlan(plan);
		
		Map<String, String> map = appProp.getMap();
	    
		if(updatePlan) {
			resp=map.get("planupdated");
	       }
		else {
			resp=map.get("plannotupdated");
		}
		
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planid}")
	public ResponseEntity<String> deletePlan( @PathVariable("planid") Integer planId){
		
		String res="";
		boolean deletePlanById = planService.deletePlanById(planId);
		
		Map<String, String> map = appProp.getMap();
	    
		if(deletePlanById)
			res=map.get("plandeleted");
		else
			res=map.get("plannotdeleted");
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
	@PutMapping("/status-change/{planid}/{status}")
	public ResponseEntity<String> planStatus(@PathVariable("planid") Integer planId, @PathVariable("status") String status){
		String resp="";
		
		boolean planStatus = planService.planStatus(planId, status);
		
		Map<String, String> map = appProp.getMap();
	    
		if(planStatus)
			resp=map.get("planstatussaved");
		else
			resp=map.get("planstatusnotsaved");
		
		return new ResponseEntity<String>(resp, HttpStatus.OK);
		
		
	}
	
	
}
