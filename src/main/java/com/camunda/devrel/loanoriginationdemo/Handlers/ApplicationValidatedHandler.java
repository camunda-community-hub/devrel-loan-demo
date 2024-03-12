package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.ApplicationValidatedCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.AssessRiskCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Services.CreditReportService;
import com.camunda.devrel.loanoriginationdemo.Services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationValidatedHandler implements Command.Handler<ApplicationValidatedCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(ApplicationValidatedHandler.class);

    @Autowired
    Pipeline pipeline;

    private final CreditReportService creditReportService;

    public ApplicationValidatedHandler(CreditReportService creditReportService) {
        this.creditReportService = creditReportService;
    }

    @Override
    public Voidy handle(ApplicationValidatedCommand command) {
        log.info("application validated command called for app id {}", command.getLoanApplication().getId());

        try {
            CreditReport creditReport = creditReportService.retrieveCreditReport(command.getLoanApplication().getApplicant());
            pipeline.send(new AssessRiskCommand(command.getLoanApplication(), creditReport));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
