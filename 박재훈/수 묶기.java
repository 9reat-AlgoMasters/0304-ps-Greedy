import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_1744 {
    static int N, cntZero;
    static List<Integer> positive, negative;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //양수 저장할 리스트
        positive = new ArrayList<>();
        //음수 저장할 리스트
        negative = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if(val > 0){
                positive.add(val);
            }else if(val < 0){
                negative.add(val);
            }else{
                //0 개수 카운트
                cntZero++;
            }
        }
        //양수는 내림차순, 음수는 오름차순
        Collections.sort(positive,Collections.reverseOrder());
        Collections.sort(negative);

        int sum = 0;

        //음수는 두수 묶어서 곱하면 양수가 되고, 큰애들끼리 곱할수록 이득
        //음수의 개수가 홀수고 수열에 0이 하나 이상 있으면 가장 큰 음수를 0과 묶어주기(곱하면 0)
        //개수가 홀수고 0이 없으면 그냥 따로 더해주기
        int nSize = negative.size();
        if(nSize % 2 == 1){
            nSize--;
            if(cntZero > 0){
                cntZero--;
            }else{
                sum += negative.get(nSize);
            }
        }
        //음수 둘을 두개씩 묶어 곱한 후 더하기
        for (int i = 0; i < nSize; i+=2) {
            sum += (negative.get(i) * negative.get(i+1));
        }

        //양수 개수가 홀수면 가장 작은 양수를 따로 더해주기
        int pSize = positive.size();
        if(pSize % 2 == 1){
            pSize--;
            sum += positive.get(pSize);
        }
        //두개씩 묶어 곱한 후 더하기
        for (int i = 0; i < pSize; i+=2) {
            //두개 묶을 때 더 작은 쪽이 1이면 곱하는 것보다 따로 더하는게 이득
            if(positive.get(i+1) == 1){
                sum += positive.get(i) + positive.get(i+1);
            }else{
                sum += (positive.get(i) * positive.get(i+1));
            }
        }

        System.out.println(sum);
    }
}
