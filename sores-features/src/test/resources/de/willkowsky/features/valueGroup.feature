Feature: Wertegruppe

  Scenario: Eine vollständig befüllte Reihe ist gültig, wobei alle Ziffern nur einmal enthalten sind
    Given ist eine Wertegruppe
    When sie mit den Ziffern 1 bis 9 befüllt ist,
    Then dann ist sie gültig

  Scenario: Eine unvollständig befüllte Reihe ist ungültig
    Given ist eine Wertegruppe
    When sie mit den Ziffern 1 und 9 befüllt ist,
    Then dann ist sie ungültig

  Scenario: Ein Wertegruppe ist mit 8 Ziffern von 1 bis 9 befüllt
    Given ist eine Wertegruppe
    When sie ausser der 5 mit den Ziffern von 1 bis 9 befüllt ist,
    Then dann ist 5 der fehlende Wert


