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

public class DataParserTest {
    ChargeRepository chargeRepository;
    CsvDataParser csvDataParser;
    JsonDataParser jsonDataParser;
    ResultReport resultReport;

    @BeforeEach
    void setUp() {
        chargeRepository = new BasicChargeRepository(csvDataParser, jsonDataParser);
        resultReport = new BasicResultReport();
    }

    @Test
    void parseTest() {
        csvDataParser = new CsvDataParser();
        jsonDataParser = new JsonDataParser();
        String csvFileLocation = "data/Tariff_20220331.csv";
        String jsonFileLocation = "data/Tariff_20220331.json";
        int csvFileSize = 303;

        List<Tariff> csvTariffs = new ArrayList<>();
        List<Tariff> jsonTariffs = new ArrayList<>();

        assertThat(csvTariffs.size()).isZero();
        assertThat(jsonTariffs.size()).isZero();

        // when
        csvTariffs = csvDataParser.parse(csvFileLocation);
        jsonTariffs = jsonDataParser.parse(jsonFileLocation);

        // then
        assertThat(csvTariffs.size()).isEqualTo(csvFileSize);
        assertThat(jsonTariffs.size()).isEqualTo(csvFileSize);
    }
}
