package com.camunda.devrel.loanoriginationdemo.Services;

import an.awesome.pipelinr.Pipeline;
import com.camunda.devrel.loanoriginationdemo.Commands.RejectApplicationCommand;
import com.camunda.devrel.loanoriginationdemo.Entities.CreditReport;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplication;
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

    public void assess(LoanApplication loanApplication, CreditReport creditReport) throws InterruptedException {
        Integer creditScore = creditReport.getCreditScore();

        // simple automated review
        if (creditScore > 800) {
            // default approval for everything if they have near perfect credit

        } else if (creditScore < 500) {
            // default rejection for everything if they have poor credit
            pipeline.send(new RejectApplicationCommand(loanApplication, "Your credit score is too low."));
        } else {
            // this would likely need a human to review it
            // let's fake it with a sleep that feels long while sitting quietly in a room
            // but really it's only a few seconds
            Thread.sleep(15000);

            // for this code example, let's randomly approve or reject it
            boolean isApproved = (new Random()).nextBoolean();
            if (isApproved) {

            } else {
                pipeline.send(new RejectApplicationCommand(loanApplication, "The Gods have spoken and you are not worthy."));
            }
        }
    }
}
