package com.nhnacademy.edu.springframework.project2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChargeRepositoryTest {
    ChargeRepository chargeRepository;
    WaterSupplyChargeService waterSupplyChargeService;
    DataParser dataParser;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
        chargeRepository = new BasicChargeRepository(dataParser);
        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
    }

    @Test
    void loadTest() {
        String fileLocation = "data/Tariff_20220331.csv";
        int fileDataSize = 303;

        assertThat(chargeRepository.getTariffs()).isEmpty();

        // when
        chargeRepository.load(fileLocation);

        // then
        assertThat(chargeRepository.getTariffs()).hasSize(fileDataSize);
    }

    @Test
    void findUnitPriceByWaterUsageTest() {
        String fileLocation = "data/Tariff_20220331.csv";
        int waterUsage = 1000;

        // when
        chargeRepository.load(fileLocation);

        // then
        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();
        for (Tariff findedTariff : findedTariffs) {
            assertThat(findedTariff.getIntervalStart() <= waterUsage).isTrue();
            assertThat(findedTariff.getIntervalEnd() >= waterUsage).isTrue();
        }

        // 오름 차순 정렬 확인
        int i;
        for (i = 0; i < findedTariffs.size() - 1; ++i) {
            assertThat(findedTariffs.get(i).getUnitPrice() <= findedTariffs.get(i + 1).getUnitPrice()).isTrue();
        }

    }
}
