import java.util.*;

class Node  {
    int num;
    long weight;
    Node parent;
    public Node(int num, long weight, Node parent){
        this.num = num;
        this.weight = weight;
        this.parent = parent;
    }
}

class Solution {

    static long go(List<Integer>[] edges, int[] a){
        Queue<Node> q = new LinkedList<>();
        Stack<Node> stack = new Stack<>();

        boolean[] check = new boolean[edges.length];
        check[0] = true;
        Node root = new Node(0, a[0], null);
        q.add(root);
        stack.add(root);


        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i=0; i<edges[cur.num].size(); i++){
                int nextNode = edges[cur.num].get(i);
                if(!check[nextNode]){
                    check[nextNode] = true;
                    Node next = new Node(nextNode, a[nextNode], cur);
                    q.add(next);
                    stack.add(next);
                }
            }

        }

        long rtn = 0;

        while(true){
            Node cur = stack.pop();
            if(cur.parent == null)
                break;
            cur.parent.weight += cur.weight;
            rtn += Math.abs(cur.weight);
        }

        return rtn;
    }

    public long solution(int[] a, int[][] edges) {
        long answer = -2;

        // 답이 없는 경우
        long sum = 0;
        boolean allZero = true;
        for(int num : a){
            sum += num;
            if(num != 0)
                allZero = false;
        }


        if(sum != 0)
            return -1;

        // 답이 0인 경우
        if(allZero)
            return 0;

        List<Integer>[] list = new List[a.length];
        for(int i=0; i<a.length; i++)
            list[i] = new ArrayList<>();

        for(int[] edge : edges){
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        // 답이 0이 아닌경우
        // 1. 트리를 만든다.
        // 2. 리프노드부터 돌면서 자신의 값을 0으로 만들고 부모에 대해 반대 연산을 한다.
        answer = go(list, a);

        return answer;
    }
}