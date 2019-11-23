package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 力扣决定给一个刷题团队发LeetCoin作为奖励。同时，为了监控给大家发了多少LeetCoin，力扣有时候也会进行查询。
 * 该刷题团队的管理模式可以用一棵树表示：
 *
 * 团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）；
 * 不存在循环管理的情况，如A管理B，B管理C，C管理A。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-bonus
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangchang
 */
public class LeeCoin {
    public static void main(String[] args) {
        LeeCoin l = new LeeCoin();
        int n = 6;
        int[][] leadership = {{1, 2}, {1, 6}, {2, 3}, {2, 5}, {1, 4}};
        int[][] operations = {{1, 1, 500}, {2, 2, 50}, {3, 1}, {2, 6, 15}, {3, 1}};
        System.out.println(Arrays.toString(l.bonus(n, leadership, operations)));
    }

    int p = (int) Math.pow(10, 9) + 7;

    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        Node[] nodes = new Node[n + 1];
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < leadership.length; i++) {
            int leader = leadership[i][0];
            int follow = leadership[i][1];
            if (nodes[leader] == null) {
                Node leaderNode = new Node();
                nodes[leader] = leaderNode;
            }

            if (nodes[follow] == null) {
                Node followNode = new Node();
                nodes[follow] = followNode;
            }
            nodes[follow].leaderNode = nodes[leader];
            nodes[leader].nodeList.add(nodes[follow]);
            nodes[leader].teamNum = nodes[leader].teamNum + 1;
            tellLeadTeam(nodes[leader]);
        }

        for (int i = 0; i < operations.length; i++) {
            if (operations[i][0] == 1) {
                int bonus = operations[i][2];
                Node curNode = nodes[operations[i][1]];
                addBonus(curNode, bonus);
                tellLeadSumBonus(curNode, bonus);
            } else if (operations[i][0] == 2) {
                int bonus = operations[i][2];
                Node curNode = nodes[operations[i][1]];
                addTeamBonus(curNode, bonus);
                tellLeadSumBonus(curNode, curNode.teamNum * bonus);
            } else if (operations[i][0] == 3) {
                int bonus = (int) (nodes[operations[i][1]].teamBonus % p);
                result.add(bonus);
            }
        }

        int[] bonusResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            bonusResult[i] = result.get(i);
        }
        return bonusResult;
    }

    class Node {
        double teamBonus;
        int teamNum;
        Node leaderNode;
        List<Node> nodeList;

        public Node() {
            this.teamNum = 1;
            this.nodeList = new ArrayList<>();
        }
    }

    public void addTeamBonus(Node node, int bonus) {
        node.teamBonus = node.teamBonus + bonus * node.teamNum;
        for (Node follow : node.nodeList) {
            addTeamBonus(follow, bonus);
        }
    }

    public void addBonus(Node node, int bonus) {
        node.teamBonus = node.teamBonus + bonus;
    }

    public void tellLeadSumBonus(Node node, int bonus) {
        while (node.leaderNode != null) {
            node.leaderNode.teamBonus = node.leaderNode.teamBonus + bonus;
            node = node.leaderNode;
        }
    }

    public void tellLeadTeam(Node node) {
        while (node.leaderNode != null) {
            node.leaderNode.teamNum = node.leaderNode.teamNum + 1;
            node = node.leaderNode;
        }
    }
}
