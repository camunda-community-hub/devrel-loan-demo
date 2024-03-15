package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.ApplicationValidatedCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.AssessRiskCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.FinalApprovalCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateCollateralCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Services.CreditReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCollateralHandler implements Command.Handler<ValidateCollateralCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(ValidateCollateralHandler.class);

    @Autowired
    Pipeline pipeline;

    @Override
    public Voidy handle(ValidateCollateralCommand command) {
        log.info("validate collateral command called for app id {}", command.getLoanApplication().getId());

        // in a real process, this would likely require human intervention
        // imagine a ui with a list of pending applications that require collateral to be checked
        // the loan officer selects an application, reviews the supporting documents, and approves or denies
        // let's sleep to mimic that for now

        try {
            Thread.sleep(15000);
            pipeline.send(new FinalApprovalCommand(command.getLoanApplication()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
