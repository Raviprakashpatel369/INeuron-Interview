package com.ClaimManagement.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ClaimManagement.demo.model.ClaimDetailsEntity;
import com.ClaimManagement.demo.repository.ClaimDetailsRepository;
import com.ClaimManagement.demo.service.ClaimServices;

@Service
public class ClaimServicesImpl implements ClaimServices {
	
	ClaimDetailsRepository claimDetailsRepository;
	
	
	public ClaimServicesImpl(ClaimDetailsRepository claimDetailsRepository) {
		super();
		this.claimDetailsRepository = claimDetailsRepository;
		
	}
	
	@Override
	public String createClaim(ClaimDetailsEntity claimDetailsEntity) {
		// TODO Auto-generated method stub
		LocalDate currentDate = LocalDate.now();
		String res;
		if (claimDetailsEntity.getDate_of_accident().isAfter(currentDate)) {
            res="Invalid date of accident";
        } else {
        	claimDetailsRepository.save(claimDetailsEntity);
            res="Claim Registered";
        }
		return res;
	}

	
	@Override
	public String withdrawTheClaim(String claimId,boolean withdraw) {
		// TODO Auto-generated method stub
		ClaimDetailsEntity claimEntityDetails = claimDetailsRepository.findById(claimId).get();
		String res;
		if(withdraw) {
			claimEntityDetails.setClaim_status(false);
			claimEntityDetails.setWithdraw_claim(false);
			res="Claim is withdrawn";
		}
		else {
			claimEntityDetails.setClaim_status(true);
			claimEntityDetails.setWithdraw_claim(true);
			res="Claim is Successfull and cant be withdrawn";
		}
		claimDetailsRepository.save(claimEntityDetails);
		return res;
	}

	@Override
	public ClaimDetailsEntity getClaim(String claimId) {
		// TODO Auto-generated method stub
		return claimDetailsRepository.findById(claimId).get();
	}

}
