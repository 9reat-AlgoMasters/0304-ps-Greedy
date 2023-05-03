import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2212 {
    static int N, M;
    static int[] arr;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q2212.txt";
        setInputFile(remainPath, fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if (M >= N) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int total = arr[N-1] - arr[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 1; i < N; i++) {
            pq.add(arr[i] - arr[i - 1]);
        }

        while (M-- > 1) {
            total -= pq.poll();
        }

        sb.append(total);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
