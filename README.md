# sores
This is an experiment to develop feature driven code that could solve sodoku riddles.

git clone https://github.com/mimomamomimo/sores.git

cd sores

mvn clean install

(hope, it worked .. )

// creates an example file -> example.txt

java -jar sores-app/target/sores-app-1.0-SNAPSHOT.one-jar.jar example

// and now we try to solve it ..

java -jar sores-app/target/sores-app-1.0-SNAPSHOT.one-jar.jar example.txt


// Die möglichen Lösungen werden in einem Lösungsbaum aufgelistet, dieser sieht so aus:


für folgendes Spiel würde so ein Baum aufgebaut werden (die 0en führen immer zu verzweigungen im Baum)

```
003406789
---------
---------
---------
---------
---------
---------
---------
---------

root
    node1_1_1
        node2_1_1
            node 5_1_1
            node 5_1_2
            .
            .
            .
            node 5_1_9 // alle Werte für das 5_1 Feld sind invalid, Lösung wurde nicht gefunden
        node2_1_2
            node 5_1_1
            node 5_1_2
            .
            .
            .
            node 5_1_9 // alle Werte für das 5_1 Feld sind invalid, Lösung wurde nicht gefunden
        node2_1_3
            .
            .
            .
        node2_1_9
            node 5_1_1
            node 5_1_2
            .
            .
            .
            node 5_1_9 // alle Werte für das 5_1 Feld sind invalid, Lösung wurde nicht gefunden
    node1_1_2
    .
    .
    .
    node1_1_5
        node2_1_4
            node 5_1_1
            node 5_1_2
            node 5_1_3 // Lösung gefunden

543436789
---------
---------
---------
---------
---------
---------
---------
---------
```




