package com.nhnacademy.edu.springframework.project2.service;

import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicWaterSupplyChargeService implements WaterSupplyChargeService{
    ChargeRepository chargeRepository;

    @Autowired
    public BasicWaterSupplyChargeService(
        ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public void calculateCharge(int waterUsage) {
        chargeRepository.getBillTotal().clear();
        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();

        for (Tariff findedTariff : findedTariffs) {
            chargeRepository.getBillTotal().put(findedTariff.getId(), findedTariff.getUnitPrice() * waterUsage);
        }
    }

    @Override
    public void loadAndFindUnitPrice(String fileLocation, int waterUsage) {
        chargeRepository.load(fileLocation);
        chargeRepository.findUnitPriceByWaterUsage(waterUsage);
    }
}
