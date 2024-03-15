package com.camunda.devrel.loanoriginationdemo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CreditReports")
public class CreditReport {
    @Id
    @GeneratedValue
    private Long id;

    private Integer creditScore;

    @ManyToOne
    private LoanApplicant loanApplicant;

    @OneToOne
    private LoanApplication loanApplication;

    public CreditReport(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public CreditReport() {

    }

    public Long getId() {
        return id;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public LoanApplicant getLoanApplicant() {
        return loanApplicant;
    }

    public void setLoanApplicant(LoanApplicant loanApplicant) {
        this.loanApplicant = loanApplicant;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
