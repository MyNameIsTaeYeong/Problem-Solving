#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int r, c, index;

int pow(int n) {
	int m = 1;
	for (int i = 1; i <= n; i++) {
		m *= 2;
	}
	return m;
}

void solve(int x, int y, int m) {
	if (m <= 1) {
		return;
	}

	m /= 2;
	
	if (r < x + m&&c < y + m) {
		index += 0;
		solve(x, y, m);
	}
	else if (r < x + m&&c >= y + m) {
		index += m*m;
		solve(x, y + m, m);
	}
	else if (r >= x + m&&c < y + m) {
		index += 2 * m*m;
		solve(x + m, y, m);
	}
	else {
		index += 3 * m*m;
		solve(x + m, y + m, m);
	}
}

int main()
{
	int n;
	scanf("%d%d%d", &n, &r, &c);

	int m = pow(n);
	
	solve(0, 0, m);
	
	printf("%d", index);
	return 0;
}