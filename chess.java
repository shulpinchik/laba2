public class chess {
    static int total = 0;
    public static void main(String[] args) {
        int n=8;
        queen(n);
        System.out.println("\nВсего решений: " + total);
    }
    static int[] recQueen(int[] p, int k) {
        int n = p.length;
        if (k == n) return p;
        for (int j = 1; j <= n; j++) {
            boolean correct = true;
            for (int i = 0; i < k; i++) {
                if (p[i] == j || k - i == Math.abs(j - p[i])) {
                  correct = false;
                    break;
                }
            }
            if (correct) {
                p[k] = j ;
                int[] pos = recQueen(p, k+1);
                if (pos != null) {
                    total++;
                    printBoard(pos);
                }
            }
        }
        return null;
    }

    static void queen(int n) {
        recQueen(new int[n], 0);
    }

    static void printBoard(int[] pos) {
        System.out.println("\nРешение №" + total );
        for (int queenPos : pos) {
            for (int k = 1; k < queenPos; k++) {
                System.out.print("_ ");
            }
            System.out.print("Q ");
            for (int k = queenPos + 1; k <= pos.length; k++) {
                System.out.print("_ ");
            }
            System.out.print("\n");
        }
    }
}
