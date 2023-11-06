# CalculatorAPP - Back-End [![My Skills](https://skillicons.dev/icons?i=java,spring)](https://skillicons.dev)

<p>This repository is the back-end for my Calculator project, the front-end is found at: https://github.com/andrade-filipe/calculator-app</p>
<p>made with Java and Spring, it's a simple API.</p>
<p>The goal is process correctly all mathematical expressions the user sends from the front-end</p>

## Features

<p>Response Class: Custom Response class so my API can be always consistent ✔️</p>
<p>ExpressionInput Class: Controls how the Expression is inputted into the API ✔️</p>
<ul>
<li>CalculatorController: Manage all requests made to the API ✔️</li>
    <ul>
        <li>/expression: Returns the current value of the expression ✔️</li>
        <li>/solve: Returns the solved expression ✔️</li>
        <li>/clear: Returns the expression cleared ✔️</li>
        <li>/build: Process the inputs from the user ✔️</li>
    </ul>
</ul>
<p>CalculatorService: Process all the requests made to the Controller ✔️</p>
<ul>
    <li>Exception Handler ✔️</li>
    <ul>
        <li>Percentage Calculations ✔️</li>
        <li>Invalid Expressions ✔️</li>
        <li>Empty Expressions ✔️</li>
    </ul>
</ul>

