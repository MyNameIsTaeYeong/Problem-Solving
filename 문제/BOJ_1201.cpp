#pragma warning(disable:4996)

#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;

int main()
{
	
	int n, m, k;
	scanf("%d%d%d", &n, &m, &k);

	// 가능한 경우
	if (m + k - 1 <= n && n <= m*k) {

		vector<int> a(n);

		for (int i = 0; i < n; i++)
			a[i] = i + 1;

		// 오름 차순 정리
		sort(a.begin(), a.end());
		
		// M등분(적어도 하나의 원소는 K개)
		vector<int> g;
		g.push_back(0);
		g.push_back(k);
		
		n -= k;
		m -= 1; 

		int gs = m == 0 ? 0 : n / m;
		int r = m == 0 ? 0 : n%m;
		for (int i = 0; i < m; i++) {
			g.push_back(g.back() + gs + (r > 0 ? 1 : 0));
			r--;
		}
		// 각 그룹을 뒤집는다.
		for (int i = 0; i < g.size() - 1; i++) {
			reverse(a.begin()+g[i],a.begin()+g[i+1]);
		}

		for (int i = 0; i < a.size(); i++)
			printf("%d ", a[i]);
 
	}

	// 불가능한 경우
	else {
		/*
		 증가 수열, 감소 수열이 각각 1개씩일때 -> 두 수열은 최대 1개를 공유할 수 있음
		 따라서 N >= M+K-1  
		 증가 수열이 감소 수열의 개수 만큼 있거나 그 반대 일때
		 N = N*M
		 N의 범위는 M+K-1 <= N <= MK
		*/
		printf("%d\n", -1);
	}

	return 0;
}
