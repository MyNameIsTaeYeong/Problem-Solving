#include<cstdio>
#include<cstring>
using namespace std;

int n;
bool areFriends[10][10];

int pairings(bool taken[10]){
	
	// �ߺ����� ���� ��츦 �����ϱ� ���� ������ ���� �ߴ�. 
	// �̷��� �Ǹ� (0,1)�� (1,0)�� ���� �ʰ�, (0,1) , (2,3) ��  (2,3) , (0,1) �� ���� ���� �� �ִ�. 
	int first = -1;
	for(int i=0; i<n; i++){
		if(!taken[i]){
			first = i; 
			break;	
		} 
	}
	if(first == -1)
		return 1;
	
	
	int ret = 0;
	for(int second = first + 1; second<n; second++){
		if(!taken[second] && areFriends[first][second]){
			taken[first] = taken[second] = true;
			ret += pairings(taken);
			taken[first] = taken[second] = false;
	
		}
	}
	
	return ret;
	
}

int main()
{
	int c;
	scanf("%d", &c);
	
	while(c--){
		int m;
		scanf("%d%d", &n, &m);
		
		memset(areFriends, false, sizeof(areFriends));
		
		for(int i=0; i<m; i++){
			int first, second;
			scanf("%d%d", &first, &second);
			areFriends[first][second] = true;
			areFriends[second][first] = true;
		}
		
		bool taken[10];
		memset(taken, false, sizeof(taken));
		
		int ans = pairings(taken);
		printf("%d\n", ans); 
	}
	
	return 0;
}
