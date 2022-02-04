import java.util.*;

class Pair{
    int car;
    int fee;
    public Pair(int car, int fee){
        this.car = car;
        this.fee = fee;
    }
}

class Solution {

    static Map<String, String> inMap = new HashMap<>();
    static Map<String, Integer> usingTime = new HashMap<>();
    static Set<String> cars = new HashSet<>();

    static int timeDiff(String outTime, String inTime){
        StringTokenizer st = new StringTokenizer(outTime, ":");
        int outHour = Integer.parseInt(st.nextToken());
        int outMin = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(inTime, ":");
        int inHour = Integer.parseInt(st.nextToken());
        int inMin = Integer.parseInt(st.nextToken());

        if(outMin < inMin){
            outHour--;
            outMin += 60;
        }

        return (outHour - inHour) * 60 + (outMin - inMin);
    }

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        for(String record : records){
            StringTokenizer st = new StringTokenizer(record, " ");
            String time = st.nextToken();
            String car = st.nextToken();
            String what = st.nextToken();
            cars.add(car);

            if(what.charAt(0) == 'I'){
                inMap.put(car, time);
            }else{
                String inTime = inMap.get(car);
                inMap.remove(car);
                int diff = timeDiff(time, inTime);
                usingTime.put(car, usingTime.getOrDefault(car, 0) + diff);
            }
        }

        Iterator<String> it = cars.iterator();
        while(it.hasNext()){
            String car = it.next();
            if(inMap.containsKey(car)){
                String inTime = inMap.get(car);
                int diff = timeDiff("23:59", inTime);
                usingTime.put(car, usingTime.getOrDefault(car, 0) + diff);
            }
        }

        ArrayList<Pair> list = new ArrayList<>();
        it = cars.iterator();

        while(it.hasNext()){
            String car = it.next();
            int time = usingTime.get(car);
            int fee = fees[1];
            time -= fees[0];
            if(time > 0){
                if(time % fees[2] == 0){
                    fee += time / fees[2] * fees[3];
                }else{
                    fee += (time / fees[2] + 1) * fees[3];
                }
            }
            list.add(new Pair(Integer.parseInt(car), fee));
        }

        Collections.sort(list, (s1, s2) -> Integer.compare(s1.car, s2.car));

        answer = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).fee;
        }

        return answer;
    }
}