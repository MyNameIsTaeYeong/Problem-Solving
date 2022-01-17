import java.io.*;
import java.util.*;

class Stu implements Comparable<Stu>{
    public int num;
    public int cnt;
    public int when;

    public Stu(int num, int cnt, int when){
        this.num = num;
        this.cnt = cnt;
        this.when = when;
    }


    @Override
    public int compareTo(Stu o) {
        if(this.cnt == o.cnt){
            return this.when - o.when;
        }else{
            return this.cnt - o.cnt;
        }
    }
}


public class BOJ_1713 {

    static int N, T;
    static Stu[] stuArr;
    static ArrayList<Stu> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());

        stuArr = new Stu[101];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<T; i++){
            int stuNum = Integer.parseInt(st.nextToken());

            if(stuArr[stuNum] != null){
                stuArr[stuNum].cnt++;
            }else{
                if(list.size() == N){
                    Collections.sort(list);
                    stuArr[list.get(0).num] = null;
                    list.remove(0);
                }
                stuArr[stuNum] = new Stu(stuNum, 1, i);
                list.add(stuArr[stuNum]);
            }
        }

        for(int i=1; i<= 100; i++){
            if(stuArr[i] != null){
                System.out.print(i+" ");
            }
        }

    }
}
