Tic Tac Toe mit einem CPU Gegner, war ein kleines Projekt, welches ich im 1. Semester Angewandte Informatik angehen wollte.
Ich habe bereits die absoluten Basics von Java gelernt und hier angewendet.

Die Erstellung dieses Programms hat sehr lange gedauert (ca. 15 Stunden) aber ist nun völlig funktionsfähig.

Der Plan ist wie folgt: 
1. Spieler 1 (Mensch) macht den ersten Move und setzt 1 Feld auf "0"
2. Spieler 2 (Computer) reagiert auf den Zustand des Spielfelds entsprechend und setzt, falls er nicht blocken kann, ein "X" auf ein zufälliges Feld,
   welches nicht bereits belegt ist.
3. Spieler 1 (Mensch) macht einen weiteren Zug.
4. Spieler 2 (Computer) erkennt eine WinCondition(falls eine besteht) und blockiert den möglichen Sieg von Spieler 1
   Falls der Computer eine eigene WinCondition hat, setzt er die Reihe zu Ende und gewinnt

Bisher sind keine Schwierigkeiten im Testing aufgetreten.
Die Implementierung von manchen Methoden sind vermutlich sehr umständlich und eventuell unnötig und manchen Code kann man bestimmt kürzer fassen, sofern man weiß wie.
Leider empfand ich die Verkürzung der einzelnen Code-Passagen als unnötig, da es viel Arbeit bedeutet und ohnehin schon funktioniert.
  -> Ziel sollte vorerst nur sein ein funktionierendes TicTacToe-Programm zu coden.
  -> Ich habe innerhalb des Programms meine Art und Weise geändert wie ich Abfragungen vornehmen, da sie mir als leichter und besser vorkamen.

Weitere Features, welche mir einfallen würden sind:
   - Anstatt random ein Feld zu besetzen, sofern keine WinCondition vorliegt, könnte man versuchen ein WinCOndition zu provozieren, indem man 0 herausfindet und X sich nicht bereits in der WinCondition befindet.
   - Einen Round-Counter, damit man best of 3 oder best of 5 spielen kann.
   - Eventuell von einer ASCII-Darstellung abweichen und ein visuelles Programm erstellen mit den entsprechenden Algorithmen.
   - Die Felder mit den Indexes beschriften, damit man nicht abzählen muss welchen Index welches Feld hat.

Vielleicht hilft es ja dem ein oder anderen einen anderen Blickwinkel auf anfängerliche Programmierung zu werfen, oder einfach um zu sehen wie schlecht das Programm eigentlich geschrieben ist ^^
// Hier sind einmal die Skizzen die ich dafür benutzt habe
<img width="1163" alt="image" src="https://github.com/user-attachments/assets/000ff41c-b3b6-4446-9650-2512cbb83125" />

SimplyTryce
