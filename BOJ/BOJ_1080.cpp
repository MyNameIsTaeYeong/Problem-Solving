#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

/*
	문제

	0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 
	최솟값을 구하는 프로그램을 작성하시오.
	행렬을 변환하는 연산은 어떤 3*3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 -> 1, 1 -> 0)

	입력 

	첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다.
	둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.

	출력

	첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
*/

int a[50][50];
int b[50][50];

void flip(int x, int y) {
	for (int i = x - 1; i < x + 2; i++) {
		for (int j = y - 1; j < y + 2; j++) {
			if (a[i][j] == 1)
				a[i][j] = 0;
			else
				a[i][j] = 1;
		}
	}
			
}

int main()
{
	int n, m;
	scanf("%d%d", &n, &m);

	//A 입력
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < m; j++) 
			scanf("%1d", &a[i][j]);
		
	
	//B 입력
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < m; j++) 
			scanf("%1d", &b[i][j]);
		
	// 변환 조건이 3*3이므로 비교 해야하는 행렬의 요소는 (n-2)*(m-2)개
	int ans = 0;
	for (int i = 0; i < n - 2; i++) 
		for (int j = 0; j < m - 2; j++) 
			if (a[i][j] != b[i][j]) {
				ans++;
				flip(i + 1, j + 1);
			}
		
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (a[i][j] != b[i][j]) {
				printf("%d", -1);
				return 0;
			}

	printf("%d", ans);
	
	return 0;
}
