import java.util.*;

class Node implements Comparable<Node> {
	public int x;
	public int y;
	public int number;
	public Node left;
	public Node right;

	public Node(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
		this.left = null;
		this.right = null;
	}

	@Override
	public int compareTo(Node n) {
		if (this.y == n.y) {
			return this.x - n.x;
		} else {
			return n.y - this.y;
		}
	}

	public void insert(Node n) {
		if (n.x < this.x) {
			if (this.left == null) {
				this.left = n;
			} else {
				this.left.insert(n);
			}
		} else {
			if (this.right == null) {
				this.right = n;
			} else {
				this.right.insert(n);
			}
		}
	}
}

class Solution {

	static void postorder(Node n, ArrayList<Integer> post) {
		if (n == null) {
			return;
		}

		postorder(n.left, post);
		postorder(n.right, post);
		post.add(n.number);
	}

	static void preorder(Node n, ArrayList<Integer> pre) {
		if (n == null) {
			return;
		}

		pre.add(n.number);
		preorder(n.left, pre);
		preorder(n.right, pre);
	}

	static void postorder(Node n) {
		if (n == null) {
			return;
		}
	}

	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < nodeinfo.length; i++) {
			pq.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
		}

		Node root = new Node(pq.peek().x, pq.peek().y, pq.peek().number);
		pq.poll();

		while (pq.size() != 0) {
			root.insert(pq.poll());
		}

		ArrayList<Integer> pre = new ArrayList<>();
		preorder(root, pre);

		ArrayList<Integer> post = new ArrayList<>();
		postorder(root, post);

		for (int i = 0; i < pre.size(); i++) {
			answer[0][i] = pre.get(i);
		}

		for (int i = 0; i < post.size(); i++) {
			answer[1][i] = post.get(i);
		}

		return answer;
	}
}