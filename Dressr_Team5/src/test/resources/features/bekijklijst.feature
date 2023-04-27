Feature: Bekijk Lijst

  Scenario: Bekijk Lijst
    Given I am on the first home page
    When I click on login
    When I enter "charles.clotuche@student.odisee.be" in the username field
    When I enter "Charles2002" in the password field
    When I press on the save button
    When I click on kledingmanager
    When I click on overzicht
    Then I should see a text "LIJST KLEDINGSTUKKEN"
    And I close the browser

  Scenario: Kledingstuk toevoegen
    Given I am on the first home page
    When I click on login
    When I enter "charles.clotuche@student.odisee.be" in the username field
    When I enter "Charles2002" in the password field
    When I press on the save button
    When I click on kledingmanager
    When I click on overzicht
    When I click on kledingstuk toevoegen
    And I enter "ferari" in the naam field
    And I enter "gucci" in the merk field
    And I enter "proper" in the kledingspecificaties field
    And I press on the save button
    And I click on 2 overzicht kleren
    Then I should see a list containing "ferari"
    And I close the browser

  Scenario: Kledingstuk verwijderen
    Given I am on the first home page
    When I click on login
    When I enter "charles.clotuche@student.odisee.be" in the username field
    When I enter "Charles2002" in the password field
    When I press on the save button
    When I click on kledingmanager
    When I click on overzicht
    When I click on ferari
    When I click on verwijderen
    When I click on overzicht
    Then I should not see a list containing "ferari"
    And I close the browser




  Scenario: Kledingstuk aanpassen
    Given I am on the first home page
    When I click on login
    When I enter "charles.clotuche@student.odisee.be" in the username field
    When I enter "Charles2002" in the password field
    When I press on the save button
    When I click on kledingmanager
    When I click on overzicht
    When I click on pull
    When I click on updaten
    When I enter updaten "hehe" in the kledingstukspecificaties field
    When I press on the save button
    Then I should see a list containing "hehe"