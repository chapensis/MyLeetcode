package leetcode;

import java.util.*;

public class Compress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String result = getCompress(input);
            System.out.println(result);
        }
    }

    public static String getCompress(String s) {
        String[] alpha = s.split("[0-9]+");
        String[] number = s.split("[a-zA-Z]+");
        List<MyString> list = new ArrayList<>();
        for (int i = 0; i < alpha.length; i++) {
            list.add(new MyString(Integer.parseInt(number[i + 1]), alpha[i]));
        }
        Collections.sort(list, (o1, o2) -> {
            if (o1.count != o2.count) {
                return o1.count - o2.count;
            } else {
                return o1.str.compareTo(o2.str);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }


    static class MyString {
        int count;
        String str;

        public MyString(int count, String str) {
            this.count = count;
            this.str = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(str);
            }
            return sb.toString();
        }
    }
}
