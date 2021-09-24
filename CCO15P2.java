import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * CCO '15 P2 - Artskjid
 * Question type: Dynamic Programming, Graph Theory
 * 50/50 on DMOJ
 * Question URL: https://dmoj.ca/problem/cco15p2
 * @author Tommy Pang
 */
public class CCO15P2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<edge> [] adj;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N];
        dp = new int[N][1<<N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            adj[a].add(new edge(b, w));
        }
        System.out.println(fun(0, 1));
    }
    static int fun(int u, int mask){
        if(dp[u][mask] >= 0) return dp[u][mask];
        if(u == N-1) return 0;
        for(edge e: adj[u]) {
            int v = e.v, w = e.w;
            if((mask >> v & 1) == 0) dp[u][mask] = Math.max(dp[u][mask], w + fun(v, mask|1<<v));
        }
        return dp[u][mask];
    }
    static class edge {
        int v, w;
        edge(int u, int v){
            this.v = u; this.w = v;
        }
    }
}
