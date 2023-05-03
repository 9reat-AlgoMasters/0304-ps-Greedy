import java.io.*;

public class Q2138 {
    static final int IMPOSSIBLE = -1;

    static int N;
    static boolean[] end;
    static boolean[] bulbs;

    static boolean findAns = false;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        bulbs = new boolean[N];
        end = new boolean[N];

        // start
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            bulbs[i] = input[i] == '1';
        }

        input = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            end[i] = input[i] == '1';
        }

        dfs(0, 0);

        sb.append(findAns ? answer : IMPOSSIBLE);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int i, int onCnt) {
        // i-2번째는 건드릴 수가 없는 상황 --> i-2번째가 정답과 다르면 return;
        if (i>=2 && bulbs[i-2] != end[i-2]) {
            return;
        }

        if (i == N ) {
            if (bulbs[i-1] == end[i-1]) {
                answer = Math.min(answer, onCnt);
                findAns = true;
            }
            return;
        }

        // ON
        switchBulb(i);
        dfs(i + 1, onCnt+1);

        switchBulb(i);
        dfs(i + 1, onCnt);
    }

    private static void switchBulb(int bulb) {
        for (int i = -1; i <= 1; i++) {
            if (isInRange(bulb + i)) {
                toggle(bulb + i);
            }
        }
    }

    private static void toggle(int bulb) {
        bulbs[bulb] = !bulbs[bulb];
    }

    private static boolean isInRange(int bulb) {
        return bulb >= 0 && bulb < N;
    }
}
