import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; // 센서의 수
	static int K; // 집중국의 개수
	static int sensor[]; // 센서의 좌표

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		sensor = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		// ------------- 입력 끝
		
		//센서를 좌표의 순서대로 나열
		Arrays.sort(sensor);
	
		
//		System.out.println("[Sensor]"+Arrays.toString(sensor));
		
		int dist[] = new int[N-1]; //i에서 i+1 센서 사이의 거리
		//즉 센서 사이의 거리 
		for (int i = 0; i < N-1; i++) {
			dist[i] = sensor[i+1]-sensor[i];
		}
//		System.out.println("[Term] : "+Arrays.toString(term)); 
	
		int sum = sensor[N-1] - sensor[0]; //센서 사이의 거리 총합
		//집중국의 개수가 더 많다면 각 센서에 하나씩 둘수 있으니까 모든 집중국의 수신가능영역거리가 모두 0이다.
		if(K >= N) {
			System.out.println(0);
			System.exit(0);
		}
		

		//거리가 긴 수신영역을 차례대로 설치할 집중국 개수 -1만큼 제외시켜준다.
		for (int i = 0; i < K-1; i++) {
			int maxTerm = 0;
			int idx = -1;
		
			for (int j = 0; j < N-1; j++) {
				if(maxTerm < dist[j]) {
					maxTerm = dist[j];
					idx = j;
				}
			}
			sum -= dist[idx];
			dist[idx] = -1;
		}
//		System.out.println(Arrays.toString(cut));
		
		System.out.println(sum);
	
 
		//전체 sum에서 큰 차이들을 한개씩 뺀다. 덩어리가 K가 될때까ㅓ지
        /*
[3] [6, 7, 8], [10], [12, 14, 15], [18, 20]
t   f   f  t    t      f  f    t    f   t
  [3, 1, 1, 2, 2, 2, 1, 3, 2]
*/
	}

}