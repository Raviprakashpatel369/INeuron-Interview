package com.ClaimManagement.demo.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ClaimManagement.demo.model.ClaimDetailsEntity;
import com.ClaimManagement.demo.repository.ClaimDetailsRepository;

class ClaimServicesImplTest {

    @Mock
    private ClaimDetailsRepository claimDetailsRepository;

    @InjectMocks
    private ClaimServicesImpl claimServicesImpl;

    AutoCloseable autoClosable;
    ClaimDetailsEntity claimDetailsEntity;

    @BeforeEach
    public void setUp() throws Exception {
        autoClosable = MockitoAnnotations.openMocks(this);
        claimDetailsEntity = new ClaimDetailsEntity("1", "1", 1000, LocalDate.parse("2022-02-09"), true, "B1", 800, true, false);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoClosable.close();
    }

    @Test
    public void testCreateClaimWithValidDate() {
        claimDetailsEntity.setDate_of_accident(LocalDate.now().minusDays(1));

        when(claimDetailsRepository.save(claimDetailsEntity)).thenReturn(claimDetailsEntity);

        String result = claimServicesImpl.createClaim(claimDetailsEntity);
        assertEquals("Claim Registered", result);
    }

    @Test
    public void testCreateClaimWithInvalidDate() {
        claimDetailsEntity.setDate_of_accident(LocalDate.now().plusDays(1));

        String result = claimServicesImpl.createClaim(claimDetailsEntity);
        assertEquals("Invalid date of accident", result);
    }

    @Test
    public void testWithdrawClaim() {
        claimDetailsEntity.setClaim_status(true);
        claimDetailsEntity.setWithdraw_claim(false);

        when(claimDetailsRepository.findById("1")).thenReturn(Optional.of(claimDetailsEntity));
        when(claimDetailsRepository.save(claimDetailsEntity)).thenReturn(claimDetailsEntity);

        String result = claimServicesImpl.withdrawTheClaim("1", true);
        assertEquals("Claim is withdrawn", result);
        assertFalse(claimDetailsEntity.isWithdraw_claim());
        assertFalse(claimDetailsEntity.isClaim_status());
    }

    @Test
    public void testGetClaim() {
        claimDetailsEntity.setClaimId("1");
        when(claimDetailsRepository.findById("1")).thenReturn(Optional.of(claimDetailsEntity));

        ClaimDetailsEntity result = claimServicesImpl.getClaim("1");
        assertEquals(claimDetailsEntity, result);
    }

}
