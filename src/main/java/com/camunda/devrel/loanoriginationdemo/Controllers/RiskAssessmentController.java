package com.camunda.devrel.loanoriginationdemo.Controllers;

import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Models.RiskAssessmentRequest;
import com.camunda.devrel.loanoriginationdemo.Models.RiskAssessmentResult;
import com.camunda.devrel.loanoriginationdemo.Services.CreditReportService;
import com.camunda.devrel.loanoriginationdemo.Services.LoanApplicationService;
import com.camunda.devrel.loanoriginationdemo.Services.RiskAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/riskAssessment")
public class RiskAssessmentController {
    private final Logger log = LoggerFactory.getLogger(RiskAssessmentController.class);

    private final LoanApplicationService loanApplicationService;

    private final CreditReportService creditReportService;

    private final RiskAssessmentService riskAssessmentService;

    public RiskAssessmentController(LoanApplicationService loanApplicationService, CreditReportService creditReportService, RiskAssessmentService riskAssessmentService) {
        this.loanApplicationService = loanApplicationService;
        this.creditReportService = creditReportService;
        this.riskAssessmentService = riskAssessmentService;
    }

    @PostMapping()
    public ResponseEntity<RiskAssessmentResult> assess(@RequestBody RiskAssessmentRequest request) throws InterruptedException {
        Optional<LoanApplication> loanApplication = loanApplicationService.findById(request.getLoanApplicationId());
        Optional<CreditReport> creditReport = creditReportService.findById(request.getCreditReportId());

        if (loanApplication.isEmpty() || creditReport.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(riskAssessmentService.assess(loanApplication.get(), creditReport.get()));
    }
}
