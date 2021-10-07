#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

/*
	����

	0�� 1�θ� �̷���� ��� A�� ��� B�� �ִ�. �̶�, ��� A�� ��� B�� �ٲٴµ� �ʿ��� ������ Ƚ���� 
	�ּڰ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
	����� ��ȯ�ϴ� ������ � 3*3ũ���� �κ� ��Ŀ� �ִ� ��� ���Ҹ� ������ ���̴�. (0 -> 1, 1 -> 0)

	�Է� 

	ù° �ٿ� ����� ũ�� N M�� �־�����. N�� M�� 50���� �۰ų� ���� �ڿ����̴�.
	��° �ٺ��� N���� �ٿ��� ��� A�� �־�����, �� �����ٺ��� N���� �ٿ��� ��� B�� �־�����.

	���

	ù° �ٿ� ������ ������ ����Ѵ�. ���� A�� B�� �ٲ� �� ���ٸ� -1�� ����Ѵ�.
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

	//A �Է�
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < m; j++) 
			scanf("%1d", &a[i][j]);
		
	
	//B �Է�
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < m; j++) 
			scanf("%1d", &b[i][j]);
		
	// ��ȯ ������ 3*3�̹Ƿ� �� �ؾ��ϴ� ����� ��Ҵ� (n-2)*(m-2)��
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
