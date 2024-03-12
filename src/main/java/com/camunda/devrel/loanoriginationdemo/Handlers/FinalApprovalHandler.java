package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.FinalApprovalCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.SignPaperworkCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateCollateralCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinalApprovalHandler implements Command.Handler<FinalApprovalCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(FinalApprovalHandler.class);

    @Autowired
    Pipeline pipeline;

    @Override
    public Voidy handle(FinalApprovalCommand command) {
        log.info("final approval command called for app id {}", command.getLoanApplication().getId());

        // in a real process, this would likely require human intervention
        // imagine a ui with a list of pending applications that require final approval
        // the loan officer selects an application, reviews the supporting documents, and approves or denies
        // let's sleep to mimic that for now

        try {
            Thread.sleep(15000);
            pipeline.send(new SignPaperworkCommand(command.getLoanApplication()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
