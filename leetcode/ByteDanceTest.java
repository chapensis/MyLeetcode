package leetcode;

public class ByteDanceTest {
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // String input = in.nextline();
        System.out.println(getIntegerByIpAddr("1.0.0.1"));
    }

    public static int getIntegerByIpAddr(String ipAddr) {
        String[] ipAddrs = ipAddr.split("\\.");
        int result = 0;
        int[] pow = {24, 16, 8, 0};
        for (int i = 0; i < ipAddrs.length; i++) {
            int num = Integer.parseInt(ipAddrs[i]);
            int index = 0;
            while (num != 0) {
                if ((num & 1) == 1) {
                    result += Math.pow(2, pow[i] + index);
                }
                index++;
                num = num >> 1;
            }
        }
        return result;
    }

    public static int getIntegerByIpAddrLk(String ipAddr) {
        String[] ipAddrs = ipAddr.split("\\.");
        int result = 0;
        int[] pow = {24, 16, 8, 0};
        for (int i = 0; i < ipAddrs.length; i++) {
            int num = Integer.parseInt(ipAddrs[i]);
            result += (num << pow[i]);
        }
        return result;
    }
}
