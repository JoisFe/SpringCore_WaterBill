package com.nhnacademy.edu.springframework.project2.report;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultReportTest {
    ChargeRepository chargeRepository;
    WaterSupplyChargeService waterSupplyChargeService;
    DataParser dataParser;
    ResultReport resultReport;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
        chargeRepository = new BasicChargeRepository(dataParser);
        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
    }

    @Test
    void reportTest() {
        // given
        resultReport = mock(ResultReport.class);
        String fileLocation = "data/Tariff_20220331.csv";
        int waterUsage = 1000;

        waterSupplyChargeService.loadAndFindUnitPrice(fileLocation, waterUsage);
        waterSupplyChargeService.calculateCharge(waterUsage);

        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();
        Map<Integer, Long> billTotal =chargeRepository.getBillTotal();

        // when
        resultReport.report(findedTariffs, billTotal);

        // then
        verify(resultReport, times(1)).report(findedTariffs, billTotal);

    }
}
