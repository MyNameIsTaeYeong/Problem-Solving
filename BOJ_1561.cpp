#pragma warning (disable:4996)

#include <cstdio>
using namespace std;
//���̱ⱸ ����ð��� �����ϴ� ����
long long a[10000];
int n, m;

// x�п� ����� �¿���� �˻��ϴ� �Լ�
bool check(long long x) {
	long long cnt = m;
	for (int i = 0; i < m; i++) {
		cnt += x / a[i];
	}
	return cnt >= n;
}

int main() 
{
	scanf("%d%d", &n, &m);

	for (int i = 0; i < m; i++) {
		scanf("%lld", &a[i]);
	}

	// 0��°�� ��� �ο��� �� �¿�� ���
	if (n <= m) {
		printf("%d", n);
		return 0;
	}

	// 1�к���
	long long l = 1;
	// �ִ� 600��б���
	long long r = 2000000000LL * 30LL;

	// here : ���⿡ ���°�� ��� �ο��� �¿������ �����Ѵ�.
	long long here = 0;

	// �̺�Ž��
	while (l <= r) {
		long long mid = (l + r) / 2;

		if (check(mid)) {
			r = mid - 1;
			here = mid;
		}
		else {
			l = mid + 1;
		}
	}

	// here - 1 �б��� ź �л� ��
	long long stu_cnt = m;
	for (int i = 0; i < m; i++) {
		stu_cnt += (here - 1) / a[i];
	}
	// here�п� Ÿ�� �л� ��
	long long stu_final = n - stu_cnt;
	
	int ans = 0;
	for (int i = 0; i < m; i++) {
		if (here % a[i] == 0) {
			stu_final--;
			if (stu_final == 0)
				ans = i;
		}
	}

	printf("%d\n", ans + 1);

	return 0;
}