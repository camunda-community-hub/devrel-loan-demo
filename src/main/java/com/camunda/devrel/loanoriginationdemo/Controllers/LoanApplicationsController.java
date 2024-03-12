package com.camunda.devrel.loanoriginationdemo.Controllers;

import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Models.CreateLoanApplicationRequest;
import com.camunda.devrel.loanoriginationdemo.Services.LoanApplicantService;
import com.camunda.devrel.loanoriginationdemo.Services.LoanApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/loanApplications")
public class LoanApplicationsController {
    private final Logger log = LoggerFactory.getLogger(LoanApplicationsController.class);

    private final LoanApplicationService loanApplicationService;
    private final LoanApplicantService loanApplicantService;

    public LoanApplicationsController(LoanApplicationService loanApplicationService, LoanApplicantService loanApplicantService) {
        this.loanApplicationService = loanApplicationService;
        this.loanApplicantService = loanApplicantService;
    }

    @GetMapping("/all")
    public Collection<LoanApplication> loanApplications() {
        log.info("finding all loan applications");
        return loanApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> findLoanApplicationById(@PathVariable Long id) {
        log.info("finding loan application with id {}", id);
        // this should really have some authorization/identity stuff, but oh well :)
        Optional<LoanApplication> group = loanApplicationService.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pending")
    public Collection<LoanApplication> findPendingApplications() {
        log.info("finding all pending loan applications");
        // this should really have some authorization/identity stuff, but oh well :)
        return loanApplicationService.findAllPending();
    }

    @PostMapping()
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody CreateLoanApplicationRequest request) {
        log.info("creating application from request {}", request);
        LoanApplicant applicant = loanApplicantService.createIfNotExists(
            request.getGivenName(),
            request.getSurname(),
            request.getAddress(),
            request.getCity(),
            request.getStateOrProvince(),
            request.getCountry(),
            request.getPostalCode()
        );

        LoanApplication application = loanApplicationService.create(
            applicant,
            request.getType(),
            request.getAmount()
        );

        return ResponseEntity.ok().body(application);
    }
}
