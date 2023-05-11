import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.MathContext;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int max;
	static Lecture lectures[];
	static class Lecture implements Comparable<Lecture>{
		int day;
		int pay;
		@Override
		public int compareTo(Lecture o) {
			
			if(this.pay - o.pay == 0) { //페이가 같다면 
				return -(this.day-o.day); //기한이 긴 날부터 나열
			}
			return -(this.pay - o.pay); //페이가 큰 순서대로 나열 
		}
		@Override
		public String toString() {
			return "lecture [day=" + day + ", pay=" + pay + "]";
		}
		public Lecture(int day, int pay) {
			super();
			this.day = day;
			this.pay = pay;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N == 0) { //요청한 강연이 없을때 처리
			System.out.println(0);
			System.exit(0);
		}
		
		lectures = new Lecture[N];
		
		int spare =0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			spare = Math.max(spare, day); //가장 긴 기한을 찾아 저장
			lectures[i] = new Lecture(day, pay);
			
		}
		
		Arrays.sort(lectures);
		int schedule[] = new int[spare+1];  //가장 기한이 긴날까지 스케쥴을 짠다.
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(lectures[i].toString());
//		}
		
	
		//페이가 큰 것 부터 차례대로 살펴보개
		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(schedule));
			
			//그날 스케쥴이 비어있지 않다면 
 			if(schedule[lectures[i].day] != 0) {
 				//기한내 스케쥴이 빈날 찾기
 				for (int j = lectures[i].day-1; j >=1; j--) {
 					//기한 내 스케쥴이 빈 날을 찾으면 
 					if(schedule[j] == 0) {
 						//스케쥴 등록하고 max에 누적
 						schedule[j] = lectures[i].pay;
 		 	 			max += lectures[i].pay;
 						break;
 					}	
				}
 			}else { //그날 스케쥴이 비어 있다면 스케쥴 등록하고 max 누적
 	 			schedule[lectures[i].day] = lectures[i].pay;
 	 			max += lectures[i].pay;
 			}
 			
		}
		
		System.out.println(max);
		
		
	}

}