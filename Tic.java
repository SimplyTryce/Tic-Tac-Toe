import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Tic {
    // Gibt das Spielfeld in der Konsole aus
    public static void printField(FieldParts[] list) {
        System.out.println("-------------");
        for (int repeat = 0; repeat < 8; repeat += 3) {
            System.out.println("| " + list[repeat].getContents() + " | " + list[repeat+1].getContents() + " | " + list[repeat + 2].getContents() + " |");
            System.out.println("-------------");
        }
    }

    // Liest das Spielfeld und speichert einen tempHitMemory, sofern 2 Hits in einer Reihe gefunden wurden
    // Wenn aber 3 Hits gefunden wurden, übergeht er das Statement von den 2 Hits und gibt einen Array aus mit True (Size = 1)
    // Ansonsten einen Array mit True und dem Index der Reihe (Size = 2)
    // Index 0 = Reihe 1 ; Index 3 = Reihe 2 ; Index 6 = Reihe 3;
    public static Object[] checkWinConditionsRows(FieldParts[] list) {
        int tempHitCounter = 0; int hitIndex = 0; boolean tempHitMemory = false;
        for (int index = 0; index < list.length; index += 3) {
            if (list[index].getContents().equals("0")) {
                tempHitCounter++;
            }
            if (list[index + 1].getContents().equals("0")) {
                tempHitCounter++;
                if (tempHitCounter == 2){
                    hitIndex = index;
                    tempHitMemory = true;
                }
            }
            if (list[index + 2].getContents().equals("0")) {
                tempHitCounter++;
                if (tempHitCounter == 2){
                    hitIndex = index;
                    tempHitMemory = true;
                }
            }
            if (tempHitCounter == 3) {
                break;
            }
            if (list[index].getContents().equals("X") ||
                    list[index +1].getContents().equals("X") ||
                    list[index+2].getContents().equals("X")) {
                tempHitMemory = false;
            }
            if (tempHitMemory) {
                break;
            }
            tempHitCounter = 0;
        }
        if (tempHitCounter == 3) {
            Object[] win = new Object[1];
            win[0] = true;
            return win;
        }
        if (tempHitMemory) {
            Object[] memoryRows = new Object[2];
            memoryRows[0] = true;
            memoryRows[1] = hitIndex;
            return memoryRows;
        }
        else {
            Object[] memoryRows = new Object[1];
            memoryRows[0] = false;
            return memoryRows;
        }
    }

    // Gleiches Prinzip wie bei Rows
    // Index 0 = Spalte 1; Index 1 = Spalte 2; Index 2 = Spalte 3
    public static Object[] checkWinConditionsColumns(FieldParts[] list) {
        int tempHitCounter = 0; int hitIndex = 0; boolean tempHitMemory = false;
        for (int index = 0; index < 3; index ++) {
            if (list[index].getContents().equals("0")) {
                tempHitCounter++;
            }
            if (list[index + 3].getContents().equals("0")) {
                tempHitCounter++;
                if (tempHitCounter == 2){
                    hitIndex = index;
                    tempHitMemory = true;
                }
            }
            if (list[index + 6].getContents().equals("0")) {
                tempHitCounter++;
                if (tempHitCounter == 2){
                    hitIndex = index;
                    tempHitMemory = true;
                }
            }
            if (tempHitCounter == 3) {
                break;
            }
            if (list[index].getContents().equals("X") ||
                    list[index + 3].getContents().equals("X") ||
                    list[index+ 6].getContents().equals("X")) {
                tempHitMemory = false;
            }
            if (tempHitMemory) {
                break;
            }
            tempHitCounter = 0;
        }
        if (tempHitCounter == 3) {
            Object[] winPlayer = new Object[1];
            winPlayer[0] = true;
            return winPlayer;
        }
        if (tempHitMemory) {
            Object[] memoryColumns = new Object[2];
            memoryColumns[0] = true;
            memoryColumns[1] = hitIndex;
            return memoryColumns;
        }
        else {
            Object[] memoryColumns = new Object[1];
            memoryColumns[0] = false;
            return memoryColumns;
        }
    }

    // Index 0 ist nur für die Gleichmäßigkeit in der Array size bei 2er Hits
    public static Object[] checkWinConditionsDiagonalTopLeft(FieldParts[] list) {
        int tempHitCounter = 0;
        if (list[0].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (list[4].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (list[8].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (tempHitCounter == 3) {
            Object[] win = new Object[1];
            win[0] = true;
            return win;
        }
        if (list[0].getContents().equals("X") ||
                list[4].getContents().equals("X") ||
                list[8].getContents().equals("X")) {
            tempHitCounter = 0;
        }

        if (tempHitCounter == 2) {
            Object[] memoryDiagonalTopLeft = new Object[2];
            memoryDiagonalTopLeft[0] = true;
            memoryDiagonalTopLeft[1] = 0;
            return memoryDiagonalTopLeft;
        }
        else {
            Object[] memoryDiagonalTopLeft = new Object[1];
            memoryDiagonalTopLeft[0] = false;
            return memoryDiagonalTopLeft;
        }
    }

    // Identisch zu checkWinConditionsDiagonalTopLeft
    public static Object[] checkWinConditionsDiagonalTopRight(FieldParts[] list) {
        int tempHitCounter = 0;
        if (list[2].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (list[4].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (list[6].getContents().equals("0")) {
            tempHitCounter++;
        }
        if (tempHitCounter == 3) {
            Object[] win = new Object[1];
            win[0] = true;
            return win;
        }
        if (list[2].getContents().equals("X") ||
                list[4].getContents().equals("X") ||
                list[6].getContents().equals("X")) {
            tempHitCounter = 0;
        }
        if (tempHitCounter == 2) {
            Object[] memoryDiagonalTopRight = new Object[2];
            memoryDiagonalTopRight[0] = true;
            memoryDiagonalTopRight[1] = 0;
            return memoryDiagonalTopRight;
        }
        else {
            Object[] memoryDiagonalTopRight = new Object[1];
            memoryDiagonalTopRight[0] = false;
            return memoryDiagonalTopRight;
        }
    }

    // Liest das Spielfeld und gibt eine ArrayList aus mit den Indexes der mit "X" gefüllten Felder
    public static ArrayList<Integer> checkPlacedFieldsX(FieldParts[] list) {
        ArrayList<Integer> filledFieldswithX = new ArrayList<>();
        for (int length = 0; length < list.length; length++) {
            if (list[length].getContents().equals("X")) {
                filledFieldswithX.add(length);
            }
        }
        return filledFieldswithX;
    }

    // Liest das Spielfeld aus und gibt eine ArrayList aus mit den Indexes aller gefüllten Felder
    public static ArrayList<Integer> checkPlacedFields(FieldParts[] list) {
        ArrayList<Integer> filledFields = new ArrayList<>();
        for (int length = 0; length < list.length; length++) {
            if (!list[length].getContents().isBlank()) {
                filledFields.add(length);
            }
        }
        return filledFields;
    }

    // Checkt 2 Arrays, ob die Inhalte von array mit array1 übereinstimmen (Auch wenn in array weitere Elemente sind)
    public static boolean checkArrays(ArrayList<Integer> array, int[] array1) {
        int hitCounter = 0;
        for (int counter = 0; counter < array.size(); counter++) {
            if (array.get(counter) == array1[0] || array.get(counter) == array1[1] || array.get(counter) == array1[2]) {
                hitCounter++;
            }
            if (hitCounter == 3){
                break;
            }
        }
        if (hitCounter == 3) {
            return true;
        }
        else {
            hitCounter = 0;
            return false;
        }
    }

    // Checkt 2 Arrays und ob mindestens 2 Übereinstimmungen gefunden wurde und gibt einen Array zurück mit entweder size 1 = false oder size 2 = (true, hitIndicator)
    // Der Indicator gibt eine Referenz auf den Array bei dem die Übereinstimmung gefunden wurde, um entsprechend zu reagieren
    public static Object[] checkArrayfor2Hits(ArrayList<Integer> placedFieldsArrayListX, int[][] multiArray, FieldParts[] list) {
        int hitCounter = 0;
        int hitIndex = 0;
        boolean hitMemory = false;
        // Läuft 8 mal ab! Weil es insgesamt 8 WinConditions gibt
        topper:
        for (int i = 0; i < 8; i++) {
            for (int counter = 0; counter < placedFieldsArrayListX.size(); counter++) {
                if (placedFieldsArrayListX.get(counter) == multiArray[i][0] || placedFieldsArrayListX.get(counter) == multiArray[i][1] || placedFieldsArrayListX.get(counter) == multiArray[i][2])
                {
                    hitCounter++;
                }
                if (list[multiArray[i][0]].getContents().equals("X") ||
                        list[multiArray[i][1]].getContents().equals("X") ||
                        list[multiArray[i][2]].getContents().equals("X")) {
                    hitCounter = 0;
                }
                if (hitCounter == 2) {
                    hitIndex = i;
                    hitMemory = true;
                }
                if (hitMemory) {
                    break topper;
                }
            }
            hitCounter = 0;
        }
        if (hitMemory) {
            Object[] returnArray = new Object[2];
            returnArray[0] = true;
            returnArray[1] = hitIndex;
            return returnArray;
        }
        else {
            Object[] returnArray = new Object[1];
            returnArray[0] = false;
            return returnArray;
        }
    }

    // Liest das Spielfeld und gibt einen boolean aus, falls das Spiel gewonnen ist, vom Computer
    public static boolean checkComputerWin(FieldParts[] list) {
        boolean didComputerWin = false;
        int[] row1 =        {0,1,2}; int[] row2 =       {3,4,5}; int[] row3 =    {6,7,8};
        int[] column1 =     {0,3,6}; int[] column2 =    {1,4,7}; int[] column3 = {2,5,8};
        int[] diagonal1 =   {0,4,8}; int[] diagonal2 =  {2,4,6};
        if (checkArrays(checkPlacedFieldsX(list), row1) || checkArrays(checkPlacedFieldsX(list), row2) || checkArrays(checkPlacedFieldsX(list), row3) ||  checkArrays(checkPlacedFieldsX(list), column1) || checkArrays(checkPlacedFieldsX(list), column2) || checkArrays(checkPlacedFieldsX(list), column3) || checkArrays(checkPlacedFieldsX(list), diagonal1) || checkArrays(checkPlacedFieldsX(list), diagonal2)){
            printField(list);
            System.out.println("The Computer won the game...\nBetter Luck next time!");
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Klassen erstellen
        Scanner input = new Scanner(System.in); FieldParts[] field = new FieldParts[9]; Random random = new Random();

        // Alle wichtigen Variablen, welche über die Schleifen hinauszählen
        int hitReactionIndicator = -1;
        boolean hitReaction = false; boolean skip = false; boolean noHitReaction = false; boolean gameStatus = true; boolean playerTurn = true; boolean superSkip = false;
        int[][] allWinConditions = {    {0,1,2},
                                        {3,4,5},
                                        {6,7,8},
                                        {0,3,6},
                                        {1,4,7},
                                        {2,5,8},
                                        {0,4,8},
                                        {2,4,6}     };

        // Array Liste mit FieldParts füllen
        for (int length = 0; length < field.length; length++) {
            field[length] = new FieldParts();
        }

        // Start des Spiels
        System.out.println("Welcome to the mighty game of Tic Tac Toe!\nYou have chosen to play against the machine hahaha\nI wish you the best of Luck! May the best player win!");
        printField(field);

        while (gameStatus) {

                // Beginn des Benutzers
                while (playerTurn) {
                    System.out.println("Please choose a field. Numbered between 0 - 8");
                    int in = -1;

                    // try catch statement, falls kein Integer eingegeben wird
                    try {
                        in = input.nextInt();
                    } catch (InputMismatchException mismatchException) {
                        System.out.println("Your input was invalid please try again!");
                        input.nextLine();
                        continue;
                    }

                    // Überprüfung des Integers (Ist der Integer in der richtigen Range? und ist das Feld bereits belegt?)
                    // Wenn ja, setze ein 0 und beende den Zug, wenn nicht Wiederholung der Schleife
                    if (in >= 0 && in < 9 && !checkPlacedFields(field).contains(in)) {
                        field[in].setContents("0");
                        printField(field);

                        // Überprüfung ob ein Win vorliegt, wenn ja spiel beenden, wenn nicht Zug beenden
                        if (checkWinConditionsColumns(field).length == 1 && checkWinConditionsColumns(field)[0].equals(true)) {
                            gameStatus = false;
                            System.out.println("Player 0 won the game");
                            break;
                        }
                        if (checkWinConditionsRows(field).length == 1 && checkWinConditionsRows(field)[0].equals(true)) {
                            gameStatus = false;
                            System.out.println("Player 0 won the game");
                            break;
                        }
                        if (checkWinConditionsDiagonalTopLeft(field).length == 1 && checkWinConditionsDiagonalTopLeft(field)[0].equals(true)) {
                            gameStatus = false;
                            System.out.println("Plyer 0 won the game");
                            break;
                        }
                        if (checkWinConditionsDiagonalTopRight(field).length == 1 && checkWinConditionsDiagonalTopRight(field)[0].equals(true)) {
                            gameStatus = false;
                            System.out.println("Player 0 won the game");
                        }
                        playerTurn = false;
                    } else {
                        System.out.println("Your input was invalid, please try again!");
                    }
                }

                // Wenn nicht alle Felder des Spielfelds bereits einen Inhalt haben, dann wird der untenstehende Code ausgeführt.
                // Parameter wann das Spiel weder gewonnen noch verloren ist (Tie)
                if (checkPlacedFields(field).size() < 9) {

                // Beginn des Computers
                while (!playerTurn) {
                    System.out.println("The Computer is choosing a field now");

                    // if Methode ob der CPU gewinnen kann, falls er eine winCondition hat, dann platziere X
                    if (checkArrayfor2Hits(checkPlacedFieldsX(field),allWinConditions,field)[0].equals(true)) {
                        int hitIndicator = (int)checkArrayfor2Hits(checkPlacedFieldsX(field),allWinConditions, field)[1];
                        switch (hitIndicator) {
                            //row1
                            case(0): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[0][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(1): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[1][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(2): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[2][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(3): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[3][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(4): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[4][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(5): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[5][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(6): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[6][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                            case(7): {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = allWinConditions[7][random.nextInt(0, 3)];
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        valid = true;
                                        superSkip = true;
                                    }
                                }
                                break;
                            }
                        }
                    }

                    if (!superSkip) {

                        // Überprüfen des Spielfelds, ob winCond oder win vorliegt
                        if (checkWinConditionsColumns(field).length == 2) {
                            hitReaction = true;
                            hitReactionIndicator = 0;
                            //System.out.println("Win Condition column realized");
                        }   // 0
                        if (checkWinConditionsRows(field).length == 2) {
                            hitReaction = true;
                            hitReactionIndicator = 1;
                            //System.out.println("Win Condition row realized");
                        }   // 1
                        if (checkWinConditionsDiagonalTopLeft(field).length == 2) {
                            hitReaction = true;
                            hitReactionIndicator = 2;
                            //System.out.println("Win Condition diagonal top left realized");
                        }   // 2
                        if (checkWinConditionsDiagonalTopRight(field).length == 2) {
                            hitReaction = true;
                            hitReactionIndicator = 3;
                            //System.out.println("Win Condition diagonal top right realized");
                        }   // 3

                        // Falls eine winCond erkannt wird, wird hitReaction auf True gesetzt und der Index gespeichert
                        if (hitReaction) {

                            // Array erstellen und mit dem return Array der winCondition füllen
                            Object[] conditionArray;

                            // Überprüfung welche WinCondition erkannt wurde (Index ist oben beschrieben)
                            switch (hitReactionIndicator) {
                                case (0): {
                                    conditionArray = checkWinConditionsColumns(field);
                                    int[] column1 = {0, 3, 6};
                                    int[] column2 = {1, 4, 7};
                                    int[] column3 = {2, 5, 8};

                                    // Wenn der Index der erkannten Reihe gleich 0 (1,2) ist, dann fülle diese Reihe, sofern das Feld nicht bereits belegt ist
                                    if (conditionArray[1].equals(0)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = column1[random.nextInt(0, 3)];
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    if (conditionArray[1].equals(1)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = column2[random.nextInt(0, 3)];
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    if (conditionArray[1].equals(2)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = column3[random.nextInt(0, 3)];
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case (1): {
                                    conditionArray = checkWinConditionsRows(field);

                                    // Wenn der Index der erkannten Reihe gleich 0 (3,6) ist, dann fülle diese Reihe, sofern das Feld nicht bereits belegt ist
                                    if (conditionArray[1].equals(0)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = random.nextInt(0, 3);
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    if (conditionArray[1].equals(3)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = random.nextInt(3, 6);
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    if (conditionArray[1].equals(6)) {
                                        boolean valid = false;
                                        while (!valid) {
                                            int randomNumber = random.nextInt(6, 9);
                                            if (!checkPlacedFields(field).contains(randomNumber)) {
                                                field[randomNumber].setContents("X");
                                                playerTurn = true;
                                                hitReaction = false;
                                                skip = true;
                                                valid = true;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case (2): {

                                    // Da es nur einen möglichen Index gibt, wird dieser hier nur für die ArrayList size verwendet
                                    // Wenn die randomNumber nicht bereits ein FieldPart ist, welches einen Inhalt hat, dann platziere X
                                    int[] diagonalTopLeft = {0, 4, 8};
                                    boolean valid = false;
                                    while (!valid) {
                                        int randomNumber = diagonalTopLeft[random.nextInt(0, 3)];
                                        if (!checkPlacedFields(field).contains(randomNumber)) {
                                            field[randomNumber].setContents("X");
                                            playerTurn = true;
                                            hitReaction = false;
                                            skip = true;
                                            valid = true;
                                        }
                                    }
                                    break;
                                }
                                case (3): {

                                    // Siehe case(2)
                                    int[] diagonalTopLeft = {2, 4, 6};
                                    boolean valid = false;
                                    while (!valid) {
                                        int randomNumber = diagonalTopLeft[random.nextInt(0, 3)];
                                        if (!checkPlacedFields(field).contains(randomNumber)) {
                                            field[randomNumber].setContents("X");
                                            playerTurn = true;
                                            hitReaction = false;
                                            skip = true;
                                            valid = true;
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                        // Wenn bereits eine winCondition erfasst wurde, wird dieser Part hier geskippt, damit der Computer nur 1 X platziert.
                        if (!skip) {
                            if (checkWinConditionsColumns(field).length == 1 && checkWinConditionsColumns(field)[0].equals(false) ||
                                    checkWinConditionsRows(field).length == 1 && checkWinConditionsRows(field)[0].equals(false) ||
                                    checkWinConditionsDiagonalTopLeft(field).length == 1 && checkWinConditionsDiagonalTopLeft(field)[0].equals(false) ||
                                    checkWinConditionsDiagonalTopRight(field).length == 1 && checkWinConditionsDiagonalTopRight(field)[0].equals(false)) {
                                noHitReaction = true;
                            }
                            if (noHitReaction) {
                                boolean valid = false;
                                while (!valid) {
                                    int randomNumber = random.nextInt(0, 9);
                                    if (!checkPlacedFields(field).contains(randomNumber)) {
                                        field[randomNumber].setContents("X");
                                        System.out.println("Put One Random");
                                        playerTurn = true;
                                        valid = true;
                                    }
                                }
                            }
                        }
                    }

                    // Zurücksetzen des boolean skip, für die nächste Runde
                    skip = false;

                    // if (Methode zum Ermitteln ob der Computer gewonnen hat) = true ist, dann beende das Spiel
                    if (checkComputerWin(field)) {
                        gameStatus = false;
                        playerTurn = true;
                    }

                    // Kurzer Delay, damit sich das Spiel echter anfühlt, danach wird das Spielfeld nach der Platzierung des X ausgegeben.
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (gameStatus) {
                        printField(field);
                    }
                }
            }
            else {
                System.out.println("It's a Tie!");
                gameStatus = false;
            }
        }
    }
}

// Klasse um die Felder des TicTacToe-Spielfeldes zu füllen (Bringt mehr Freiheit und Zugriff auf die Felder)
class FieldParts {

    // Data field
    String contents = " ";
    static int countingIndex = 0;
    int index;

    // No Arg Constructor
    FieldParts() {
        this.index = countingIndex;
        countingIndex++;
    }

    // Methoden
    // Gibt den Inhalt des FieldParts zurück
    public String getContents() {
            return this.contents;
    }

    // Ändert den Inhalt des FieldParts
    public void setContents(String newContents) {
        this.contents = newContents;
    }
}