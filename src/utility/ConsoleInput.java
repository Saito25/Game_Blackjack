package utility;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Ejercicio 3, excepciones.
 * Esta clase sirve para pedir datos por teclado, verificar que los datos sean correctos
 * y si no es así, volver a pedirlos.
 *
 * @author Manuel
 * @version 1.0
 * @since 1.0
 */
public class ConsoleInput {

    private final Scanner keyboard;

    /**
     * Constructor de la clase.
     * Permite la utilización del teclado en los demás métodos.
     * @param keyboard recibe por parámetro la clase teclado
     * @since 1.0
     */
    public ConsoleInput(Scanner keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Limpia el buffer.
     */
    private void cleanInput() {
        keyboard.nextLine();
    }


    /**
     * Retorna un carácter introducido por el usuario.
     * Entra en búcle hasta que se introduzca un valor correcto.
     * @return un carácter
     * @since 1.0
     */
    public char readChar() {

        String string;
        Character letter;

        do {
            // Pide una cadena de texto hasta que solo se introduzca un caracter.
            string = keyboard.nextLine();
            if (string.length() != 1) {
                System.out.println("Has introducido 0 o más de 1 carácter. Vuelve a introducirlo. ");
            }
        } while (string.length() != 1);

        letter = string.charAt(0);

        return letter;
    }

    /**
     * Esta función permite exigirle al usuario que introduzca un caracter específico.
     * @param validCharacters cadena que contiene los posibles valores a devolver.
     * @return un valor dentro de la cadena introducida por parámetro.
     * @see readChar()
     * @since 1.0
     */
    public char readChar(String validCharacters) {

        Character letter;
        int cont = 0;
        boolean condition = false;

        do {
            cont = 0; // Reset cont.
            letter = readChar(); // Llamamos a la función readChar() para obtener un carácter.

            do {
                if (letter == validCharacters.charAt(cont)) {
                    // Comprobamos que el valor introducido coincida con alguno de los dados.
                    condition = true;
                }
                cont++;
            } while (!condition && cont < validCharacters.length());

            if (!condition) {
                System.out.println("No coincide con los carácteres dados, " + validCharacters);
            }

        } while (!condition);

        return letter;
    }

    /**
     * Obliga al usuario a introducir un caracter vocal.
     * @return una vocal mayúscula o minúscula
     * @see readChar(String validCharacters)
     * @since 1.0
     */
    public char readVowel() {

        Character letter;
        String string = "aeiouAEIOU";

        letter = readChar(string);

        return letter;

    }

    /**
     * Obliga al usuario a introducir un caracter dígito.
     * @return una dígito
     * @see readChar()
     * @since 1.0
     */
    public char readDigit() {

        Character letter;
        boolean condition = false;

        do {
            letter = readChar();

            if (Character.isDigit(letter)) {
                // Comprueba que el caracter introducido sea un número.
                condition = true;
            } else {
                System.out.println("No has introducido un número.");

            }
        } while (!condition);

        return letter;
    }

    /**
     * Obliga al usuario a introducir un caracter alfabético en minúscula.
     * @return una letra minúscula
     * @see readChar()
     * @since 1.0
     */
    public char readLowerCase() {

        Character letter;
        boolean condition = false;

        do {
            letter = readChar();
            if (Character.isLowerCase(letter)) {
                // Comprueba que el caracter sea una letra minúscula
                condition = true;
            } else {
                System.out.println("No has introducido una letra minúscula");
            }
        } while (!condition);

        return letter;
    }

    /**
     * Obliga al usuario a introducir un caracter alfabético en mayúsculas.
     * @return una letra mayúsculas
     * @see readChar()
     * @since 1.0
     */
    public char readUpperCase() {

        Character letter;
        boolean condition = false;

        do {
            letter = readChar();
            if (Character.isUpperCase(letter)) {
                // Comprueba que el caracter sea una letra mayúscula
                condition = true;
            } else {
                System.out.println("No has introducido una letra mayúscula");
            }
        } while (!condition);

        return letter;
    }

    /**
     * Exercise1.
     * Esta clase tiene un método recursivo que se usará para
     * obtener una cadena con forma de cruz.
     *
     * @author Manuel
     * @version 1.0
     * @since 1.0
     */
    public String readString() {

        String string;

        string = keyboard.nextLine();

        return string;
    }

    /**
     * Devuelve el valor de un String introducido por teclado inferior al parámetro dado.
     * @param maxLength longitud máxima que puede tener la cadena
     * @return una cadena
     * @see readString()
     * @since 1.0
     */
    public String readString(int maxLength) {

        String string;

        do {
            // Bucle que comprueba que el valor sea menor que el maxLength.
            string = readString();

            if (string.length() > maxLength) {
                System.out.println("La cadena excede el número de caracteres.");
            }

        } while (string.length() > maxLength);

        return string;
    }


    /**
     * Devuelve true o false dependiendo de qué introduzca el usuario
     * por teclado. Si lo introducido es igual al parámetro, es true,
     * en caso contrario, false.
     * @param affirmativeValue carácter que sirve de comparador
     * @return true o false
     * @see readChar()
     * @since 1.0
     */
    public boolean readBooleanUsingChar(char affirmativeValue) {

        Character letter;
        boolean condition = false;

        letter = readChar();
        letter = Character.toLowerCase(letter);
        affirmativeValue = Character.toLowerCase(affirmativeValue);

        if (letter == affirmativeValue) {
            condition = true;
        }

        return condition;
    }

    /**
     * Devuelve true o false dependiendo de qué introduzca el usuario
     * por teclado. Si lo introducido es igual a s o S, es true,
     * en caso contrario, false.
     * @return true o false
     * @see readBooleanUsingChar()
     * @since 1.0
     */
    public boolean readBooleanUsingChar() {

        boolean condition = readBooleanUsingChar('s');

        return condition;
    }

    /**
     * Devuelve true o false dependiendo de qué introduzca el usuario
     * por teclado. Si lo introducido es igual affirmativeValue, es true,
     * en caso contrario, false.
     * @return true o false
     * @param affirmativeValue entero que nos sirve de comparador.
     * @see readInt()
     * @since 1.0
     */
    public boolean readBooleanUsingInt(int affirmativeValue) {

        int n = readInt();
        boolean condition = false;

        if (n == affirmativeValue) {
            condition = true;
        }

        return condition;
    }

    /**
     * Devuelve true o false dependiendo de qué introduzca el usuario
     * por teclado. Si lo introducido es igual a 1, es true,
     * en caso contrario, false.
     * @return true o false
     * @see readInt()
     * @since 1.0
     */
    public boolean readBooleanUsingInt() {

        boolean condition = readBooleanUsingInt(1);

        return condition;
    }

    /**
     * Pide un byte por teclado al usuario, si este no introduce un byte,
     * se le vuelve a pedir.
     * @return un byte
     * @since 1.0
     */
    public byte readByte() {

        byte n = 0;
        boolean condition = false;

        do {
            // Repite el bucle hasta que se introduzca un valor correcto.
            try {
                n = keyboard.nextByte();
                condition = true;
            } catch (InputMismatchException e) {
                // Excepción controlada si se introduce un tipo incorrecto.
                System.out.println("Número incorrecto. Un byte es del rango -128 a 127.");
                condition = false;
            } finally {
                // Limpia el buffer.
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un byte por teclado al usuario, si este no introduce un byte,
     * se le vuelve a pedir. Dicho byte tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el byte.
     * @return un byte
     * @see readByteLessOrEqualThan(byte upperBound)
     * @since 1.0
     */
    public byte readByteLessThan(byte upperBound) {
        byte n = readByteLessOrEqualThan((byte) (upperBound - 1));

        return n;
    }


    /**
     * Pide al usuario un byte y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el byte.
     * @return un byte
     * @see readByte()
     * @version 1.0
     */
    public byte readByteLessOrEqualThan(byte upperBound) {

        byte n = 0;
        boolean condition = false;

        do {
            // Búcle que comprueba que el byte sea menor al parámetro
            n = readByte();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);
            }

        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un byte y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el byte.
     * @return un byte
     * @see readByteGreaterOrEqualThan(byte lowerBound)
     * @version 1.0
     */
    public byte readByteGreaterThan(byte lowerBound) {

        byte n = readByteGreaterOrEqualThan((byte) (lowerBound + 1));

        return n;
    }

    /**
     * Pide al usuario un byte y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el byte.
     * @return un byte
     * @see readByte()
     * @version 1.0
     */
    public byte readByteGreaterOrEqualThan(byte lowerBound) {

        byte n = 0;
        boolean condition = false;

        do {
            n = readByte();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un byte y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el byte.
     * @param upperBound define el tamaño máximo que podrá tener el byte.
     * @return un byte
     * @see readByte()
     * @version 1.0
     */
    public byte readByteInRangeInclusive(byte lowerBound, byte upperBound) {

        byte n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readByte();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un byte y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el byte.
     * @param upperBound define el tamaño máximo que podrá tener el byte, no está incluido.
     * @return un byte
     * @see readByteInRangeInclusive(byte lowerBound, byte upperBound)
     * @version 1.0
     */
    public byte readByteInRangeExclusive(byte lowerBound, byte upperBound) {

        byte n = readByteInRangeInclusive(lowerBound, (byte) (upperBound - 1));

        return n;
    }

    /**
     * Pide un short por teclado al usuario, si este no introduce un short,
     * se le vuelve a pedir.
     * @return un short
     * @since 1.0
     */
    public short readShort() {

        short n = 0;
        boolean condition = false;

        do {
            try {
                n = keyboard.nextShort();
                condition = true;
            } catch (InputMismatchException e) {
                System.out.println("Número incorrecto. Fuera del rango de un short.");
                condition = false;
            } finally {
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un short por teclado al usuario, si este no introduce un short,
     * se le vuelve a pedir. Dicho short tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el short.
     * @return un short
     * @see readShortLessOrEqualThan(short upperBound)
     * @since 1.0
     */
    public short readShortLessThan(Short upperBound) {

        short n = readShortLessOrEqualThan((short) (upperBound - 1));

        return n;
    }

    /**
     * Pide al usuario un short y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el short.
     * @return un short
     * @see readShort()
     * @version 1.0
     */
    public short readShortLessOrEqualThan(short upperBound) {

        short n = 0;
        boolean condition = false;

        do {
            n = readShort();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un short y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el short.
     * @return un short
     * @see readShort()
     * @see readShortGreaterOrEqualThan(short lowerBound)
     * @version 1.0
     */
    public short readShortGreaterThan(short lowerBound) {

        short n = readShortGreaterOrEqualThan((short) (lowerBound + 1));

        return n;
    }

    /**
     * Pide al usuario un short y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el short.
     * @return un short
     * @see readShort()
     * @version 1.0
     */
    public short readShortGreaterOrEqualThan(short lowerBound) {

        short n = 0;
        boolean condition = false;

        do {
            n = readShort();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un short y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el short.
     * @param upperBound define el tamaño máximo que podrá tener el short.
     * @return un short
     * @see readShort()
     * @version 1.0
     */
    public short readShortInRangeInclusive(short lowerBound, short upperBound) {

        short n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readShort();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un short y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el short.
     * @param upperBound define el tamaño máximo que podrá tener el short, no está incluido.
     * @return un short
     * @see readShortInRangeInclusive(short lowerBound, short upperBound)
     * @version 1.0
     */
    public short readShortInRangeExclusive(short lowerBound, short upperBound) {

        short n = readShortInRangeInclusive(lowerBound, (short) (upperBound - 1));

        return n;
    }

    /**
     * Pide un int por teclado al usuario, si este no introduce un int,
     * se le vuelve a pedir.
     * @return un int
     * @since 1.0
     */
    public int readInt() {

        int n = 0;
        boolean condition = false;

        do {
            try {
                n = keyboard.nextInt();
                condition = true;
            } catch (InputMismatchException e) {
                System.out.println("Número incorrecto. Fuera del rango de un int.");
                condition = false;
            } finally {
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un int por teclado al usuario, si este no introduce un int,
     * se le vuelve a pedir. Dicho int tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el int.
     * @return un int
     * @see readIntLessOrEqualThan(int upperBound)
     * @since 1.0
     */
    public int readIntLessThan(int upperBound) {
        // Devuelve un int menor que el introducido por parámetro.

        int n = readIntLessOrEqualThan(upperBound - 1);

        return n;
    }

    /**
     * Pide al usuario un int y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el int.
     * @return un int
     * @see readInt()
     * @version 1.0
     */
    public int readIntLessOrEqualThan(int upperBound) {
        // Devuelve un int menor o igual que el introducido por parámetro.

        int n = 0;
        boolean condition = false;

        do {
            n = readInt();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un int y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el int.
     * @return un int
     * @see readInt()
     * @see readIntGreaterOrEqualThan(int lowerBound)
     * @version 1.0
     */
    public int readIntGreaterThan(int lowerBound) {
        // Devuelve un int mayor que el introducido por parámetro.

        int n = readIntGreaterOrEqualThan(lowerBound + 1);

        return n;
    }

    /**
     * Pide al usuario un int y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el int.
     * @return un int
     * @see readInt()
     * @version 1.0
     */
    public int readIntGreaterOrEqualThan(int lowerBound) {
        // Devuelve un int mayor o igual al número introducido.

        int n = 0;
        boolean condition = false;

        do {
            n = readInt();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un int y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el int.
     * @param upperBound define el tamaño máximo que podrá tener el int.
     * @return un int
     * @see readInt()
     * @version 1.0
     */
    public int readIntInRangeInclusive(int lowerBound, int upperBound) {

        int n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readInt();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un int y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el int.
     * @param upperBound define el tamaño máximo que podrá tener el int, no está incluido.
     * @return un int
     * @see readIntInRangeInclusive(int lowerBound, int upperBound)
     * @version 1.0
     */
    public int readIntInRangeExclusive(int lowerBound, int upperBound) {
        // Devuelve un int dentro de un rango, el mayor excluido.

        int n = readIntInRangeInclusive(lowerBound, upperBound - 1);

        return n;
    }

    /**
     * Pide un long por teclado al usuario, si este no introduce un long,
     * se le vuelve a pedir.
     * @return un long
     * @since 1.0
     */
    public long readLong() {
        // Retorna un long introducido por el usuario.

        long n = 0;
        boolean condition = false;

        do {
            try {
                n = keyboard.nextLong();
                condition = true;
            } catch (InputMismatchException e) {
                System.out.printf("Número incorrecto. Fuera del rango de un long.");
                condition = false;
            } finally {
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un long por teclado al usuario, si este no introduce un long,
     * se le vuelve a pedir. Dicho long tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el long.
     * @return un long
     * @see readLongLessOrEqualThan(long upperBound)
     * @since 1.0
     */
    public long readLongLessThan(long upperBound) {
        // Devuelve un long menor que el introducido por parámetro.

        long n = readLongLessOrEqualThan(upperBound - 1);

        return n;
    }

    /**
     * Pide al usuario un long y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el long.
     * @return un long
     * @see readLong()
     * @version 1.0
     */
    public long readLongLessOrEqualThan(long upperBound) {
        // Devuelve un long menor o igual que el introducido por parámetro.

        long n = 0;
        boolean condition = false;

        do {
            n = readLong();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un long y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el long.
     * @return un long
     * @see readLong()
     * @see readLongGreaterOrEqualThan(long lowerBound)
     * @version 1.0
     */
    public long readLongGreaterThan(long lowerBound) {
        // Devuelve un long mayor que el introducido por parámetro.

        long n = readLongGreaterOrEqualThan(lowerBound + 1);

        return n;
    }

    /**
     * Pide al usuario un long y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el long.
     * @return un long
     * @see readLong()
     * @version 1.0
     */
    public long readLongGreaterOrEqualThan(long lowerBound) {
        // Devuelve un long mayor o igual al número introducido.

        long n = 0;
        boolean condition = false;

        do {
            n = readLong();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un long y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el long.
     * @param upperBound define el tamaño máximo que podrá tener el long.
     * @return un long
     * @see readLong()
     * @version 1.0
     */
    public long readLongInRangeInclusive(long lowerBound, long upperBound) {

        long n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readLong();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un long y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el long.
     * @param upperBound define el tamaño máximo que podrá tener el long, no está incluido.
     * @return un long
     * @see readLongInRangeInclusive(long lowerBound, long upperBound)
     * @version 1.0
     */
    public long readLongInRangeExclusive(long lowerBound, long upperBound) {
        // Devuelve un long dentro de un rango, el mayor excluido.

        long n = readLongInRangeInclusive(lowerBound, upperBound - 1);

        return n;
    }

    /**
     * Pide un float por teclado al usuario, si este no introduce un float,
     * se le vuelve a pedir.
     * @return un float
     * @since 1.0
     */
    public float readFloat() {
        // Retorna un float introducido por el usuario.

        float n = 0;
        boolean condition = false;

        do {
            try {
                n = keyboard.nextFloat();
                condition = true;
            } catch (InputMismatchException e) {
                System.out.printf("Número incorrecto. Fuera del rango de un float.");
                condition = false;
            } finally {
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un float por teclado al usuario, si este no introduce un float,
     * se le vuelve a pedir. Dicho float tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el float.
     * @return un float
     * @see readFloatLessOrEqualThan(float upperBound)
     * @since 1.0
     */
    public float readFloatLessThan(float upperBound) {
        // Devuelve un float menor que el introducido por parámetro.

        float n = 0;
        boolean condition = false;

        do {
            n = readFloat();

            if (n < upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor o igual al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un float y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el float.
     * @return un float
     * @see readFloat()
     * @version 1.0
     */
    public float readFloatLessOrEqualThan(float upperBound) {
        // Devuelve un float menor o igual que el introducido por parámetro.

        float n = 0;
        boolean condition = false;

        do {
            n = readFloat();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un float y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el float.
     * @return un float
     * @see readFloat()
     * @see readFloatGreaterOrEqualThan(float lowerBound)
     * @version 1.0
     */
    public float readFloatGreaterThan(float lowerBound) {
        // Devuelve un float mayor que el introducido por parámetro.

        float n = 0;
        boolean condition = false;

        do {
            n = readFloat();

            if (n > lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un float y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el float.
     * @return un float
     * @see readFloat()
     * @version 1.0
     */
    public float readFloatGreaterOrEqualThan(float lowerBound) {
        // Devuelve un float mayor o igual al número introducido.

        float n = 0;
        boolean condition = false;

        do {
            n = readFloat();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un float y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el float.
     * @param upperBound define el tamaño máximo que podrá tener el float.
     * @return un float
     * @see readFloat()
     * @version 1.0
     */
    public float readFloatInRangeInclusive(float lowerBound, float upperBound) {

        float n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readFloat();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un float y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el float.
     * @param upperBound define el tamaño máximo que podrá tener el float, no está incluido.
     * @return un float
     * @version 1.0
     */
    public float readFloatInRangeExclusive(float lowerBound, float upperBound) {

        float n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readFloat();

            if (n >= lowerBound && n < upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y menor a %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un double por teclado al usuario, si este no introduce un double,
     * se le vuelve a pedir.
     * @return un double
     * @since 1.0
     */
    public double readDouble() {

        double n = 0;
        boolean condition = false;

        do {
            try {
                n = keyboard.nextDouble();
                condition = true;
            } catch (InputMismatchException e) {
                System.out.printf("Número incorrecto. Fuera del rango de un double.");
                condition = false;
            } finally {
                cleanInput();
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide un double por teclado al usuario, si este no introduce un double,
     * se le vuelve a pedir. Dicho double tiene que ser menor al pasado
     * en el parámetro.
     * @param upperBound define el tamaño máximo que podrá tener el double.
     * @return un double
     * @see readDouble()
     * @since 1.0
     */
    public double readDoubleLessThan(double upperBound) {

        double n = 0;
        boolean condition = false;

        do {
            n = readDouble();

            if (n < upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un double y comprueba que sea menor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param upperBound define el tamaño máximo que podrá tener el double.
     * @return un double
     * @see readDouble()
     * @version 1.0
     */
    public double readDoubleLessOrEqualThan(double upperBound) {

        double n = 0;
        boolean condition = false;

        do {
            n = readDouble();

            if (n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("%d es mayor al número límite, %d\n", n, upperBound);

            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un double y comprueba que sea mayor al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el double.
     * @return un double
     * @see readDouble()
     * @version 1.0
     */
    public double readDoubleGreaterThan(double lowerBound) {

        double n = 0;
        boolean condition = false;

        do {
            n = readDouble();

            if (n > lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un double y comprueba que sea mayor o igual al pasado
     * en el parámetro, en caso contrario, lo vuelve a pedir.
     * @param lowerBound define el tamaño mínimo que podrá tener el double.
     * @return un double
     * @see readDouble()
     * @version 1.0
     */
    public double readDoubleGreaterOrEqualThan(double lowerBound) {

        double n = 0;
        boolean condition = false;

        do {
            n = readDouble();

            if (n >= lowerBound) {
                condition = true;
            } else {
                System.out.printf("%d es menor al número límite, %d\n", n, lowerBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un double y comprueba que esté dentro de un rango dado,
     * ambos incluidos
     * @param lowerBound define el tamaño mínimo que podrá tener el double.
     * @param upperBound define el tamaño máximo que podrá tener el double.
     * @return un double
     * @see readDouble()
     * @version 1.0
     */
    public double readDoubleInRangeInclusive(double lowerBound, double upperBound) {

        double n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readDouble();

            if (n >= lowerBound && n <= upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }

    /**
     * Pide al usuario un double y comprueba que esté dentro de un rango dado,
     * el mayor excluido
     * @param lowerBound define el tamaño mínimo que podrá tener el double.
     * @param upperBound define el tamaño máximo que podrá tener el double, no está incluido.
     * @return un double
     * @see readDouble()
     * @version 1.0
     */
    public double readDoubleInRangeExclusive(double lowerBound, double upperBound) {


        double n = 0, temporaly = upperBound;
        boolean condition = false;

        if (lowerBound > upperBound) {
            // Si lower es mayor que upper se invierten los parámetros.
            upperBound = lowerBound;
            lowerBound = temporaly;
        }

        do {
            n = readDouble();

            if (n >= lowerBound && n < upperBound) {
                condition = true;
            } else {
                System.out.printf("El número no está en el rango entre %d y menor a %d\n", lowerBound, upperBound);
            }
        } while (!condition);

        return n;
    }
}