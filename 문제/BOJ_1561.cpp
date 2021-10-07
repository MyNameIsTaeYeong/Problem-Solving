#pragma warning (disable:4996)

#include <cstdio>
using namespace std;
//놀이기구 운행시간을 저장하는 변수
long long a[10000];
int n, m;

// x분에 몇명을 태우는지 검사하는 함수
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

	// 0분째에 모든 인원을 다 태우는 경우
	if (n <= m) {
		printf("%d", n);
		return 0;
	}

	// 1분부터
	long long l = 1;
	// 최대 600억분까지
	long long r = 2000000000LL * 30LL;

	// here : 여기에 몇분째에 모든 인원을 태우는지를 저장한다.
	long long here = 0;

	// 이분탐색
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

	// here - 1 분까지 탄 학생 수
	long long stu_cnt = m;
	for (int i = 0; i < m; i++) {
		stu_cnt += (here - 1) / a[i];
	}
	// here분에 타는 학생 수
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