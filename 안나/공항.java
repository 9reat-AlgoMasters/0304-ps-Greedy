import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static final int CONNECT = 1;
	static int G; //게이트의 수 
	static int P; //비행기의 수 
	static int gate[]; //각 비행기가 1번부터 몇번까지 
	static int parent[];
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		

		gate = new int[P];
		
		for (int i = 0; i < P; i++) {
			gate[i] = Integer.parseInt(br.readLine());
		}
		
		parent = new int [G+1]; //1~i번째 도킹을 허용하는 비행기가 들어왓을때
		//1~i번째 게이트중 연결할 수 있는 가장 큰 번호를 가지는 게이트 번호를 기록한다.
		for (int i = 1; i < G+1; i++) {
			parent[i] = i;  //처음에는 1~i번째 중 i번이 가장 큰 번호를 가지는 게이트 번호이니까 i를 기록
		}
		
		
		for (int i = 0; i < P; i++) {
			if(find(gate[i]) == 0) {
				break;
			}
			
			//1~gate[i]번 게이트중 가장 큰 번호를 가지는 게이트 번호 찾기]
			//gate[i]를 3이라고 예시를 들어보자.
			//그럼 1~3번 게이트 중 가장 큰 번호를 가지는 게이트 번호를 반환한다.
			//3번 게이트에 연결한 비행기가 없다면 반환할 번호는 3이겠지만
			//3번 게이트에 비행기가 도킹 되어 있다면 2번이 반환될 수도 1번이 반환될 수도 있다.
			int p = find(gate[i]); 
			//p번 게이트에 i번에 들어온 비행기를 도킹 시킴
			
			//만약 3번 게이트에 도킹한 비행기가 없어서 3이 반환 되었다면 p는 3이었을 것이다.
			//그럼 이제 3번 게이트에는 연결 할 수 없으니 
			//다음번에 또 1~3번 게이트 중 도킹할 비행기가 들어온다면 2(p-1)번 게이트가 반환 될 것이다.
			//**하지만 이때 2번 게이트가 이전에 이미 도킹되어 있다면 1~2번 게이트 중 도킹할 비행기가 들어올때
			//도킹할 수 있는 게이트를 반환할 것이다. (1번이 도킹되어 있지 않다면 1번게이트가 반환된다.)
			
			//이때 1~(p-1)번 게이트 중 도킹할 비행기가 들어온다면 반환할 게이트번호를 찾는 과정이
			//find(p-1)이다.
			parent[p] = find(p-1);
			count ++;
		}
		
		System.out.println(count);	
	}
	
	
	//부모를 찾으며 경로 압축
	static int find(int x) {
		if(x == parent[x]) return x;
		else return parent[x] = find(parent[x]);
	}

}