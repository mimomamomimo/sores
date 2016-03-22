# sores
This is an experiment to develop feature driven code that could solve sudoku riddles.

git clone https://github.com/mimomamomimo/sores.git

cd sores

mvn clean install

(hope, it worked .. )

// creates an example file -> example.txt

java -jar sores-app/target/sores-app-1.0-SNAPSHOT.one-jar.jar example

// and now we try to solve it ..

java -jar sores-app/target/sores-app-1.0-SNAPSHOT.one-jar.jar example.txt


// Die möglichen Lösungen werden in einem Lösungsbaum aufgelistet, dieser sieht so aus:
// für folgendes Spiel

```
001206789
....5....
....4....
.........
.........
.........
.........
.........
.........

// würde so ein Baum aufgebaut werden (die Nullen führen immer zu Verzweigungen im Baum)
root
    finde nächstes ungefülltes Feld (das ist an position 2, 1)
    hole mögliche Werte für ungefülltes Feld, dies sind 3, 4, 5
    node1_1_3 // setze 1,1 auf 3
        finde nächstes ungefülltes Feld (dies ist an Position 2, 1)
        hole mögliche Werte für ungefülltes Feld, dies sind 4, 5
        node2_1_4 // setze 2,1 auf 4
            finde nächstes ungefülltes Feld (das ist an position 5, 1)
            hole mögliche Werte für ungefülltes Feld, kein Wert möglich
            Liste möglicher Werte leer -> keine Lösung möglich, gehe im Baum zur letzten Abzweigung
        node2_1_5 // setze 2,1 auf 5
            finde nächstes ungefülltes Feld (das ist an position 5, 1)
            hole mögliche Werte für ungefülltes Feld, kein Wert möglich
            Liste möglicher Werte leer -> keine Lösung möglich, gehe im Baum zur letzten Abzweigung
        alle Werte für das 2,1 Feld sind invalid, gehe im Baum zur letzten Abzweigung zurück            
    node1_1_2 // setze 1,1 auf den Wert 4
    ...
    node1_1_5 // setze 1,1 auf den Wert 5
        node2_1_4 // setze 2,1 auf den Wert 4
            finde nächstes ungefülltes Feld (das ist an position 5, 1)
            hole mögliche Werte für ungefülltes Feld, dies ist 3
            node 5_1_3 // setze 5,1 auf 3
            finde nächstes ungefülltes Feld, kein Feld ist ungelöst
            Lösung gefunden, Lösungswerte sind 5, 4, 3

541236789
....5....
....4....
.........
.........
.........
.........
.........
.........
```






