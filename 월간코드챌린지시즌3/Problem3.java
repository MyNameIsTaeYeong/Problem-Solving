class Solution {

    static boolean possilbe(long mid, int a, int b, int[] g, int[] s, int[] w, int[] t){
        long gMax = 0;
        long gMin = 0;
        long sMax = 0;
        long sMin = 0;

        int n = g.length;

        for(int i=0; i<n; i++){
            long cnt = mid / t[i];

            if(cnt % 2 == 0){
                cnt /= 2;
            }else{
                cnt /= 2;
                cnt += 1;
            }



            long totalWeight = cnt * w[i];



            // 금 우선
            if(g[i] > totalWeight)
                gMax += totalWeight;
            else{
                gMax += g[i];
                if(s[i] >= (totalWeight - g[i]))
                    sMin += totalWeight - g[i];
                else
                    sMin += s[i];
            }

            // 은 우선
            if(s[i] > totalWeight)
                sMax += totalWeight;
            else{
                sMax += s[i];
                if(g[i] >= (totalWeight - s[i]))
                    gMin += totalWeight - s[i];
                else
                    gMin += g[i];
            }
        }

        //System.out.println(gMax + sMin);

        if(a <= gMax && b <= sMax && a + b <= gMax + sMin)
            return true;
        else
            return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;

        long left = 0;
        long right = 1;
        for(int i=0; i<15; i++){
            right *= 10;
        }

        while(left <= right){
            long mid = (left + right) / 2;
            if(mid < 0)
                mid = left + (right - left) / 2;

            //System.out.println(mid);

            if(possilbe(mid, a, b, g, s, w, t)){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }

        }

        return answer;
    }
}