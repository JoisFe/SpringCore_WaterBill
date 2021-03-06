package com.nhnacademy.edu.springframework.project2.repository;

import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BasicChargeRepository implements ChargeRepository {
    DataParser dataParser;

    @Autowired
    public BasicChargeRepository(
        DataParser dataParser) {
        this.dataParser = dataParser;
    }

    private List<Tariff> tariffs = new ArrayList<>();

    private List<Tariff> findedTariffs = new ArrayList<>();
    private final Map<Integer, Long> billTotal = new HashMap<>();
    private boolean loaded;

    @Override
    public List<Tariff> getFindedTariffs() {
        return findedTariffs;
    }

    @Override
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public Map<Integer, Long> getBillTotal() {
        return billTotal;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load(String fileLocation) {
        tariffs = dataParser.parse(fileLocation);

        loaded = true;
    }

    @Override
    public void findUnitPriceByWaterUsage(int waterUsage) {
        List<Tariff> allTariffList = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            if (waterUsage >= tariff.getIntervalStart() && waterUsage <= tariff.getIntervalEnd()) {
                allTariffList.add(tariff);
            }
        }

        allTariffList.sort(Comparator.naturalOrder());

        if (allTariffList.size() > 5) {
            findedTariffs = allTariffList.subList(0, 5);
        } else {
            findedTariffs = allTariffList;
        }
    }
}
