package com.camunda.devrel.loanoriginationdemo.Services;

import com.camunda.devrel.loanoriginationdemo.Data.CreditReportRepository;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import com.camunda.devrel.loanoriginationdemo.Handlers.ApplicationValidatedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CreditReportService {
    private final Logger log = LoggerFactory.getLogger(CreditReportService.class);
    private final CreditReportRepository repository;

    public CreditReportService(CreditReportRepository repository) {
        this.repository = repository;
    }

    public CreditReport retrieveCreditReport(LoanApplicant applicant) throws InterruptedException {
        // typically this would call an external credit bureau or service
        // turnaround times are not often instant, so let's delay a bit
        Thread.sleep(5000);

        CreditReport report = new CreditReport(generateRandomCreditScore());
        report.setLoanApplicant(applicant);

        log.info("giving credit score of {} to {} {}", report.getCreditScore(), applicant.getGivenName(), applicant.getSurname());

        return repository.save(report);
    }

    private Integer generateRandomCreditScore() {
        return ThreadLocalRandom.current().nextInt(300, 850 + 1);
    }
}
