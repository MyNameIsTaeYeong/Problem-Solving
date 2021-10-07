#pragma warning (disable:4996)

#include <cstdio>
#include<vector>
#include<cstring>
using namespace std;

vector<pair<int,int>> island[10001];
bool c[10001];
int start, destination;

bool check(int node, int x) {
	if (c[node])
		return false;
	
	c[node] = true;

	if (node == destination)
		return true;

	for (auto &v : island[node]) {
		int next = v.first;
		int weight = v.second;
		if (weight >= x) {
			if (check(next, x)) {
				return true;
			}
		}
	}
	return false;
}

int main() {
	int n, m;
	scanf("%d%d", &n, &m);

	for (int i = 0; i < m; i++) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		island[a].push_back(make_pair(b, c));
		island[b].push_back(make_pair(a, c));
	}

	
	scanf("%d%d", &start, &destination);

	int l = 1;
	int r = 1000000000;
	int ans = 0;
	while (l <= r) {
		int mid = (l + r) / 2;
		memset(c, false, sizeof(c));
		if (check(start, mid)) {
			l = mid + 1;
			ans = mid;
		}
		else {
			r = mid - 1;
		}
	}

	printf("%d\n", ans);

	return 0;
}