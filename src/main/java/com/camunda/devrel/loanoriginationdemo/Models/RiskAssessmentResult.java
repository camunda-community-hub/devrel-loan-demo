package com.camunda.devrel.loanoriginationdemo.Models;

public class RiskAssessmentResult {
    private Boolean passed;

    private String reason;

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
