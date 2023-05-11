import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138 {
	static final char ON = '0';
	static final char OFF = '1';
	static int N;
	static char[] nowBulbs;
	static char[] goalBulbs;

	/*
	 * 아이디어 1. 첫번째 스위치 첫번째 스위치를 눌렀는지 안눌렀는지에 따라서 목표에 도달 여부와 최소 스위치누름 횟수가 달라진다.
	 * 
	 * 2. 탐색방법 keypoint. 스위치를 눌렀을때 상태가 바뀌는 세 전구 중 가장 왼쪽 전구의 상태를 목표상태와 일치시킨다. 1). 전구의
	 * 상태의 일치 여부를 왼쪽에서 오른쪽으로 이동하며 탐색한다. 2). 일치하지 않는다면 탐색하고 있는 전구 +1번째의 스위치를 눌러 전구의
	 * 상태를 일치시킨다. 3). 가장 마지막 전구가 일치하는지를 확인하면 목표에 도달했는지 못했는지 알 수 있다.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nowBulbs = br.readLine().toCharArray();
		goalBulbs = br.readLine().toCharArray();

		char[] nowBulbs_copy = Arrays.copyOf(nowBulbs, N);

		// 첫 번째 전구의 상태를 바꾸지 않고 탐색
		int answer_1 = count();

		nowBulbs = nowBulbs_copy;
		// 첫 번째 전구의 상태 변경
		switchOnOff(0, true);

		// 첫 번째 전구의 상태를 번경 후 탐색 (첫번째 전구의 상태를 변경했기 때문에 count에서 +1 해준다.)
		int answer_2 = count() + 1;

		// 두 값중 작은 값을 찾는다.
		int answer = Math.min(answer_1, answer_2);

		if (answer >= Integer.MAX_VALUE - 1) {
			System.out.println(-1);
			return;
		}
		System.out.println(answer);

	}

	// 전구를 목표상태에 도달하기위해 변경한 횟수를 세는 함수
	static int count() {
		int cnt = 0;
		for (int i = 0; i < N - 1; i++) {
			// 사이드(즉 마지막 전구 -1번째 전구)이면 2개만 변경
			boolean side = i == N - 2 ? true : false;
			if (nowBulbs[i] != goalBulbs[i]) { // 현재 보고 있는 전구의 위치가 같지 않다면
				// i , i+1, i+2 번째 전구 상태 변경
				switchOnOff(i, side);
				cnt++;
			}
		}

		// 다 일치 시킨후 마지막 전구의 상태를 확인 => 마지막 전구가 일치하면 모두 일치
		if (nowBulbs[N - 1] == goalBulbs[N - 1]) {
			return cnt;
		}
		return Integer.MAX_VALUE - 1;
	}

	// idx+1번째 스위치를 눌렀을때 idx, idx+1, idx+2 전구의 상태를 변경하는 함수
	static void switchOnOff(int idx, boolean isSide) {
		int range = 3;
		// 양쪽 사이드라면 2개의 전구만 변경한다.
		if (isSide) {
			range = 2;
		}
		for (int i = idx; i < idx + range; i++) {
			if (nowBulbs[i] == ON) {
				nowBulbs[i] = OFF;
			} else {
				nowBulbs[i] = ON;
			}
		}
	}
}