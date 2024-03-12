package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.ApplicationValidatedCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanStatus;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateApplicationHandler implements Command.Handler<ValidateApplicationCommand, Voidy> {
    public final Logger log = LoggerFactory.getLogger(ValidateApplicationHandler.class);

    @Autowired
    Pipeline pipeline;

    @Override
    public Voidy handle(ValidateApplicationCommand command) {
        log.info("validate application command called for app id {}", command.getLoanApplication().getId());

        // for simplicity, doing this in the handler
        // it's not necessarily the best enterprise architecture, but that's ok for this demo
        // maybe this should be a dmn? 🧐

        LoanApplication application = command.getLoanApplication();

        if (application.getType() == LoanType.AUTO) {
            if (application.getAmount() > 45000) {
                reject(application,
                "Your loan application is for a luxury vehicle that we do not provide loans for.");
            }
            validated(application);
        } else if (application.getType() == LoanType.HOME) {
            if (application.getAmount() < 20000 || application.getAmount() > 450000) {
                reject(application,
                String.format("Your loan application is for %s than we can issue a loan for.", application.getAmount() < 20000 ? "less" : "more"));
            }
            validated(application);
        }

        return null;
    }

    private void reject(LoanApplication application, String reason) {
        pipeline.send(new RejectApplicationCommand(application, reason));
    }

    private void validated(LoanApplication application) {
        pipeline.send(new ApplicationValidatedCommand(application));
    }
}
