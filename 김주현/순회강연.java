import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2109 {
    static int N;
    static Lecture[] lectures;

    static class Lecture {
        int day, price;

        public Lecture(int price, int day) {
            this.price = price;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        if (N == 0) {
            System.out.println(0);
            return;
        }

        lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures, (l1, l2) -> l2.day - l1.day);
        int curDay = lectures[0].day;
        int lectureIdx = 0;

        // pq에는 curDay ~ maxDay 까지의 강연이 들어있어야 함.
        // pq의 정렬 기준 ---> 1. 가격 내림차순, 2. (가격이 같으면) day 내림차순
        PriorityQueue<Lecture> possibleLectures = new PriorityQueue<>((l1, l2) -> l1.price == l2.price ? l2.day - l1.day : l2.price - l1.price);
        int totalPrice = 0;

        while (curDay > 0) {
            // curDay 이상의 강의들을 모두 pq에 넣음
            while (lectureIdx<N && lectures[lectureIdx].day >= curDay) {
                possibleLectures.add(lectures[lectureIdx]);
                lectureIdx++;
            }

            if (possibleLectures.isEmpty()) {
                curDay--;
                continue;
            }

            Lecture now = possibleLectures.poll();
            // 만약 now의 day가 curDay보다 크거나 같다면 curDay를 하나 줄인다.
            // now의 day가 curDay보다 작다면 curDay를 now.day-1로 설정한다.
            if (now.day >= curDay) {
                curDay--;
            } else {
                curDay = now.day-1;
            }
            totalPrice += now.price;
        }

        sb.append(totalPrice);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
