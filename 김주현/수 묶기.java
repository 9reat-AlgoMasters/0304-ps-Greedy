import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1744 {
    static int N;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curDirPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curDirPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q1744.txt";
        setInputFile(path, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        PriorityQueue<Integer> plus = new PriorityQueue<>((a,b) -> b-a);
        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        int total = 0;
        // 0 포함 음수 처리
        while (!minus.isEmpty()) {
            int num1 = minus.poll();
            if (minus.isEmpty()) {
                total += num1;
            } else {
                total += (num1 * minus.poll());
            }
        }

        // 양수 처리
        boolean hasOne;
        while (!plus.isEmpty()) {
            int num1 = plus.poll();
            if (plus.isEmpty()) {
                total += num1;
            } else {
                int num2 = plus.poll();
                hasOne = num2 == 1; // num1이 1이면 num2도 무조건 1
                if (hasOne) {
                    total += (num1 + num2);
                } else {
                    total += (num1 * num2);
                }
            }
        }

        sb.append(total);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
