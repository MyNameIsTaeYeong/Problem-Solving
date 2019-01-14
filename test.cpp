//
//  BOJ_2022.cpp
//  Algorithm_Mac
//
//  Created by TaeYeong on 14/01/2019.
//  Copyright © 2019 TaeYeong. All rights reserved.
//
#include <cstdio>
#include <cmath>
#include <algorithm>
using namespace std;
int main()
{
    double x,y,c;
    scanf("%lf%lf%lf",&x,&y,&c);
    
    double left = 0;
    double right = min(x,y);
    
    int i;
    for(i=0;i<10000;i++){
        double mid = (left+right)/2.0;
        
        double h1 = sqrt(y*y-mid*mid);
        double h2 = sqrt(x*x-mid*mid);
        double test_c = (h1 * h2) / (h1 + h2);
        
        if(test_c < c){
            right = mid;
        }else {
            left = mid;
        }
        
    }
    
    printf("%.3lf",left);
    
    return 0;
}
