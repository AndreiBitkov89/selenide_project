# SelenideTestProject 🧪

## 📋 Описание
Автотесты для сайта [demoblaze.com](https://www.demoblaze.com/) с использованием Selenide, JUnit и Allure.

## 🚀 Технологии
- Selenide
- JUnit 5
- Java Faker
- Allure Reports

## 🔧 Как запустить

1. Клонировать проект:
   ```bash
   git clone https://github.com/abitk123/selenideTestPractice.git

2. Запуск тестов :
   ```bash 
   mvn clean test

3. Формирование отчета :
   ```bash 
   allure generate ./allure-results --clean -o ./allure-report
   allure serve ./allure-results