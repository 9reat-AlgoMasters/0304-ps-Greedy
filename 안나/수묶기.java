import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int N;
	static int num[];
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		// -------------입력
		if (N == 1) {
			System.out.println(num[0]);
			System.exit(0);
		}

		Arrays.sort(num); //오름차순으로 정렬

//		System.out.println(Arrays.toString(num));

		int left = 0; //가장 왼쪽에서 오른쪽 방향으로 향하는 포인터(음수에서 두 수를 곱했을때 양수가 나오는지 판별)
		int right = N - 1;  // 가장 오른쪽에서 왼쪽으로 향하는 포인터(양수에서 두 수를 곱했을 때 양수가 나오는지 판별)
		
		
		//왼쪽에서 차례대로 두 수를 보는데 두 수 중 0보다 큰 수가 나오면 break;
		//두 수가 (음수, 음수)이거나, (음수,0)일때만 묶기
		while (true) {
			if (num[left] > 0 || num[left + 1] > 0) {
				break;
			}

			sum += (num[left] * num[left + 1]); //묶기

			left += 2;
			
			//만약 범위를 넘어간다면 
			if (!isInRange(left + 1)) {
				//남는 수가 있는지 확인후
				if (isInRange(left)) {//남는 수가 있다면 마저 더해주고
					sum += num[left];
				}
				//오른쪽까지 모든 수를 다 봤기 때문에 출력하고 끝내기
				System.out.println(sum);
				System.exit(0);
			}
		}
		
		
		//오른쪽에서 왼쪽으로 차례대로 두 수를 보는데 두 수 중 0또는 음수가 나오면 break;
		//추가로 두 수 중에 1을 포함하는 경우에는 곱하는 경우보다 더하는 경우가 수가 더 크기 때문에 break;
		//두 수가 1보다 큰 (양수, 양수)일때만 묶기 
		while (true) {
			if (num[right - 1] <= 0 || num[right] <= 0) {
				break;
			}
			if(num[right-1] == 1) {
				break;
			}

			sum += (num[right-1] * num[right]); //묶기

			right -= 2;
			
			//만약 범위를 넘어간다면 
			if (!isInRange(right - 1)) {
				//남는 수가 있는지 확인후
				if (isInRange(right)) { //남는 수가 있다면 마저 더해주고
					sum += num[right];
				}
				//왼쪽까지 모든 수를 다 봤기 때문에 출력하고 끝내기
				System.out.println(sum);
				System.exit(0);
				
				
			}

		}
//		System.out.println("[LEFT : "+left+"]"+",  [RIGHT : "+ right+"]");

		
		//남은 수들 더해주기 
		for (int i = left; i <= right; i++) {
			sum += num[i];
		}

		System.out.println(sum);
	}

	private static boolean isInRange(int i) {
		return i >= 0 && i < N;
	}

}