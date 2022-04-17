package com.nhnacademy.edu.springframework.project2.service;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

public interface WaterSupplyChargeService {
    void calculateCharge(int waterUsage);

    void loadAndFindUnitPrice(String fileLocation, int waterUsage);
}
