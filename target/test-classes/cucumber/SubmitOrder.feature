@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given I logged in with username <userName> and password <password>
    When I add product <productName> to cart
    And  I checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | userName                 | password   |  productName  |
      | rajveebhatt178@gmail.com | Vivek@2212 |  ZARA COAT 3   |
     