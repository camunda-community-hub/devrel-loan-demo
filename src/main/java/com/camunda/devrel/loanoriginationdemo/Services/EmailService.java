package com.camunda.devrel.loanoriginationdemo.Services;

import com.camunda.devrel.loanoriginationdemo.Controllers.LoanApplicationsController;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendRejectionNotification(LoanApplication application, String reason) {
        log.info("sending rejection email for application id {} for reason {}",
                application.getId(),
                reason);
    }
}
