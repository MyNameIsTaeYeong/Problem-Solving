#pragma warning (disable:4996)

#include<cstdio>
#include<algorithm>
using namespace std;
int n, c;
int house[200000];


bool check(int x) {
	int cnt = 1;
	int install = house[0];
	for (int i = 1; i < n; i++) {
		if (house[i] - install >= x) {
			cnt++;
			install = house[i];
		}
	}
	return cnt >= c;
}

int main()
{
	scanf("%d%d", &n, &c);
	int max_x = 0;
	int min_x = 1000000000;

	for (int i = 0; i < n; i++) {
		scanf("%d", &house[i]);
		if (max_x < house[i])
			max_x = house[i];
		if (min_x > house[i])
			min_x = house[i];
	}

	int l = 1;
	int r = max_x - min_x;

	sort(&house[0], &house[n]);

	int ans = 0;

	while (l <= r) {
		int mid = (l + r) / 2;
		if (check(mid)) {
			l = mid + 1;
			ans = mid;
		}
		else {
			r = mid - 1;
		}
	}

	printf("%d", ans);

	return 0;
}