#include<cstdio>
#include<vector>
#include<math.h>
using namespace std;

const int INF = 9999, SWITCHES = 10, CLOCKS = 16;

const char linked[SWITCHES][CLOCKS+1] = {
	"xxx.............",
	"...x...x.x.x....",
	"....x.....x...xx",
	"x...xxxx........",
	"......xxx.x.x...",
	"x.x...........xx",
	"...x..........xx",
	"....xx.x......xx",
	".xxxxx..........",
	"...xxx...x...x.."
};

bool areAlingned(const vector<int>& clocks){
	for(int i=0; i<clocks.size(); i++){
		if(clocks[i] != 12)
			return false;
	}
	return true;
}

void push(vector<int>& clocks, int swtch){
	for(int clock=0; clock<CLOCKS; clock++)
	if(linked[swtch][clock] == 'x'){
		clocks[clock] += 3;
		if(clocks[clock] == 15)
			clocks[clock] = 3;
	}
}

int solve(vector<int>& clocks, int swtch){
	if(swtch == SWITCHES) return areAlingned(clocks) ? 0 : INF;
	
	int ret = INF;
	for(int cnt=0; cnt<4; cnt++){
		ret = min(ret, cnt+solve(clocks, swtch+1));
		push(clocks, swtch);
	}
	return ret;
}

int main()
{
	int c;
	scanf("%d", &c);
	
	while(c--){
		vector<int> clocks;
		for(int i=0; i<CLOCKS; i++){
			int clock = 0;
			scanf("%d", &clock);
			clocks.push_back(clock);
		}
		
		int ans = solve(clocks, 0);
		if(ans >= INF)
			printf("%d\n", -1);
		else
			printf("%d\n", ans);
	}
	
	return 0;
}
