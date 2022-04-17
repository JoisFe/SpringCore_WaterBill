package com.nhnacademy.edu.springframework.project2.report;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class BasicResultReport implements ResultReport {
    private static final Log log = LogFactory.getLog(BasicResultReport.class);

    @Override
    public void report(List<Tariff> tariffs, Map<Integer, Long> billTotal) {
        for (Tariff tariff : tariffs) {
            log.info("WaterBill{city =" + tariff.getCity() + ", sector = " + tariff.getSector() +
                ", unitPrice = " + tariff.getUnitPrice() + ", billTotal = " +
                billTotal.get(tariff.getId()) + "}");
        }

    }
}
