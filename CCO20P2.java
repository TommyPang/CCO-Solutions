import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
/**
 * CCO '20 P2 - Exercise Deadlines
 * Question type: Data Structures, Greedy Algorithms
 * 25/25 on DMOJ
 * Question URL: https://dmoj.ca/problem/cco20p2
 * @author Tommy Pang
 */
public class CCO20P2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int mod = (int) 1e9+7;
    static int [] bit;
    public static void main(String[] args) throws IOException{
        int n = readInt(); bit = new int[n+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int [] order = new int[n+1];
        List<Integer> [] list = new List[n+1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            list[readInt()].add(i);
        }
        long ans = 0;
        for (int i = n; i >= 1; i--) {
            for (int nxt : list[i]) {
                pq.add(nxt);
            }
            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }
            order[i] = pq.poll();
        }
        for (int i = 1; i <= n; i++) {
            ans+=(i-1-query(order[i])); update(order[i]);
        }
        System.out.println(ans);
    }
    static void update(int idx) {
        for (int i = idx; i < bit.length; i+=(i&-i)) { bit[i]++; }
    }
    static long query(int idx) {
        long ret = 0;
        for (int i = idx; i > 0; i-=(i&-i)) { ret+=bit[i]; }
        return ret;
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
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
