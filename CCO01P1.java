import java.io.*;
import java.util.*;
/**
 * CCO '01 P1 - The Monkey Dance
 * Question type: Implementation
 * 25/25 on DMOJ
 * Question URL: https://dmoj.ca/problem/cco01p1
 * @author Tommy Pang
 */
public class CCO01P1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int mod = (int) 1e9+7;
    static List<Integer> [] adj;
    static boolean [] vis;
    static List<List<Integer>> scc;
    public static void main(String[] args) throws IOException{
        int n = readInt();
        while (n!=0) {
            adj = new List[n+1]; vis = new boolean[n+1];
            scc = new ArrayList<>();
            for (int i = 1; i < n+1; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                int a = readInt(), b = readInt();
                adj[a].add(b);
            }
            for (int i = 1; i <= n; i++) {
                if (!vis[i]) {
                    DFS(i, new ArrayList<>());
                }
            }
            int lcm = 1;
            for (List<Integer> nxt : scc) {
                lcm = lcm*nxt.size()/gcd(lcm, nxt.size());
            }
            System.out.println(lcm);
            n = readInt();
        }
    }
    static boolean DFS(int cur, List<Integer> path) {
        if (vis[cur]) {
            scc.add(new ArrayList<>(path));
            return true;
        }
        vis[cur] = true;
        for (int nxt : adj[cur]) {
            path.add(nxt);
            if (DFS(nxt, path)) return true;
            path.remove(path.size()-1);
        }
        return false;
    }
    static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
    static String readLine() throws IOException {
        return br.readLine().trim();
    }
    static int readLongLineInt() throws IOException{
        int x = 0, c;
        while((c = br.read()) != ' ' && c != '\n')
            x = x * 10 + (c - '0');
        return x;
    }
    static long pow (long x, long exp){
        if (exp==0) return 1;
        long t = pow(x, exp/2);
        t = t*t %mod;
        if (exp%2 == 0) return t;
        return t*x%mod;
    }
}
