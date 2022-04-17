//package com.nhnacademy.edu.springframework.project2.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.nhnacademy.edu.springframework.project2.parser.CsvDataParser;
//import com.nhnacademy.edu.springframework.project2.parser.DataParser;
//import com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository;
//import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
//import com.nhnacademy.edu.springframework.project2.repository.Tariff;
//import java.util.List;
//import java.util.Random;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class WaterSupplyChargeServiceTest {
//    DataParser dataParser;
//    ChargeRepository chargeRepository;
//    WaterSupplyChargeService waterSupplyChargeService;
//
//    @BeforeEach
//    void setUp() {
//        dataParser = new CsvDataParser();
//        chargeRepository = new BasicChargeRepository(dataParser);
//        waterSupplyChargeService = new BasicWaterSupplyChargeService(chargeRepository);
//    }
//
//    @Test
//    void calculateChargeTest() {
//        int waterUsage = 1000;
//        String fileLocation = "data/Tariff_20220331.csv";
//        Random random = new Random();
//
//        // when
//        waterSupplyChargeService.loadAndFindUnitPrice(fileLocation, waterUsage);
//        waterSupplyChargeService.calculateCharge(waterUsage);
//
//        int index = random.nextInt(chargeRepository.getFindedTariffs().size());
//
//        Tariff tariff = chargeRepository.getFindedTariffs().get(index);
//
//        // then
//        assertThat(tariff.getUnitPrice() * waterUsage).isEqualTo(chargeRepository.getBillTotal().get(tariff.getId()));
//    }
//
//    @Test
//    void loadAndFindUnitPrice() {
//
//    }
//}
