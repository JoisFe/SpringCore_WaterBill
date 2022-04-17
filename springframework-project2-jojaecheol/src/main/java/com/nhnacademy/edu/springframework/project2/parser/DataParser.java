package com.nhnacademy.edu.springframework.project2.parser;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;

public interface DataParser {
    List<Tariff> parse(String fileLocation);
}
