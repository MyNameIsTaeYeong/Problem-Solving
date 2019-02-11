#include<cstdio>
using namespace std;

int h,w;
char board[20][20];

int dx1[4] = {1,0,0,1};
int dy1[4] = {0,1,1,0};
int dx2[4] = {1,1,1,1};
int dy2[4] = {1,1,0,-1};


int covering(){
	
	int x = -1, y = -1;
	for(int i=0; i<h; i++){
		for(int j=0; j<w; j++){
			if(board[i][j] == '.'){
				x = i;
				y = j;
				break;
			}
		}
		if(y != -1)
			break;
	}
	
	if(y == -1)
		return 1;
	
	int ret = 0;

	for(int i=0; i<4; i++){
		if(board[x+dx1[i]][y+dy1[i]] == '.' &&  board[x+dx2[i]][y+dy2[i]] == '.'
		&& x+dx1[i] < h && x+dx1[i] >=0 && x+dx2[i] < h && x+dx2[i] >= 0 
		&& y+dy1[i] < w && y+dy1[i] >= 0 && y+dy2[i] < w && y+dy2[i] >=0 ){
			//채운다
			board[x][y] = board[x+dx1[i]][y+dy1[i]] = board[x+dx2[i]][y+dy2[i]]	= '#';
	
			//다음 칸 탐색	
			ret += covering();
			
			//비운다	
			board[x][y] = board[x+dx1[i]][y+dy1[i]] = board[x+dx2[i]][y+dy2[i]]	= '.';
		} 
	}
	
	return ret;
	
}

int main()
{
	int c;
	scanf("%d", &c);
	
	while(c--){
		scanf("%d%d", &h,&w);
	
		int cnt = 0;
		for(int i=0; i<h; i++){
			for(int j=0; j<w; j++){
				scanf(" %1c", &board[i][j]);
				if(board[i][j] == '.')
					cnt++;
			}
		}
		
		int ans = 0;
		if(cnt % 3 != 0){
			printf("%d\n", ans);
		}else{
			ans = covering();
			printf("%d\n", ans);
		}
		
	
		
	}
	
	
	return 0;
}
