import java.io.*;

public class BOJ_11279 {

    static void swap(int[] arr, int idx1, int idx2){
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] heap = new int[100001];
        int last = 1;


        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input == 0){
                if(last == 1){
                    System.out.println(0);
                }else{
                    System.out.println(heap[1]);
                    heap[1] = heap[last-1];
                    heap[last-1] = 0;
                    last--;

                    int cur = 1;
                    // 자식이 더 크다면
                    while(((cur * 2) < last && heap[cur] < heap[cur*2]) || ((cur * 2 + 1) < last && heap[cur] < heap[cur*2+1])){
                        // 자식이 둘 인 경우
                        if((cur * 2 + 1) < last){
                            // 자식 둘다 큰 경우
                            if(heap[cur] < heap[cur*2] && heap[cur] < heap[cur*2+1]){
                                // 오른쪽자식이 큰 경우
                                if(heap[cur*2] < heap[cur*2+1]){
                                    swap(heap, cur, cur*2+1);
                                    cur = cur*2+1;
                                }else {
                                    swap(heap, cur, cur*2);
                                    cur = cur*2;
                                }
                            }
                            // 자식 하나가 큰 경우
                            else{
                                if(heap[cur] < heap[cur*2]){
                                    swap(heap, cur, cur*2);
                                    cur = cur*2;
                                }else{
                                    swap(heap, cur, cur*2+1);
                                    cur = cur*2+1;
                                }
                            }
                        }
                        // 자식이 하나인 경우
                        else{
                            swap(heap, cur, cur*2);
                            cur = cur*2;
                        }
                    }
                }

            }
            // 삽입
            else{
                heap[last++] = input;
                int cur = last-1;
                while(cur >= 2 && heap[cur/2] < heap[cur]){
                    swap(heap, cur, cur/2);
                    cur /= 2;
                }
            }
        }
    }
}
