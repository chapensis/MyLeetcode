package leetcode;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate("(5-(1+(5)))"));
    }

    public int calculate(String s) {
        if (s == null) {
            return 0;
        }

        s = s.replace(" ", "");
        if (s.length() == 0 || "".equals(s)) {
            return 0;
        }

        if (s.indexOf('(') >= 0) {
            int lastLeftBracket = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    lastLeftBracket = i;
                } else if (s.charAt(i) == ')') {
                    String subS = s.substring(lastLeftBracket + 1, i);
                    return calculate(s.substring(0, lastLeftBracket) + calculate(subS) + s.substring(i + 1, s.length()));
                }
            }
        }

        s = s.replace("+-", "-");
        s = s.replace("--", "+");
        String[] addArray = s.split("\\+");
        int result = 0;
        for (int i = 0; i < addArray.length; i++) {
            String addStr = addArray[i];
            result = result + getSubResult(addStr);
        }
        return result;
    }

    public int getSubResult(String s) {
        if(s.startsWith("-"))
        {
            s = "0" + s;
        }
        String[] subArray = s.split("-");
        int result = Integer.parseInt(subArray[0]);
        for (int i = 1; i < subArray.length; i++) {
            result = result - Integer.parseInt(subArray[i]);
        }
        return result;
    }

}
