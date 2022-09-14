import java.util.HashMap;

public class NumeralConverter {
    private static HashMap<String, Integer> romanNumerals;

    public NumeralConverter() {
        romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);
        romanNumerals.put("XX", 20);
        romanNumerals.put("XXX", 30);
        romanNumerals.put("XL", 40);
        romanNumerals.put("L", 50);
        romanNumerals.put("LX", 60);
        romanNumerals.put("LXX", 70);
        romanNumerals.put("LXXX", 80);
        romanNumerals.put("XC", 90);
        romanNumerals.put("C", 100);
    }

    public int getArabicFromRoman(String s) {
        int result = -1;

        if (romanNumerals.containsKey(s)) {
            result = romanNumerals.get(s);
        }

        return result;
    }

    public String getRomanFromArabic(int i) {
        StringBuilder builder = new StringBuilder();

        String arabicNumeral = String.valueOf(i);

        if (arabicNumeral.length() == 1 || arabicNumeral.length() == 3) {
            builder.append(getRomanNumeral(i));
        } else if (arabicNumeral.length() == 2) {
            builder.append(getRomanNumeral(Character.getNumericValue(arabicNumeral.charAt(0)) * 10));
            if (arabicNumeral.charAt(1) != '0') {
                builder.append(getRomanNumeral(Character.getNumericValue(arabicNumeral.charAt(1))));
            }
        }

        return builder.toString();
    }

    private String getRomanNumeral(int i) {
        String result = null;

        for (String s : romanNumerals.keySet()) {
            if (romanNumerals.get(s) == i) {
                result = s;
                break;
            }
        }

        return result;
    }
}
