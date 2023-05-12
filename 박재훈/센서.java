import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main_2212 {
    static int N, K;
    static int[] arr, dist;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        //일단 직선 상 위치대로 표현할 수 있게 정렬
        Arrays.sort(arr);

        //각 센서 사이 거리를 저장할 배열 -> 거리가 클수록 그 곳을 영역의 경계선으로 선택해야 이득 -> 해당 거리는 계산에 포함X
        dist = new int[N-1];
        for (int i = 0; i < N-1; i++) {
            dist[i] = arr[i+1] - arr[i];
        }

        //정렬
        Arrays.sort(dist);

        int sum = 0;
        //N개 센서 사이 거리:N-1개
        //K개 집중국 사이의 경계 수:K-1개
        //거리가 큰 쪽에서부터 K-1개 제외하고 합산
        int limit = (N-1)-(K-1);
        for (int i = 0; i < limit; i++) {
            sum += dist[i];
        }

        System.out.println(sum);
    }

}
