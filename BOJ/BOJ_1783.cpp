#pragma warning(disable:4996)

#include<cstdio>
#include<algorithm>
using namespace std;


int main()
{
	int h, w;
	scanf("%d%d", &h, &w);

	//���� 1 : ���������θ� �����δ� -> �����ٿ��� ��ĭ�� �湮 �� �� �ִ� 
	//		-> ���̰� 3�̻��̸� ���̴� �信 ������ ���� �ʴ´�.

	if (h == 1)
		printf("%d", 1);
	else if (h == 2)
		printf("%d", min(4, (w + 1) / 2));
	//���� 2 : 4�� �̻� �̵��� ��� ������ �̵������ �� �� �̻� �̿�. -> 4�� �̵��ϸ� (7,1) 
	//		-> �� ���ĺ��ʹ� �ʺ� 1�� 1ĭ �� �湮 ����
	else
		//4�� �̻� �̵��ϴ� ���
		if (w >= 7)
			printf("%d", w - 2);
	//4�� �̸� �̵��ϴ� ���
		else
			printf("%d", min(4, w));

	return 0;
}