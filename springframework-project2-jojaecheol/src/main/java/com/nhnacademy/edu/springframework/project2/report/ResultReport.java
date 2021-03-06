package com.nhnacademy.edu.springframework.project2.report;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import java.util.Map;

public interface ResultReport {
    void report(List<Tariff> tariffs, Map<Integer, Long> billTotal);
}
