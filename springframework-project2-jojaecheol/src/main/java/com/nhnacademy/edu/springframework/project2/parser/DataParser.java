package com.nhnacademy.edu.springframework.project2.parser;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import org.springframework.stereotype.Service;

public interface DataParser {
    List<Tariff> parse(String fileLocation);
}
