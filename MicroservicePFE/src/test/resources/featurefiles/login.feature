Feature: Login


  Scenario: Successful Login to the page

    Given I open Chrome browser

    When I navigate to login page

    And I provide username as "12378888" and password as "9.XIb]R?FlxW"

    And I click on login button

    Then I navigate to dashboard page

  Scenario: Failed Login to the page

    Given I open Chrome browser

    When I navigate to login page

    And I provide username as "000000" and password as "9.XIb]R?FlxW"

    And I click on login button

    Then Error Message

