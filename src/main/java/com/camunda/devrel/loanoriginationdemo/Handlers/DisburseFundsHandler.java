package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.DisburseFundsCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateCollateralCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisburseFundsHandler implements Command.Handler<DisburseFundsCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(DisburseFundsHandler.class);

    @Autowired
    Pipeline pipeline;

    @Override
    public Voidy handle(DisburseFundsCommand command) {
        log.info("disburse funds command called for app id {}", command.getLoanApplication().getId());

        // in a real process, this would likely require human intervention
        // after the paperwork is signed, the loan officer returns to their desk ...
        // ... they click a button in their banking app, and this gets called
        // let's sleep to mimic that for now

        return null;
    }
}
