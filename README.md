📋 Overview

Automated UI test suite for demoblaze.com using:

🚀 Technologies Used
Selenide
JUnit 5
Allure Report
Maven
Java 17+

🏗️ Architecture Patterns

- Page Object Model
- Component Element
- Factory
- Loadable Component
- Value Object
- Decorator
- Builder
- Data Registry
- Steps

📦 Install dependencies

`mvn clean install
`

🚀 Run tests

`mvn clean test`

📂 Generate Allure report

`allure generate target/allure-results --clean -o allure-report
`

🧪 Run a specific test class

`mvn -Dtest=LoginTests test`


