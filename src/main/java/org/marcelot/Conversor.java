package org.marcelot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {

    //https://www.guj.com.br/t/metodo-para-converter-numeros-romanos-para-decimal-alguem-sabe/81923/7
    /** Método para traduzir Numeral Romano
     * @param texto String - Texto romano para ser convertido.
     * @return Integer - n inteiro convertido
     * */
    public int traduzirNumeralRomano(String texto) {
        int n = 0;
        int numeralDaDireita = 0;
        for (int i = texto.length() - 1; i >= 0; i--) {
            int valor = (int) traduzirNumeralRomano(texto.charAt(i));
            n += valor * Math.signum(valor + 0.5 - numeralDaDireita);
            numeralDaDireita = valor;
        }
        return n;
    }

    private double traduzirNumeralRomano(char caractere) {
        return Math.floor(Math.pow(10, "IXCM".indexOf(caractere))) + 5 * Math.floor(Math.pow(10, "VLD".indexOf(caractere)));
    }

    //ou
    //https://coding.tools/pt/roman-numerals-to-numbers



    //https://www.baeldung.com/java-convert-roman-arabic
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
    public String traduzirNumeralArabico(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}
