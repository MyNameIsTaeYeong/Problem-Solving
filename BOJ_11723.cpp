//
//  BOJ_2022.cpp
//  Algorithm_Mac
//
//  Created by TaeYeong on 14/01/2019.
//  Copyright © 2019 TaeYeong. All rights reserved.
//
#include<cstdio>
#include<cstring>
using namespace std;
char command[10];
int main()
{
    int m;
    scanf("%d",&m);
    
    int s = 0;
    
    for(int i=0;i<m;i++){
        int x;
        
        scanf("%s",command);
        
        if(!strcmp(command,"all")){
            s = (1<<21)-2;
        }else if(!strcmp(command,"empty")){
            s = 0;
        }else{
            scanf("%d",&x);
            
            if(!strcmp(command,"add")){
                s = s|(1<<x);
            }else if(!strcmp(command,"remove")){
                s = s&~(1<<x);
            }else if(!strcmp(command,"check")){
                if(s&(1<<x)){
                    puts("1");
                }else{
                    puts("0");
                }
            }else if(!strcmp(command,"toggle")){
                s = s^(1<<x);
            }
        }
        
        
    }
    
    return 0;
}
