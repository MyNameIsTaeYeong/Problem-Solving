#pragma warning (disable:4996)

#include<cstdio>
using namespace std;
int n, m;
int tree[1000000];

bool check(int x) {
	long long cnt = 0;
	for (int i = 0; i < n; i++) {
		if (tree[i] > x)
			cnt += tree[i] - x;
	}
	return cnt >= m;
}

int main()
{
	scanf("%d%d", &n, &m);
	int max = 0;
	for (int i = 0; i < n; i++) {
		scanf("%d", &tree[i]);
		if (max < tree[i])
			max = tree[i];
	}

	int under = 1;
	int up = max;
	int ans = 0;
	while (under <= up) {
		int mid = (under + up) / 2;
		if (check(mid)) {
			under = mid + 1;
			ans = mid;
		}
		else {
			up = mid - 1;
		}
	}

	printf("%d", ans);

	return 0;
}