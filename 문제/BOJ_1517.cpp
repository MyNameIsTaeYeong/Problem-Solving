#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int a[500000];
int b[500000];
long long int ans;

void solve(int start, int end) {
	if (start >= end)
		return;

	int mid = (start + end) / 2;

	solve(start, mid);
	solve(mid + 1, end);

	int l = start;
	int r = mid + 1;
	int b_index = start;

	while (l <= mid&&r <= end) {
		if (a[l] <= a[r]) b[b_index++] = a[l++];
		else {
			b[b_index++] = a[r++];
			ans += mid - l + 1;
		}
	}
	while (l <= mid) b[b_index++] = a[l++];
	while (r <= end) b[b_index] = a[r++];

	for (int i = start; i <= end; i++)
		a[i] = b[i];
}

int main()
{
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		scanf("%d", &a[i]);

	solve(0, n - 1);

	printf("%lld", ans);

	return 0;
}