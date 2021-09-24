import java.io.*;
import java.util.*;
/**
 * CCO '00 P1 - Subsets
 * Question type: Graph Theory
 * 5/5 on DMOJ
 * Question URL: https://dmoj.ca/problem/cco00p1
 * @author Tommy Pang
 */
public class CCO00P1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    static int mod = (int) 1e9+7;
    static Set<Character>[] setElement = new HashSet[26];
    static List<Character> [] adj = new List[26];
    static boolean [] vis = new boolean[26];
    static boolean [] appeared = new boolean[26];
    public static void main(String[] args) throws IOException{
        int n = readInt();
        for (int i = 0; i < 26; i++) {
            setElement[i] = new HashSet<>();
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            String s = readLine();
            char fir = s.charAt(0), sec = s.charAt(s.length()-1);
            appeared[fir-'A'] = true;
            if (Character.isUpperCase(sec)) {
                adj[sec-'A'].add(fir);
                appeared[sec-'A'] = true;
            }
            else setElement[fir-'A'].add(sec);
        }

        for (int i = 0; i < 26; i++) {
            vis = new boolean[26];
            DFS(i);
        }
        char cur = 'A';
        for (int i = 0; i < 26; i++) {
            if (appeared[i]) {
                System.out.print((char) cur + " = {");
                List<Character> list = new ArrayList<>(setElement[cur-'A']);
                Collections.sort(list);
                for (int j = 0; j < list.size()-1; j++) {
                    System.out.print(list.get(j) + ",");
                }
                if (list.size()!=0) {
                    System.out.print(list.get(list.size()-1));
                }
                System.out.print("}");
                System.out.print("\n");
            }
            cur++;
        }
    }
    static void DFS(int cur) {
        vis[cur] = true;
        for (Character nxt : adj[cur]) {
            if (!vis[nxt-'A']) {
                setElement[nxt-'A'].addAll(setElement[cur]);
                DFS(nxt-'A');
            }
        }
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