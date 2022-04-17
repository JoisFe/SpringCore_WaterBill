package com.nhnacademy.edu.springframework.project2.repository;

import java.util.List;
import java.util.Map;

public interface ChargeRepository {
    List<Tariff> getFindedTariffs();

    List<Tariff> getTariffs();

    Map<Integer, Long> getBillTotal();

    boolean isLoaded();

    void load(String fileLocation);

    void findUnitPriceByWaterUsage(int waterUsage);
}
