package com.camunda.devrel.loanoriginationdemo.Data;

import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicantRepository extends JpaRepository<LoanApplicant, Long> {
    LoanApplicant findByGivenNameAndSurname(String givenName, String surname);
}
