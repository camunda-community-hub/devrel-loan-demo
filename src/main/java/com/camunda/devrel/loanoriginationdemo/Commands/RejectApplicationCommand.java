package com.camunda.devrel.loanoriginationdemo.Commands;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;

public class RejectApplicationCommand implements Command<Voidy> {
    private final LoanApplication loanApplication;
    private final String reason;

    public RejectApplicationCommand(LoanApplication loanApplication, String reason) {
        this.loanApplication = loanApplication;
        this.reason = reason;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public String getReason() { return this.reason; }
}
