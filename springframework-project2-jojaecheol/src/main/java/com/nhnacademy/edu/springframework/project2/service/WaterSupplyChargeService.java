package com.nhnacademy.edu.springframework.project2.service;

public interface WaterSupplyChargeService {
    void calculateCharge(int waterUsage);

    void loadAndFindUnitPrice(String fileLocation, int waterUsage);
}
