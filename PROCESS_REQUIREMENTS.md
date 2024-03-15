# Loan Origination Steps

Your task is to model a loan origination using BPMN and Camunda Web Modeler.
If you don't have a free Camunda account yet, sign up at https://camunda.io/.

## Loan origination requirements

The loan origination process starts when a user submits a loan application form ...

1. First, the application must be validated to ensure everything is filled out and withing parameters. We will not support auto loans for more than $45,000, or home loans for less than $20,000 or more than $450,000.
2. If the application is not valid, we need to send a rejection email.
3. Next, a credit report and score is pulled for the applicant.
4. After the report is available, a risk assessment is performed. If their credit score is more than 800 it's an automatic approval; if it's less than 500, it's an automatic rejection.
5. If the risk assessment passes, then the collateral needs to be validated.
6. Then the application is sent to the underwriter for final approval. This task cannot be automated, it must be performed by a human.
7. If the underwriter approves the loan, then all the loan paperwork needs to be signed by both the financial institution and the applicant.
8. Finally, the funds are disbursed!