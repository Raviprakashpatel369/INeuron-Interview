package com.ClaimManagement.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ClaimManagement.demo.model.ClaimDetailsEntity;
import com.ClaimManagement.demo.service.impl.ClaimServicesImpl;

@RestController
@RequestMapping("/api/claims")
public class ClaimManagementController {
	
	ClaimServicesImpl claimServicesImpl;	
	
	public ClaimManagementController(ClaimServicesImpl claimServicesImpl) {
		this.claimServicesImpl = claimServicesImpl;
	}
	
	@Autowired
	public void setClaimServicesImpl(ClaimServicesImpl claimServicesImpl) {
		this.claimServicesImpl = claimServicesImpl;
	}



	//Just wrote this method to see the actions
	@GetMapping("get/{claimId}")
	public ClaimDetailsEntity getClaimDetails(@PathVariable("claimId") String claimId) {
		return claimServicesImpl.getClaim(claimId);
	}
	
	@PostMapping("addClaim")
	public String addClaimDetails(@RequestBody ClaimDetailsEntity claim) {
		
		return claimServicesImpl.createClaim(claim);
	}
	
	@PutMapping("{claimId}")
	public String updateClaim(@RequestBody @PathVariable("claimId") String claimId,@RequestParam boolean withdraw) {
		
		return claimServicesImpl.withdrawTheClaim(claimId, withdraw);
	}
}
