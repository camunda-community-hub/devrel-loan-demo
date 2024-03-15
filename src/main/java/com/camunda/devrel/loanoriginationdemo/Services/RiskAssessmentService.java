package com.camunda.devrel.loanoriginationdemo.Services;

import an.awesome.pipelinr.Pipeline;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
import com.camunda.devrel.loanoriginationdemo.Models.RiskAssessmentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RiskAssessmentService {
    private final Logger log = LoggerFactory.getLogger(RiskAssessmentService.class);

    @Autowired
    Pipeline pipeline;

    public RiskAssessmentResult assess(LoanApplication loanApplication, CreditReport creditReport) throws InterruptedException {
        Integer creditScore = creditReport.getCreditScore();
        RiskAssessmentResult result = new RiskAssessmentResult();

        // simple automated review
        if (creditScore > 800) {
            // default approval for everything if they have near perfect credit
            result.setPassed(true);
        } else if (creditScore < 500) {
            // default rejection for everything if they have poor credit
            result.setPassed(false);
            result.setReason("Your credit score is too low.");
        } else {
            // this would likely need a human to review it
            // let's fake it with a sleep that feels long while sitting quietly in a room
            // but really it's only a few seconds
            Thread.sleep(15000);

            // for this code example, let's randomly approve or reject it
            boolean isApproved = (new Random()).nextBoolean();
            if (isApproved) {
                result.setPassed(true);
            } else {
                result.setPassed(false);
                result.setReason("The Gods have spoken and you are not worthy.");
            }
        }

        return result;
    }
}
