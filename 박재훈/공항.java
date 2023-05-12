import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10775_2 {
    static int G, P;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        visited = new boolean[G+1];
        P = Integer.parseInt(br.readLine());

        int cnt = 0;
        //limit : 배치가능한 게이트 번호의 최댓값
        int limit = G;
        for (int i = 0; i < P; i++) {
            //1번부터 r번 중에 하나로 배치해야함
            int r = Integer.parseInt(br.readLine());
            //r번부터 확인, 이미 배치 된 게이트면 번호를 줄여야함
            while (r > 0 && visited[r]) {
                //r번이 limit보다 크면 r과 limit 사이 번호는 볼 필요가 없음(이미 배치 불가란걸 알고있음)
                if(r > limit){
                    r = limit;
                }else{
                    r--;
                }
            }
            //배치가능한 번호 찾음
            if(r > 0){
                //해당 번호가 limit이면 갱신
                if(r == limit){
                    limit = r-1;
                }
                //방문표시, 배치가능 비행기 수 증가
                visited[r] = true;
                cnt++;
            //배치 불가능함 => 끝
            }else{
                break;
            }
        }
        System.out.println(cnt);
    }
}
