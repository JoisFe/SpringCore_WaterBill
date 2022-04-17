//package com.nhnacademy.edu.springframework.project2.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
//import com.nhnacademy.edu.springframework.project2.parser.DataParser;
//import com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService;
//import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class ChargeRepositoryTest {
//    ChargeRepository chargeRepository;
//    WaterSupplyChargeService waterSupplyChargeService;
//    DataParser dataParser;
//
//    @BeforeEach
//    void setUp() {
//        dataParser = new CsvDataParser();
//        chargeRepository = new BasicChargeRepository(dataParser);
//        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
//    }
//
//    @Test
//    void loadTest() {
//        String fileLocation = "data/Tariff_20220331.csv";
//        int fileDataSize = 303;
//
//        assertThat(chargeRepository.getTariffs().size()).isZero();
//
//        // when
//        chargeRepository.load(fileLocation);
//
//        // then
//        assertThat(chargeRepository.getTariffs().size()).isEqualTo(fileDataSize);
//    }
//
//    @Test
//    void findUnitPriceByWaterUsageTest() {
//        String fileLocation = "data/Tariff_20220331.csv";
//        int waterUsage = 1000;
//
//        // when
//        chargeRepository.load(fileLocation);
//
//        // then
//        List<Tariff> findedTariffs = chargeRepository.getFindedTariffs();
//        int i;
//        for (i = 0; i < findedTariffs.size(); ++i) {
//            assertThat(findedTariffs.get(i).getIntervalStart() <= waterUsage).isTrue();
//            assertThat(findedTariffs.get(i).getIntervalEnd() >= waterUsage).isTrue();
//        }
//
//    }
//}
