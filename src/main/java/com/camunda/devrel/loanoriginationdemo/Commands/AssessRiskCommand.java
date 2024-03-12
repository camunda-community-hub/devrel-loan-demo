package com.camunda.devrel.loanoriginationdemo.Commands;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;

public class AssessRiskCommand implements Command<Voidy> {
    private final LoanApplication loanApplication;

    private final CreditReport creditReport;

    public AssessRiskCommand(LoanApplication loanApplication, CreditReport creditReport) {
        this.loanApplication = loanApplication;
        this.creditReport = creditReport;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public CreditReport getCreditReport() {
        return creditReport;
    }
}
