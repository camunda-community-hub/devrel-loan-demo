package com.camunda.devrel.loanoriginationdemo.Commands;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;

public class DisburseFundsCommand implements Command<Voidy> {
    private final LoanApplication loanApplication;

    public DisburseFundsCommand(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }
}
