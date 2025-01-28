
@tag
Feature: Purchase the order from Ecommerce Website
  

Background: 
Given I landed on Ecommerce Page  

  @tag2
  Scenario Outline: Positive Test of submitting the order
    Given I logged in with username  <name> and password <password>
    When I add the product <productName>  to cart 
    And Checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER" message displayed on Confirmation Page.

    Examples: 
      | name  						 | password  |productName |
      | hello1hi@gmail.com | Hellohii1 |BANARSI SAREE |
      