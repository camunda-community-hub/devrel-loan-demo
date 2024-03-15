package com.camunda.devrel.loanoriginationdemo.Data;

import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditReportRepository extends JpaRepository<CreditReport, Long> {
}
