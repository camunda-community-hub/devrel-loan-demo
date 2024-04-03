[![Community Extension](https://img.shields.io/badge/Community%20Extension-An%20open%20source%20community%20maintained%20project-FF4700)](https://github.com/camunda-community-hub/community)
[![](https://img.shields.io/badge/Lifecycle-Proof%20of%20Concept-blueviolet)](https://github.com/Camunda-Community-Hub/community/blob/main/extension-lifecycle.md#proof-of-concept-)
![Compatible with: Camunda 8](https://img.shields.io/badge/Compatible%20with-Camunda%208-0072Ce)
![](https://img.shields.io/badge/Tutorial%20Reference%20Project-Tutorials%20for%20getting%20started%20with%20Camunda-%2338A3E1)

# Camunda Loan Origination Demonstration

## Goals

This project aims to demonstrate how to add process orchestration to an existing application.
By following this README and the accompanying materials, the goal is to:
- create a greater understanding of process orchestration
- how to model processes with BPMN
- how to connect your process to an existing application.

The goal, in other words, is for the process engine handles _the state of your process_ while
your application continues to manage the _data_. The goal is to have each step of the process
not have any knowledge or requirement of what the next step in the process in: they handle the
task given to them, and the process engine moves to the next step.

## Requirements

To interact with the API, you will need an API client like [Bruno](https://www.usebruno.com/) or
[Postman](https://www.postman.com/).

To automate the process, you will need a free Camunda account. Sign up for your free account at
https://camunda.io/.

Knowledge of basic [BPMN modeling](https://docs.camunda.io/docs/components/modeler/bpmn/bpmn-primer/)
is needed. [Camunda Academy](https://academy.camunda.com/) has a wonderful set of training videos to
get you started with BPMN. At a minimum, you need to understand:
- start and end events
- service tasks
- user tasks
- gateways (XOR)

To run this project, you either need Java 21+ installed and/or Docker.

### Docker

The included `DOCKERFILE` will build and start the application. First you need to build
the image:
```bash
docker build --tag "camunda-loan-demo" .
```
After the image is build, you can run it:
```bash
docker run -p 8080:8080 -d camunda-loan-demo
```
By using the `-p 8080:8080` argument, Docker forwards port 8080 to the container. You will be
able to connect to the API using `http://localhost:8080`.

### Java

With Java 21+ installed, run:
```bash
./mvnw spring-boot:run
```

## Application Overview

The application is extremely simple and does not have much business logic. It is meant to
provide the feeling of a production application without the need to spend time learning how the
application functions first.

Using the CQRS pattern, the application mimics an event-driven application that accepts a
loan application form from an end user (applicant), then moves the loan through a series of 
validations and checks before either rejecting or approving the loan. The steps are outlined in
the [PROCESS REQUIREMENTS](./PROCESS_REQUIREMENTS.md) document.

To understand what API's are available, two collections are provided. One is for
[Bruno](https://www.usebruno.com/), an open source API Client; the other is for
[Postman](https://www.postman.com/). Import the collection for whichever API client/manager you
prefer.
- Bruno: [bruno-collection.json](./bruno-collection.json)
- Postman: [postman-collection.json](./postman-collection.json)

### Test a loan request

Using your API client, send a POST request to the `/api/loanApplications` endpoint with the
following body:
```json
{
  "givenName": "James",
  "surname": "Holden",
  "address": "123 Main St.",
  "city": "Windmills",
  "stateOrProvince": "Montana",
  "country": "USA",
  "postalCode": "59354",
  "type": "HOME",
  "amount": 220000
}
```
The application mimics a process that has many steps, some which require human interaction, so
the response can take 15 seconds. Give it some time to process, and you should get a response.

## The Challenge

Following the requirements outlined in the [PROCESS REQUIREMENTS](./PROCESS_REQUIREMENTS.md),
craft an executable BPMN model that eliminates the need for CQRS within the application,
integrate the service tasks with the API, and, optionally, create a DMN table to automate some of
the simple rules (for instance, basic rejection based on credit score). 

### Steps

1. Craft a BPMN model using [Camunda Web Modeler](https://docs.camunda.io/docs/components/modeler/web-modeler/launch-cloud-modeler/)
   that fulfills the [PROCESS REQUIREMENTS](./PROCESS_REQUIREMENTS.md)
2. Configure the [service tasks](https://docs.camunda.io/docs/components/modeler/bpmn/service-tasks/)
   to call the API using the [REST Connector](https://docs.camunda.io/docs/components/connectors/protocol/rest/)
3. Implement [forms](https://docs.camunda.io/docs/components/modeler/forms/camunda-forms-reference/)
   for the tasks that require humans (for instance, signing the loan paperwork)
4. Optionally, implement a [DMN table](https://docs.camunda.io/docs/components/modeler/dmn/) that
   moves some simple rules into the model (for instance, home loans greater than $450,000 are
   rejected)
5. Optionally, explore using a [job worker](https://docs.camunda.io/docs/components/concepts/job-workers/)
   instead of a Connector

## Workshop

This demonstration was originally given as a [workshop at SCaLE 21x](https://www.socallinuxexpo.org/scale/21x/presentations/orchestrate-your-most-complex-business-processes)
in Pasadena, CA, on March 15, 2024. Slides for the workshop can be [found here](https://docs.google.com/presentation/d/1bGsZFfWpYOpOD7_A8Fc36wJBnCiJtTtIXgrmrG_Q0TY/edit?usp=sharing).

Interested in having this presented as a workshop to your company or conference? Let the Camunda
Developer Relations know by emailing us at community@camunda.com!

## TODO

There are many features that could be added and improved in this demonstration. Some ideas:
- simple front-end that shows the forms
- branch with a completed model and cleaned up code
- branch using job workers with a completed model and cleaned up code
- example custom Connector that extends the REST Connector