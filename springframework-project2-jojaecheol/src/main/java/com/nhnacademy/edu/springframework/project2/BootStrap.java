package com.nhnacademy.edu.springframework.project2;

import com.nhnacademy.edu.springframework.project2.config.MainConfiguration;
import com.nhnacademy.edu.springframework.project2.report.ResultReport;
import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import com.nhnacademy.edu.springframework.project2.service.WaterSupplyChargeService;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    private static final Log log = LogFactory.getLog(BootStrap.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            MainConfiguration.class)) {
            WaterSupplyChargeService waterSupplyChargeService =
                context.getBean("basicWaterSupplyChargeService",
                    WaterSupplyChargeService.class);
            ResultReport resultReport = context.getBean("basicResultReport",
                ResultReport.class);
            ChargeRepository chargeRepository =
                context.getBean("basicChargeRepository", ChargeRepository.class);

            int waterUsage;

            String jsonFileLocation = "data/Tariff_20220331.json";

            while (true) {
                log.info("사용량을 입력해 주세요 (그만하시려면 0을 입력해주세요) : ");
                waterUsage = scanner.nextInt();
                if (waterUsage == 0) {
                    break;
                }
                waterSupplyChargeService.loadAndFindUnitPrice(jsonFileLocation, waterUsage);
                waterSupplyChargeService.calculateCharge(waterUsage);
                resultReport.report(chargeRepository.getFindedTariffs(),
                    chargeRepository.getBillTotal());
            }
        }
    }
}
