package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.DisburseFundsCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.FinalApprovalCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.SignPaperworkCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateCollateralCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignPaperworkHandler implements Command.Handler<SignPaperworkCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(SignPaperworkHandler.class);

    @Autowired
    Pipeline pipeline;

    @Override
    public Voidy handle(SignPaperworkCommand command) {
        log.info("disburse funds command called for app id {}", command.getLoanApplication().getId());
        log.info("\u001B[32m" + "loan successfully completed! app id {}"+ "\u001B[0m", command.getLoanApplication().getId());

        // in a real process, this would likely require human intervention
        // the loan officer gives you a stack of paper work to sign
        // let's sleep to mimic that for now

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
