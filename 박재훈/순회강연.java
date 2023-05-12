import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_2109 {
    static int n;
    static boolean[] visited;
    static PriorityQueue<int[]> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        //받을 돈을 기준으로 내림차순 저장할 우선순위 큐
        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });

        int size = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int d = Integer.parseInt(input[1]);
            pq.add(new int[]{p, d});
            //d 값 중 가장 큰 값 찾기
            size = Math.max(size, d);
        }
        if(size > 0) {
            //강연할 날짜의 최대크기만큼 방문 배열 생성
            visited = new boolean[size + 1];

            int sum = 0;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int idx = cur[1];
                //돈 많이 주는 강연 순서대로 체크
                //강연 기한 마지막 날부터 가능한지보고 불가능하면 하루씩 내려감
                while (idx > 0 && visited[idx]) {
                    idx--;
                }
                //강연 가능하면 방문 표시 후 금액 합산
                if (idx > 0) {
                    visited[idx] = true;
                    sum += cur[0];
                }
            }

            System.out.println(sum);
        }else{
            System.out.println(0);
        }


    }
}
