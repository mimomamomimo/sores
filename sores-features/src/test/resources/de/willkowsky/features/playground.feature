Feature: Spielfeld

  Scenario: Ein Spielfeld enthält 9 x 9 Wertefelder, die einen Wert bekommen können (1)
    Given ist eine Spielfeld
    When  es 9 x 9 Wertefelder hat
    And   und das Feld in Zeile 1, Spalte 2 auf den Wert 3 gesetzt wird
    Then  dann ist in dem Feld Zeile 1 Spalte 2 den Wert 3 gesetzt

  Scenario: Ein Spielfeld hat einen gültigen Block wenn der erste Block mit den Werten 1 bis 9 befüllt ist
    Given ist ein Spielfeld mit 9 Blöcken
    When  der Block 1 mit den Ziffern 1 bis 9 befüllt ist,
    Then  ist der Block gültig

  Scenario: Ein Spielfeld in dem in jedem Block nur ein Wert fehlt kann gelöst werden
    Given ist ein Spielfeld vom 7.Januar
    When  das Spielfeld in jedem Block nur eine fehlende Ziffer hat
    Then dann kann es gelöst werden

  Scenario: Ein unvollständiges leichtes Spielfeld kann gelöst werden
    Given ist ein Spielfeld vom 9.Januar
    When  das Spielfeld seine Lücken hat
    Then dann kann es gelöst werden

  Scenario: Ein unvollständiges normales Spielfeld kann gelöst werden
    Given ist ein Spielfeld aus "schwierigkeit_normal.txt"
    When  das Spielfeld seine Lücken hat
    Then dann kann es gelöst werden

  Scenario: Ein unvollständiges schweres Spielfeld kann gelöst werden
    Given ist ein Spielfeld aus "schwierigkeit_schwer.txt"
    When  das Spielfeld seine Lücken hat
    Then dann kann es gelöst werden

#  Scenario: Ein unvollständiges extrem schweres Spielfeld kann gelöst werden
#    Given ist ein Spielfeld aus "schwierigkeit_extrem.txt"
#    When  das Spielfeld seine Lücken hat
#    Then dann kann es gelöst werden

  Scenario: Ein unvollständiges schweres Spielfeld (WK 09.01) kann gelöst werden
    Given ist ein Spielfeld vom 11.Januar
    When  das Spielfeld seine Lücken hat
    Then dann kann es gelöst werden

