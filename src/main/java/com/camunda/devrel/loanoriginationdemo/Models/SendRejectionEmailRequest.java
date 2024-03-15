package com.camunda.devrel.loanoriginationdemo.Models;

public class SendRejectionEmailRequest {
    private Long loanApplicationId;

    private String reason;

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
