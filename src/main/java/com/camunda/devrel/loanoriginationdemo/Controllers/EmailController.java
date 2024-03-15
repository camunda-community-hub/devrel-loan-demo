package com.camunda.devrel.loanoriginationdemo.Controllers;

import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Models.SendRejectionEmailRequest;
import com.camunda.devrel.loanoriginationdemo.Services.EmailService;
import com.camunda.devrel.loanoriginationdemo.Services.LoanApplicationService;
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
@RequestMapping("/api/email")
public class EmailController {
    private final Logger log = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    private final LoanApplicationService loanApplicationService;

    public EmailController(EmailService emailService, LoanApplicationService loanApplicationService) {
        this.emailService = emailService;
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping("/rejection")
    public ResponseEntity<Boolean> sendRejectionEmail(@RequestBody SendRejectionEmailRequest request) {
        Optional<LoanApplication> loanApplication = loanApplicationService.findById(request.getLoanApplicationId());
        if (loanApplication.isPresent()) {
            emailService.sendRejectionNotification(loanApplication.get(), request.getReason());
            return ResponseEntity.ok().body(true);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
