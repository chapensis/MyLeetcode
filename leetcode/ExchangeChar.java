package leetcode;

import java.util.*;

public class ExchangeChar {
    public static void main(String[] args) {
        ExchangeChar e = new ExchangeChar();
        String s = "fqtvkfkt";
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair1 = new ArrayList<>();
        pair1.add(2);
        pair1.add(4);
        List<Integer> pair2 = new ArrayList<>();
        pair2.add(5);
        pair2.add(7);
        List<Integer> pair3 = new ArrayList<>();
        pair3.add(1);
        pair3.add(0);
        List<Integer> pair4 = new ArrayList<>();
        pair4.add(0);
        pair4.add(0);
        List<Integer> pair5 = new ArrayList<>();
        pair5.add(4);
        pair5.add(7);
        List<Integer> pair6 = new ArrayList<>();
        pair6.add(0);
        pair6.add(3);
        List<Integer> pair7 = new ArrayList<>();
        pair7.add(4);
        pair7.add(1);
        List<Integer> pair8 = new ArrayList<>();
        pair8.add(1);
        pair8.add(3);

        pairs.add(pair1);
        pairs.add(pair2);
        pairs.add(pair3);
        pairs.add(pair4);
        pairs.add(pair5);
        pairs.add(pair6);
        pairs.add(pair7);
        pairs.add(pair8);

        System.out.println(e.smallestStringWithSwaps(s, pairs));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs == null || pairs.size() == 0) {
            return s;
        }

        List<HashSet<Integer>> listHashSet = new ArrayList<>();
        while (pairs.size() > 0) {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(pairs.get(0).get(0));
            hashSet.add(pairs.get(0).get(1));
            listHashSet.add(hashSet);
            int count = 0;
            do {
                Iterator<List<Integer>> iterator = pairs.iterator();
                count = hashSet.size();
                while (iterator.hasNext()) {
                    List<Integer> list = iterator.next();
                    if (hashSet.contains(list.get(0)) || hashSet.contains(list.get(1))) {
                        hashSet.add(list.get(0));
                        hashSet.add(list.get(1));
                        iterator.remove();
                    }
                }
            } while (count < hashSet.size());
        }

        char[] charArray = s.toCharArray();
        for (HashSet hashSet : listHashSet) {
            Object[] indexList = hashSet.toArray();
            char[] charList = new char[indexList.length];
            for (int i = 0; i < indexList.length; i++) {
                charList[i] = charArray[(Integer) indexList[i]];
            }

            Arrays.sort(indexList);
            Arrays.sort(charList);

            for (int i = 0; i < indexList.length; i++) {
                charArray[(Integer) indexList[i]] = charList[i];
            }
        }

        return String.valueOf(charArray);
    }
}
