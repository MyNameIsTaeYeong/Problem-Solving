import java.util.*;

class Card implements Comparable<Card> {
	public int x;
	public int y;
	public int num;

	public Card(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}

	@Override
	public int compareTo(Card c) {
		if (this.num == c.num) {
			return -1;
		} else {
			return this.num - c.num;
		}
	}
}

class Node {
	public int x;
	public int y;
	public int dist;

	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

class Solution {

	static int[][] g_board;
	static int g_r;
	static int g_c;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int ans = 1000000000;

	static Node ctrl_next(int[][] board, int direction, int x, int y, int dist) {
		if (direction == 0) {
			if (x == 0) {
				return null;
			}
			x--;
			while (x > 0 && board[x][y] == 0) {
				x--;
			}

		} else if (direction == 1) {
			if (y == 3) {
				return null;
			}
			y++;
			while (y < 3 && board[x][y] == 0) {
				y++;
			}

		} else if (direction == 2) {
			if (x == 3) {
				return null;
			}
			x++;
			while (x < 3 && board[x][y] == 0) {
				x++;
			}

		} else {
			if (y == 0) {
				return null;
			}
			y--;
			while (y > 0 && board[x][y] == 0) {
				y--;
			}

		}

		return new Node(x, y, dist + 1);
	}

	static int bfs(int[][] board, int start_x, int start_y, int end_x, int end_y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start_x, start_y, 0));

		while (q.size() != 0) {
			Node node = q.poll();

			if (node.x == end_x && node.y == end_y) {
				// 뒤집어서 리턴
				return node.dist + 1;
			}

			for (int i = 0; i < 4; i++) {
				int next_x = node.x + dx[i];
				int next_y = node.y + dy[i];

				if (next_x < 4 && next_x >= 0 && next_y < 4 && next_y >= 0) {
					// System.out.print(next_x+","+next_y+","+(node.dist+1)+" ");
					q.add(new Node(next_x, next_y, node.dist + 1));
				}
			}

			for (int i = 0; i < 4; i++) {
				Node next_node = ctrl_next(board, i, node.x, node.y, node.dist);
				if (next_node != null) {
					q.add(next_node);
				}
			}

		}

		return 0;
	}

	static void find_min_distance(Deque<Card> permutation) {

		int[][] board = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = g_board[i][j];
			}
		}

		Iterator<Card> it = permutation.iterator();
		Card start = new Card(g_r, g_c, 0);

		int dist = 0;
		int flip = 0;
		while (it.hasNext()) {
			Card end = it.next();
			dist += bfs(board, start.x, start.y, end.x, end.y);
			// System.out.print("("+start.x+","+start.y+")"+"("+end.x+","+end.y+")"+dist+"
			// ");
			flip++;
			if (flip == 2) {
				flip = 0;
				board[start.x][start.y] = 0;
				board[end.x][end.y] = 0;
			}

			start = end;
		}

		if (dist < ans) {
			ans = dist;
			// if(ans == 14){
			// it = permutation.iterator();
			// while(it.hasNext()){
			// Card c = it.next();
			// //System.out.print("("+c.x+","+c.y+","+c.num+")" +" ");
			// }
			// }
		}
	}

	static void make_permutations(ArrayList<Card> list, Deque<Card> permutation, boolean[] check) {
		if (permutation.size() == list.size()) {
			find_min_distance(permutation);
			return;
		}

		for (int i = 0; i < list.size(); i += 2) {
			if (!check[i / 2]) {
				check[i / 2] = true;
				permutation.addLast(list.get(i));
				permutation.addLast(list.get(i + 1));
				make_permutations(list, permutation, check);
				permutation.removeLast();
				permutation.removeLast();

				permutation.addLast(list.get(i + 1));
				permutation.addLast(list.get(i));
				make_permutations(list, permutation, check);
				permutation.removeLast();
				permutation.removeLast();
				check[i / 2] = false;
			}
		}
	}

	public int solution(int[][] board, int r, int c) {
		int answer = 0;

		g_board = board;
		g_r = r;
		g_c = c;

		ArrayList<Card> list = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					list.add(new Card(i, j, board[i][j]));
				}
			}
		}

		Collections.sort(list);

		make_permutations(list, new LinkedList<>(), new boolean[list.size() / 2]);

		// Deque<Card> dq = new LinkedList<>();
		// dq.add(new Card(0,0,3));
		// dq.add(new Card(3,3,3));
		// dq.add(new Card(3,0,2));
		// dq.add(new Card(0,3,2));
		// dq.add(new Card(1,2,1));
		// dq.add(new Card(2,1,1));
		// find_min_distance(dq);

		// System.out.print(bfs(board, 0, 3, 1,2));

		answer = ans;

		return answer;
	}
}