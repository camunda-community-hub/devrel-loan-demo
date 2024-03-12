package com.camunda.devrel.loanoriginationdemo.Data;

import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    Collection<LoanApplication> findByStatus(LoanStatus status);
}
