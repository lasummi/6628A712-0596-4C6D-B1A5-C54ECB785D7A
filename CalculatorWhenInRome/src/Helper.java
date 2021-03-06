import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Helper
{
    private LinkedHashMap<String, Integer> romanNumeralMap = new LinkedHashMap<String, Integer>();
    private HashMap<Integer, String> integerMap = new HashMap<Integer, String>();

    Helper(){
        populateRomanNumeralHashMap();
        populateIntegerHashMap();
    }

    private void populateIntegerHashMap() {
        romanNumeralMap.put("M", 1000);
        romanNumeralMap.put("CM", 900);
        romanNumeralMap.put("D", 500);
        romanNumeralMap.put("CD", 400);
        romanNumeralMap.put("C", 100);
        romanNumeralMap.put("XC", 90);
        romanNumeralMap.put("L", 50);
        romanNumeralMap.put("XL", 40);
        romanNumeralMap.put("X", 10);
        romanNumeralMap.put("IX", 9);
        romanNumeralMap.put("V", 5);
        romanNumeralMap.put("IV", 4);
        romanNumeralMap.put("I", 1);
    }

    private void populateRomanNumeralHashMap() {
        integerMap.put(1, "I");
        integerMap.put(4, "IV");
        integerMap.put(5, "V");
        integerMap.put(9, "IX");
        integerMap.put(10, "X");
        integerMap.put(40, "XL");
        integerMap.put(50, "L");
        integerMap.put(90, "XC");
        integerMap.put(100, "C");
        integerMap.put(400, "CD");
        integerMap.put(500, "D");
        integerMap.put(900, "CM");
        integerMap.put(1000, "M");
    }

    int convertToInteger(String romanNumeral) {
        int result = 0;
        if (romanNumeral==null){
            return result;
        }

        String[] splitRoman = romanNumeral.split("");

        for (int i = 1; i < splitRoman.length; i++) {
            if (romanNumeralMap.containsKey(splitRoman[i])) {
                if (i + 1 < splitRoman.length) {
                    if (checkNextRomanIsGreater(romanNumeralMap, splitRoman[i], splitRoman[i + 1])) {
                        result += romanNumeralMap.get(splitRoman[i + 1]) - romanNumeralMap.get(splitRoman[i]);
                        i += 1;
                    } else {
                        result += romanNumeralMap.get(splitRoman[i]);
                    }
                } else {
                    result += romanNumeralMap.get(splitRoman[i]);
                }
            }
        }
        return result;
    }

    String convertToRomanNumeral(Integer integer) {
        String romanNumber = "";
        if(integer < 0){
            romanNumber = "-";
            integer = Math.abs(integer);
        }
        RomanResult result = new RomanResult(integer, romanNumber);

        for (Map.Entry<String, Integer> entry : romanNumeralMap.entrySet()) {
            result = IterateThrough(entry.getValue(), result.getInputInteger(), result.getRomanNumber());
        }
        return result.getRomanNumber();

    }

    private RomanResult IterateThrough(int c, int inputInteger, String romanNumber) {
        while (inputInteger / c >= 1.0) {
            romanNumber += integerMap.get(c);
            inputInteger -= c;
        }
        return new RomanResult(inputInteger, romanNumber);
    }

    private static boolean checkNextRomanIsGreater(HashMap<String, Integer> romanNumeralMap, String s, String s1) {
        return romanNumeralMap.get(s1) > romanNumeralMap.get(s);
    }

}
