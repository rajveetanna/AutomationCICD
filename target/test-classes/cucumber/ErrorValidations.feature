@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce Page
    When I logged in with username <userName> and password <password>
    Then "Incorrect email or password." message is displayed

  Examples: 
      | userName                 | passowrd | 
      | rajveebhatt178@gmail.com | Vivek@22 |
