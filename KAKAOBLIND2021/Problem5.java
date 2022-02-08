import java.util.*;

// 한번만 제대로 구해놓기.

class Solution {

    static long[] time = new long[360001];

    static void acumulate(){
        for(int i=1; i<=360000; i++){
            time[i] += time[i-1];
        }
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        StringTokenizer st;
        for(String log : logs){
            st = new StringTokenizer(log, "-");
            String start = st.nextToken();
            String end = st.nextToken();

            st = new StringTokenizer(start, ":");
            int startIdx = Integer.parseInt(st.nextToken()) * 3600
                    + Integer.parseInt(st.nextToken()) * 60
                    + Integer.parseInt(st.nextToken());

            time[startIdx+1] += 1;

            st = new StringTokenizer(end, ":");
            int endIdx = Integer.parseInt(st.nextToken()) * 3600
                    + Integer.parseInt(st.nextToken()) * 60
                    + Integer.parseInt(st.nextToken());
            time[endIdx+1] -= 1;

            //System.out.println(startIdx+" "+endIdx);
        }

        acumulate();
        acumulate();

        st = new StringTokenizer(adv_time, ":");
        int totalAdvTime = Integer.parseInt(st.nextToken()) * 3600
                + Integer.parseInt(st.nextToken()) * 60
                + Integer.parseInt(st.nextToken());

        st = new StringTokenizer(play_time, ":");
        int totalPlayTime = Integer.parseInt(st.nextToken()) * 3600
                + Integer.parseInt(st.nextToken()) * 60
                + Integer.parseInt(st.nextToken());

        long maxTime = -1;
        int endTime = totalAdvTime;


        for(int i=totalAdvTime; i<=totalPlayTime; i++){
            if(time[i] - time[i-totalAdvTime] > maxTime){
                maxTime = time[i] - time[i-totalAdvTime];
                endTime = i;
            }
        }

        int startTime = endTime - totalAdvTime;

        //System.out.println(endTime);

        if(startTime / 3600 > 9){
            answer += (startTime / 3600);
        }else{
            answer += "0";
            answer += (startTime / 3600);
        }

        answer += ":";

        startTime %= 3600;

        if(startTime / 60 > 9){
            answer += (startTime / 60);
        }else{
            answer += "0";
            answer += (startTime / 60);
        }

        answer += ":";

        startTime %= 60;

        if(startTime > 9){
            answer += startTime;
        }else{
            answer += "0";
            answer += startTime;
        }

        return answer;
    }
}