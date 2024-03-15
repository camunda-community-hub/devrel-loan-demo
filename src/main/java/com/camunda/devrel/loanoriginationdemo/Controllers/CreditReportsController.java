package com.camunda.devrel.loanoriginationdemo.Controllers;

import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import com.camunda.devrel.loanoriginationdemo.Services.CreditReportService;
import com.camunda.devrel.loanoriginationdemo.Services.LoanApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/creditReports")
public class CreditReportsController {
    private final Logger log = LoggerFactory.getLogger(CreditReportsController.class);

    private final CreditReportService creditReportService;

    private final LoanApplicantService loanApplicantService;

    public CreditReportsController(CreditReportService creditReportService, LoanApplicantService loanApplicantService) {
        this.creditReportService = creditReportService;
        this.loanApplicantService = loanApplicantService;
    }

    @GetMapping("/{loanApplicantId}")
    public ResponseEntity<CreditReport> retrieveCreditReport(@PathVariable Long loanApplicantId) throws InterruptedException {
        Optional<LoanApplicant> loanApplicant = loanApplicantService.findById(loanApplicantId);
        if (loanApplicant.isPresent()) {
            return ResponseEntity.ok().body(creditReportService.retrieveCreditReport(loanApplicant.get()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
