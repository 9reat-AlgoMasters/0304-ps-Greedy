import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138 {
    static int N;
    static char[] status, target, copy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //초기 상태
        status = new char[N];
        //목표
        target = new char[N];

        status = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        copy = Arrays.copyOf(status, N);

        //목표 배열과 비교해서 i번의 전구가 안맞으면 바로 뒤의 i+1번 버튼을 눌러서 i번 전구를 목표와 맞춰주기(i, i+1, i+2번 전구가 바뀜)
        //맨 앞 전구가 안맞을 때 첫 버튼을 눌러서 맞춰 줄 수도 있고 두번째 버튼을 눌러서 맞춰줄 수도 있음 -> 두 경우로 나누기

        //맨 첫 버튼을 안 누른 경우의 횟수
        int cnt1 = 0;



        //맨 첫 버튼을 누를 때의 횟수
        int cnt2 = 1;
        //첫번째 버튼 누름 처리
        status[0] = status[0] == '1' ? '0' : '1';
        status[1] = status[1] == '1' ? '0' : '1';

        for (int i = 0; i < N-1; i++) {
            //copy는 첫 버튼 안누른 경우의 배열, status는 누른 경우
            if(copy[i] != target[i]){
                copy[i] = copy[i] == '1' ? '0' : '1';
                copy[i+1] = copy[i+1] == '1' ? '0' : '1';
                if(i != N-2) {
                    copy[i+2] = copy[i+2] == '1' ? '0' : '1';
                }
                cnt1++;
            }
            if(status[i] != target[i]){
                status[i] = status[i] == '1' ? '0' : '1';
                status[i+1] = status[i+1] == '1' ? '0' : '1';
                if(i != N-2) {
                    status[i+2] = status[i+2] == '1' ? '0' : '1';
                }
                cnt2++;
            }
        }
        //마지막 전구가 안 맞으면 앞에 다 맞춰놓은 전구들의 상태를 유지한 채 마지막만 바꿀 방법이 없다...->불가능
        if(copy[N-1] != target[N-1]){
            cnt1 = Integer.MAX_VALUE;
        }
        if(status[N-1] != target[N-1]){
            cnt2 = Integer.MAX_VALUE;
        }
        //둘 중 적은 횟수 선택
        int ans = Math.min(cnt1, cnt2);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
