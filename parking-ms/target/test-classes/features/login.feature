Feature: feature to test file which response at parking data

  Scenario: should be change field on true in test file after parking
    Given initialize file with data
    When create instance service class
    And execute method to take slot
    Then field in file should be change on true

  Scenario: if free slot present in test file then should be return true after checking
    Given initialize file with data
    When create instance service class
    And execute method for chek places in file
    Then return true if slot is free

  Scenario: if free slot taken in test file then should be return false after checking
    Given initialize file with data
    When create instance service class
    And execute method for chek places in file
    Then return false if slot is busy

