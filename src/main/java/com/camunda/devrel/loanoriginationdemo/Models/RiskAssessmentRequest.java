package com.camunda.devrel.loanoriginationdemo.Models;

public class RiskAssessmentRequest {
    private Long loanApplicationId;

    private Long creditReportId;

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public Long getCreditReportId() {
        return creditReportId;
    }

    public void setCreditReportId(Long creditReportId) {
        this.creditReportId = creditReportId;
    }
}
