Feature: Test rest app

  Scenario: test rest
    Given uri http://localhost:8080
    When go to /rs/json/products
    Then get products
