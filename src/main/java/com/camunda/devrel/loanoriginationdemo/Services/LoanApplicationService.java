package com.camunda.devrel.loanoriginationdemo.Services;

import an.awesome.pipelinr.Pipeline;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Data.LoanApplicationRepository;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanStatus;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanType;
import com.camunda.devrel.loanoriginationdemo.Models.CreateLoanApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoanApplicationService {
    @Autowired
    Pipeline pipeline;

    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanApplication create(LoanApplicant applicant, LoanType type, Double amount) {
        LoanApplication application = new LoanApplication(type, amount, LoanStatus.SUBMITTED);
        application.setApplicant(applicant);
        repository.save(application);

        // validate application
        pipeline.send(new ValidateApplicationCommand(application));

        return application;
    }

    public LoanApplication approve(Long id) {
        Optional<LoanApplication> loanApplication = findById(id);
        if (loanApplication.isPresent()) {
            LoanApplication loanApp = loanApplication.get();
            loanApp.setStatus(LoanStatus.APPROVED);
            return repository.save(loanApp);
        }

        throw new RuntimeException("Application was not found!");
    }

    public LoanApplication reject(Long id) {
        Optional<LoanApplication> loanApplication = findById(id);
        if (loanApplication.isPresent()) {
            LoanApplication loanApp = loanApplication.get();
            loanApp.setStatus(LoanStatus.DENIED);
            return repository.save(loanApp);
        }

        throw new RuntimeException("Application was not found!");
    }

    public Collection<LoanApplication> findAll() {
        return repository.findAll();
    }

    public Optional<LoanApplication> findById(Long id) {
        return repository.findById(id);
    }

    public Collection<LoanApplication> findAllPending() {
        return Stream.of(
            repository.findByStatus(LoanStatus.SUBMITTED),
            repository.findByStatus(LoanStatus.IN_REVIEW)
        ).flatMap(Collection::stream)
        .collect(Collectors.toList());
    }
}
