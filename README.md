# CalculatorAPP - back-end
<p>This repository is the back-end for my Calculator project, the front-end is found at: https://github.com/andrade-filipe/calculator-app
</p>
<p>made with Java and Spring, it's a simple Rest API
</p>

## Controller

### CalculatorController
<p>This class maps the requests URL and calls the respective method according to the request that the user has made</p>

<p>has 4 methods on /api/v1:</p>
<p></p>
<p>(/expression) getExpression():</p>(/clear) clear():
<p>(/solve) solve():</p>
<p>(/build) buildExpression(ExpressionInput):</p>

## Model

### ExpressionInput
<p>the template expected for any expression input, it helps the application read the json</p>

### Response
<p>the model template for any API response, with all needed information, this is really helpful for the consumers of the API</p>

## Domain/Service

### CalculatorService
<p>It's in this class that you want to code the methods that will be used by the Controller, every class should have a specific function inside the application, the service is to processes the request made to the controller
</p>
<p>3 methods that interact with CalculatorController:</p>
<p>solveExpression(string):</p>
<p>buildExpression(string):</p>
<p>clear():</p>

<p>the other method is to deal with the percentage char (%), because the library net.objecthunter.exp4j i chose to help me process the mathematical expressions doesn't do it very well.</p>
<p>percentageCase(string):</p>
