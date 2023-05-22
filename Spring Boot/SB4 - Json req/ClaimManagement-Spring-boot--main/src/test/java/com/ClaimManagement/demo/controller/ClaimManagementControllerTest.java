package com.ClaimManagement.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ClaimManagement.demo.model.ClaimDetailsEntity;
import com.ClaimManagement.demo.service.impl.ClaimServicesImpl;

@ExtendWith(MockitoExtension.class)
public class ClaimManagementControllerTest {

    @Mock
    private ClaimServicesImpl claimServicesImpl;
    
    @InjectMocks
    private ClaimManagementController claimManagementController;

    @Test
    public void getClaimDetails() throws Exception {
    	ClaimDetailsEntity claimDetailsEntity = new ClaimDetailsEntity("1","1",1000,LocalDate.parse("2022-02-09"), true,"B1",800,true,false);
        when(claimServicesImpl.getClaim("1")).thenReturn(claimDetailsEntity);

        ClaimDetailsEntity result = claimManagementController.getClaimDetails("1");
        assertEquals(claimDetailsEntity, result);
    }

    @Test
    public void addClaimDetails() throws Exception {
    	ClaimDetailsEntity claimDetailsEntity = new ClaimDetailsEntity("1", "1", 1000, LocalDate.parse("2022-02-09"),
				true, "B1", 800, true, false);

    	when(claimServicesImpl.createClaim(claimDetailsEntity)).thenReturn("Claim Registered");

        String result = claimManagementController.addClaimDetails(claimDetailsEntity);

        assertEquals("Claim Registered", result);

    }

    @Test
    public void updateClaim() throws Exception {
    	String claimId = "1";
        boolean withdraw = true;
        String expected = "Claim is withdrawn";

        when(claimServicesImpl.withdrawTheClaim(claimId, withdraw)).thenReturn(expected);

        String result = claimManagementController.updateClaim(claimId, withdraw);
        assertEquals(expected, result);
    }
}
