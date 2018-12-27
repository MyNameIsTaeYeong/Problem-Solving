#pragma warning(disable:4996)

#include<cstdio>
#include<algorithm>
using namespace std;


int main()
{
	int h, w;
	scanf("%d%d", &h, &w);

	//조건 1 : 오른쪽으로만 움직인다 -> 세로줄에서 한칸만 방문 할 수 있다 
	//		-> 높이가 3이상이면 높이는 답에 영향을 주지 않는다.

	if (h == 1)
		printf("%d", 1);
	else if (h == 2)
		printf("%d", min(4, (w + 1) / 2));
	//조건 2 : 4번 이상 이동할 경우 각각의 이동방법을 한 번 이상 이용. -> 4번 이동하면 (7,1) 
	//		-> 그 이후부터는 너비 1당 1칸 더 방문 가능
	else
		//4번 이상 이동하는 경우
		if (w >= 7)
			printf("%d", w - 2);
	//4번 미만 이동하는 경우
		else
			printf("%d", min(4, w));

	return 0;
}