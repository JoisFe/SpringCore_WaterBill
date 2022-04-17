package com.nhnacademy.edu.springframework.project2.aop;

import com.nhnacademy.edu.springframework.project2.repository.ChargeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AopConfiguration {
    private static final Log log = LogFactory.getLog(AopConfiguration.class);

    ChargeRepository chargeRepository;

    @Autowired
    public AopConfiguration(
        ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Before(
        "execution(* com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository.get*(..))" +
            "|| execution(* com.nhnacademy.edu.springframework.project2.repository.BasicChargeRepository.findUnitPriceByWaterUsage(..))" +
            "|| execution(* com.nhnacademy.edu.springframework.project2.service.BasicWaterSupplyChargeService.calculateCharge(..))"
    )
    private void catchNotScoresLoaded() {
        if (!chargeRepository.isLoaded()) {
            throw new IllegalStateException();
        }
    }

    @Around("execution(public * com.nhnacademy.edu.springframework.project2.service.*.*(..))" +

        "|| execution(public * com.nhnacademy.edu.springframework.project2.report.*.*(..))"
    )
    public Object loggingTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch(pjp.getSignature().getName());

        try {
            stopWatch.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }
}

