package com.nhnacademy.edu.springframework.project2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaterSupplyChargeServiceTest {
    DataParser dataParser;
    ChargeRepository chargeRepository;
    WaterSupplyChargeService waterSupplyChargeService;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
        chargeRepository = new BasicChargeRepository(dataParser);
        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
    }

    @Test
    void calculateChargeTest() {
        int waterUsage = 1000;
        String fileLocation = "data/Tariff_20220331.csv";

        Random random = new Random();

        assertThat(chargeRepository.getBillTotal()).isEmpty();

        // when
        waterSupplyChargeService.loadAndFindUnitPrice(fileLocation, waterUsage);
        waterSupplyChargeService.calculateCharge(waterUsage);

        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();
        Map<Integer, Long> billTotal = chargeRepository.getBillTotal();

        int index = random.nextInt(findedTariffs.size());

        Tariff tariff = findedTariffs.get(index);

        // then
        assertThat(billTotal).hasSize(5);
        assertThat(tariff.getUnitPrice() * waterUsage).isEqualTo(billTotal.get(tariff.getId()));
    }

    @Test
    void loadAndFindUnitPrice() {
        int waterUsage = 1000;
        String fileLocation = "data/Tariff_20220331.csv";
        int fileDataSize = 303;


        assertThat(chargeRepository.getTariffs()).isEmpty();
        assertThat(chargeRepository.getFindedTariffs()).isEmpty();

        // when
        waterSupplyChargeService.loadAndFindUnitPrice(fileLocation, waterUsage);

        // then
        assertThat(chargeRepository.getTariffs()).hasSize(fileDataSize);
        assertThat(chargeRepository.getFindedTariffs()).hasSize(5);

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
