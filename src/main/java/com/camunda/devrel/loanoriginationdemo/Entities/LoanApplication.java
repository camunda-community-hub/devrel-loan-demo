package com.camunda.devrel.loanoriginationdemo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "LoanApplications")
public class LoanApplication {
    @Id
    @GeneratedValue
    private Long id;

    private LoanType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private LoanApplicant applicant;

    private Double amount;

    private LoanStatus status;

    @OneToOne
    private CreditReport creditReport;

    public LoanApplication(LoanType type, Double amount, LoanStatus status) {
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public LoanApplication() {

    }

    public Long getId() {
        return id;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public LoanApplicant getApplicant() {
        return applicant;
    }

    public void setApplicant(LoanApplicant applicant) {
        this.applicant = applicant;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public CreditReport getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReport creditReport) {
        this.creditReport = creditReport;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", type=" + type +
                ", applicant=" + applicant +
                ", amount=" + amount +
                ", status=" + status +
                ", creditReport=" + creditReport +
                '}';
    }
}
