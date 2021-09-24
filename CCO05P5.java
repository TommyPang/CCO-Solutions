import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * CCO '05 P5 - Segments
 * Question type: Dynamic Programming
 * 90/90 on DMOJ
 * Question URL: https://dmoj.ca/problem/cco05p5
 * @author Tommy Pang
 */
public class CCO05P5 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int [] L = new int[N+1], R = new int[N+1];
        int [] dpL = new int[N+1], dpR = new int[N+1];
        L[0] = 1; R[0] = 1;
        dpL[0] = -1; dpR[0] = -1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            L[i] = Integer.parseInt(st.nextToken());
            R[i] = Integer.parseInt(st.nextToken());
            dpL[i] = Math.min(dpL[i-1] + Math.abs(R[i]-L[i-1]), dpR[i-1] + Math.abs(R[i]-R[i-1])) + 1 + R[i]-L[i];
            dpR[i] = Math.min(dpL[i-1] + Math.abs(L[i] - L[i-1]), dpR[i-1] + Math.abs(R[i-1]-L[i])) + 1 + R[i]-L[i];
        }
        System.out.println(Math.min(N-L[N] + dpL[N], N-R[N] + dpR[N]));

    }
}
