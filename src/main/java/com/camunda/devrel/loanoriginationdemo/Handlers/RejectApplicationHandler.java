package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.ApplicationValidatedCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanType;
import com.camunda.devrel.loanoriginationdemo.Services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectApplicationHandler implements Command.Handler<RejectApplicationCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(RejectApplicationHandler.class);

    @Autowired
    Pipeline pipeline;

    private final EmailService emailService;

    public RejectApplicationHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public Voidy handle(RejectApplicationCommand command) {
        log.info("reject application command called for app id {}", command.getLoanApplication().getId());
        emailService.sendRejectionNotification(
            command.getLoanApplication(),
            command.getReason()
        );

        return null;
    }
}
