Feature: Repondre


  Scenario: Repondre Reclamation

    Given I open Chrome browserR

    When I navigate to login pageR

    And I provide username as "12378888" and password as "9.XIb]R?FlxW" R

    And I click on login buttonR

    Then I navigate to reclamations pageR

    When I click on details buttonR
