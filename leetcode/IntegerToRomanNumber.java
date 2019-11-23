package leetcode;

public class IntegerToRomanNumber {
    public static void main(String[] args) {
        IntegerToRomanNumber i = new IntegerToRomanNumber();
        System.out.println(i.intToRoman(4));
    }

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int M = num / 1000;
        result.append(getNumOfString(M, "M"));
        int rest = num % 1000;
        if(rest >= 900)
        {
            result.append(getNumOfString(1, "CM"));
            rest = rest - 900;
        }

        if(rest >= 500)
        {
            result.append(getNumOfString(1, "D"));
            rest = rest - 500;
        }

        if(rest >= 400)
        {
            result.append(getNumOfString(1, "CD"));
            rest = rest - 400;
        }

        int C = rest / 100;
        result.append(getNumOfString(C, "C"));
        rest = rest % 100;
        if(rest >= 90)
        {
            result.append(getNumOfString(1, "XC"));
            rest = rest - 90;
        }

        if(rest >= 50)
        {
            result.append(getNumOfString(1, "L"));
            rest = rest - 50;
        }

        if(rest >= 40)
        {
            result.append(getNumOfString(1, "XL"));
            rest = rest - 40;
        }

        int X = rest / 10;
        result.append(getNumOfString(X, "X"));
        rest = rest % 10;
        if(rest >= 9)
        {
            result.append(getNumOfString(1, "IX"));
            rest = rest - 9;
        }

        if(rest >= 5)
        {
            result.append(getNumOfString(1, "V"));
            rest = rest - 5;
        }

        if(rest >= 4)
        {
            result.append(getNumOfString(1, "IV"));
            rest = rest - 4;
        }

        result.append(getNumOfString(rest, "I"));
        return result.toString();
    }

    public String getNumOfString(int n, String s) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
