package com.camunda.devrel.loanoriginationdemo.Commands;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;

public class ValidateCollateralCommand implements Command<Voidy> {
    private final LoanApplication loanApplication;

    public ValidateCollateralCommand(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }
}
