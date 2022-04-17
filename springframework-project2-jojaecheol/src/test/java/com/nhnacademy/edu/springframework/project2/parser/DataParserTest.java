package com.nhnacademy.edu.springframework.project2.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springframework.project2.report.BasicResultReport;
import com.nhnacademy.edu.springframework.project2.report.ResultReport;
import com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataParserTest {
    ChargeRepository chargeRepository;
    DataParser dataParser;
    ResultReport resultReport;

    @BeforeEach
    void setUp() {
        chargeRepository = new BasicChargeRepository(dataParser);
        resultReport = new BasicResultReport();
    }

    @Test
    void parseTest() {
        dataParser = new CsvDataParser();
        String fileLocation = "data/Tariff_20220331.csv";
        int csvFileSize = 303;

        List<Tariff> tariffs = new ArrayList<>();

        assertThat(tariffs).isEmpty();

        // when
        tariffs = dataParser.parse(fileLocation);

        // then
        assertThat(tariffs).hasSize(csvFileSize);

    }
}
