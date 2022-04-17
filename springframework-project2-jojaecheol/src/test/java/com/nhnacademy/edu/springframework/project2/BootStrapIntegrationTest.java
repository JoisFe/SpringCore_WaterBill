package com.nhnacademy.edu.springframework.project2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.springframework.project2.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project2.parser.DataParser;
import com.nhnacademy.edu.springframework.project2.report.ResultReport;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class BootStrapIntegrationTest {
    AnnotationConfigApplicationContext context;
    ChargeRepository chargeRepository;
    WaterSupplyChargeService waterSupplyChargeService;
    DataParser dataParser;
    ResultReport resultReport;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(
            MainConfiguration.class);
        dataParser = context.getBean("csvDataParser", DataParser.class);
        chargeRepository = context.getBean("basicChargeRepository", ChargeRepository.class);
        waterSupplyChargeService = context.getBean("basicWaterSupplyChargeService", WaterSupplyChargeService.class);
    }

    @Test
    void IntegrationTest() {
        // given
        int waterUsage = 1000;
        String jsonFileLocation = "data/Tariff_20220331.json";

        // load 이외 함수를 load전에 실행시 IllegalStateException 에러를 일으키는지 테스트
        assertThatThrownBy(() -> chargeRepository.findUnitPriceByWaterUsage(waterUsage)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> waterSupplyChargeService.calculateCharge(waterUsage)).isInstanceOf(IllegalStateException.class);

        // when
        waterSupplyChargeService.loadAndFindUnitPrice(jsonFileLocation, waterUsage);
        waterSupplyChargeService.calculateCharge(waterUsage);

        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();
        Map<Integer, Long> billTotal =chargeRepository.getBillTotal();

        Random random = new Random();
        int index = random.nextInt(chargeRepository.getFindedTariffs().size());

        resultReport = mock(ResultReport.class);

        // then

        // load, findUnitPrice 되었는지
        assertThat(chargeRepository.getTariffs()).hasSize(303);
        assertThat(chargeRepository.getFindedTariffs()).hasSize(5);

        for (Tariff findedTariff : findedTariffs) {
            assertThat(findedTariff.getIntervalStart() <= waterUsage).isTrue();
            assertThat(findedTariff.getIntervalEnd() >= waterUsage).isTrue();
        }

        // 오름 차순 정렬 확인
        int i;
        for (i = 0; i < findedTariffs.size() - 1; ++i) {
            assertThat(findedTariffs.get(i).getUnitPrice() <= findedTariffs.get(i + 1).getUnitPrice()).isTrue();
        }

        // calculateCharge 되었는지
        assertThat(billTotal).hasSize(5);
        Tariff tariff = chargeRepository.getFindedTariffs().get(index);
        assertThat(tariff.getUnitPrice() * waterUsage).isEqualTo(billTotal.get(tariff.getId()));

        resultReport.report(findedTariffs, billTotal);
        // report 되었는지
        verify(resultReport, times(1)).report(findedTariffs, billTotal);
    }
}
