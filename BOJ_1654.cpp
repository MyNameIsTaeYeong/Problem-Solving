#pragma warning (disable:4996)

#include<cstdio>
using namespace std;

long long a[10000];
int k, n;
bool check(long long x) {
	long long cnt = 0;
	for (int i = 0; i < k; i++) {
		cnt += a[i] / x;
	}

	return cnt >= n;
}

int main()
{
	scanf("%d%d", &k, &n);
	long long max = 0;
	for (int i = 0; i < k; i++) {
		scanf("%lld", &a[i]);
		if (max < a[i])
			max = a[i];
	}

	long long l, r;
	l = 1;
	r = max;
	long long ans = 0;
	while (l <= r) {
		long long mid = (l + r) / 2;
		if (check(mid)) {
			l = mid + 1;
			ans = mid;
		}
		else {
			r = mid - 1;
		}
	}

	printf("%lld", ans);

	return 0;
}