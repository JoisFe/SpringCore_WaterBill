package com.nhnacademy.edu.springframework.project2.report;

import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultReportTest {
    ChargeRepository chargeRepository;
    WaterSupplyChargeService waterSupplyChargeService;
    DataParser dataParser;
    ResultReport resultReport;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
        chargeRepository = new BasicChargeRepository(dataParser);
        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
        resultReport = new BasicResultReport();
    }

    @Test
    void reportTest() {
        // given
        String fileLocation = "data/Tariff_20220331.csv";
        int waterUsage = 1000;

        waterSupplyChargeService.loadAndFindUnitPrice(fileLocation, waterUsage);
        waterSupplyChargeService.calculateCharge(waterUsage);

        // when
        resultReport.report(chargeRepository.getFindedTariffs(), chargeRepository.getBillTotal());

        // then

    }
}
