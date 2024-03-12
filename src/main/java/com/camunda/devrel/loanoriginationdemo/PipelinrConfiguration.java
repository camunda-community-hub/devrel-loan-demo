package com.camunda.devrel.loanoriginationdemo;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelinrConfiguration {

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers) {
        return new Pipelinr().with(commandHandlers::stream);
    }
}
