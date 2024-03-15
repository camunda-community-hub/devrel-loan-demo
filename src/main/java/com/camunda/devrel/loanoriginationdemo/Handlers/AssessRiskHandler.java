package com.camunda.devrel.loanoriginationdemo.Handlers;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import com.camunda.devrel.loanoriginationdemo.Commands.ApplicationValidatedCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.AssessRiskCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Commands.ValidateCollateralCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Models.RiskAssessmentResult;
import com.camunda.devrel.loanoriginationdemo.Services.CreditReportService;
import com.camunda.devrel.loanoriginationdemo.Services.RiskAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssessRiskHandler implements Command.Handler<AssessRiskCommand, Voidy> {
    private final Logger log = LoggerFactory.getLogger(AssessRiskHandler.class);

    @Autowired
    Pipeline pipeline;

    private final RiskAssessmentService riskAssessmentService;

    public AssessRiskHandler(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @Override
    public Voidy handle(AssessRiskCommand command) {
        log.info("assess risk command called for app id {}", command.getLoanApplication().getId());

        // perhaps an AI algorithm?
        // perhaps a manual person reviewing for risk?
        // perhaps a combination of all of those?
        RiskAssessmentResult result = null;
        try {
            result = riskAssessmentService.assess(command.getLoanApplication(), command.getCreditReport());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!result.getPassed()) {
            pipeline.send(new RejectApplicationCommand(command.getLoanApplication(), result.getReason()));
        }

        pipeline.send(new ValidateCollateralCommand(command.getLoanApplication()));

        return null;
    }
}
