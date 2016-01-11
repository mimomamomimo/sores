Feature: Ein Wertefeld

  Scenario: Ein Wertefeld kennt seine Wertegruppen 4:4
    Given aus einem Spielfeld das Feld Reihe 4, Spalte 4
    When es nach seinem zugehörigen Gruppen gefragt wird,
    Then dann ist es der 4. Block, die Zeile 4 und die Spalte 4

  Scenario: Ein Wertefeld kennt seine Wertegruppen 7:1
    Given aus einem Spielfeld das Feld Reihe 7, Spalte 1
    When es nach seinem zugehörigen Gruppen gefragt wird,
    Then dann ist es der 6. Block, die Zeile 7 und die Spalte 1

  Scenario: Ein Wertefeld kann seine möglichen Werte bestimmen
    Given aus einem Spielfeld das Feld Reihe 7, Spalte 1
    When das Feld 7, 8 den Wert 3
    And  das Feld 0, 1 den Wert 5
    And  das Feld 6, 0 den Wert 7 hat
    Then sind noch 6 andere Werte als 3,5,7 möglich


