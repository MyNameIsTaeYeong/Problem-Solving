#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int a[2000000];
int b[2000000];

void merge(int start, int end) {
	int mid = (start + end) / 2;
	int i = start, j = mid + 1, k = 0;
	while (i <= mid&&j <= end) {
		if (a[i] <= a[j]) b[k++] = a[i++];
		else b[k++] = a[j++];
	}

	while (i <= mid) b[k++] = a[i++];
	while (j <= end) b[k++] = a[j++];
	for (int i = start; i <= end; i++)
		a[i] = b[i - start];
}

void m_sort(int start, int end) {
	if (start == end)
		return;
	int mid = (start + end) / 2;
	m_sort(start, mid);
	m_sort(mid + 1, end);
	merge(start, end);
}





int main()
{
	int n, m;
	scanf("%d%d", &n, &m);

	for (int i = 0; i < m + n; i++)
		scanf("%d", &a[i]);

	m_sort(0, m + n - 1);

	for (int i = 0; i < m + n; i++)
		printf("%d ", a[i]);


	/*
	

	for (int i = 0; i < n; i++) {
		scanf("%d", &a[i]);
	}

	for (int i = 0; i < m; i++) {
		scanf("%d", &b[i]);
	}

	int a_index = 0, b_index = 0, c_index = 0;

	while (a_index < n&&b_index < m) {
		if (a[a_index] < b[b_index]) {
			c[c_index] = a[a_index];
			a_index++;
		}
		else {
			c[c_index] = b[b_index];
			b_index++;
		}
		c_index++;
	}

	while (a_index < n) c[c_index++] = a[a_index++];
	while (b_index < m) c[c_index++] = b[b_index++];

	for (int i = 0; i < m + n; i++)
		printf("%d ", c[i]);
	*/
	return 0;
}