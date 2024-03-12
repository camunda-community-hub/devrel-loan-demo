package com.camunda.devrel.loanoriginationdemo;

import com.camunda.devrel.loanoriginationdemo.Data.LoanApplicantRepository;
import com.camunda.devrel.loanoriginationdemo.Data.LoanApplicationRepository;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanStatus;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final LoanApplicantRepository loanApplicantRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    public Initializer(
            LoanApplicantRepository loanApplicantRepository,
            LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicantRepository = loanApplicantRepository;
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of(
            new LoanApplicant("Nathan", "Loding", "123 Main St", "Townsville", "State", "USA", "12345"),
            new LoanApplicant("Niall", "Deehan", "123 Main St", "Townsville", "State", "USA", "12345"),
            new LoanApplicant("Samantha", "Holstine", "123 Main St", "Townsville", "State", "USA", "12345")
        ).forEach(loanApplicantRepository::save);

        loanApplicantRepository.findAll().forEach(System.out::println);

        loanApplicantRepository.findAll().forEach(applicant -> {
            LoanApplication loanApp1 = new LoanApplication(LoanType.AUTO, 20000.00, LoanStatus.APPROVED);
            LoanApplication loanApp2 = new LoanApplication(LoanType.HOME, 120000.00, LoanStatus.IN_REVIEW);

            applicant.setApplications(Set.of(loanApp1, loanApp2));
            System.out.println(applicant);
            loanApplicantRepository.saveAndFlush(applicant);
        });

        loanApplicationRepository.findAll().forEach(System.out::println);
    }
}
