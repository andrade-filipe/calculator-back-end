# CalculatorAPP - back-end
This repository is the back-end for my Calculator project, the front-end is found at: https://github.com/andrade-filipe/calculator-app

made with Java and Spring, it's a simple Rest API

## Controller

### CalculatorController
This class maps the requests URL and calls the respective method according to the request that the user has made

has 4 methods on /api/v1:
(/expression) getExpression():
(/clear) clear():
(/solve) solve():
(/build) buildExpression(ExpressionInput):

## Model

### ExpressionInput
the template expected for any expression input, it helps the application read the json

### Response
the model template for any API response, with all needed information, this is really helpful for the consumers of the API

## Domain/Service

### CalculatorService
It's in this class that you want to code the methods that will be used by the Controller, every class should have a specific function inside the application, the service is to processes the request made to the controller

3 methods that interact with CalculatorController:
solveExpression(string):
buildExpression(string):
clear():

the other method is to deal with the percentage char (%), because the library net.objecthunter.exp4j i chose to help me process the mathematical expressions doesn't do it very well.
percentageCase(string):
