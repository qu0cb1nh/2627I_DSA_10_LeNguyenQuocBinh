package homework;

import java.util.Scanner;

public class LocalMinimumOfMatrix {
    public static int[] find(int[][] a, int r1, int r2, int c1, int c2) {
        if (r1 > r2 || c1 > c2) return null;
        int rm = r1 + (r2 - r1) / 2, cm = c1 + (c2 - c1) / 2;
        int minR = rm, minC = c1;

        for (int c = c1; c <= c2; c++) if (a[rm][c] < a[minR][minC]) minC = c;
        for (int r = r1; r <= r2; r++) if (a[r][cm] < a[minR][minC]) { minR = r; minC = cm; }

        int n = a.length, minVal = a[minR][minC], nextR = minR, nextC = minC;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = minR + dr[i], nc = minC + dc[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && a[nr][nc] < minVal) {
                nextR = nr; nextC = nc; break;
            }
        }

        if (minR == nextR && minC == nextC) return new int[]{minR, minC};

        int newR1 = (nextR < rm) ? r1 : rm + 1;
        int newR2 = (nextR < rm) ? rm - 1 : r2;
        int newC1 = (nextC < cm) ? c1 : cm + 1;
        int newC2 = (nextC < cm) ? cm - 1 : c2;
        return find(a, newR1, newR2, newC1, newC2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int[] min = find(matrix, 0, n - 1, 0, n - 1);
        if (min != null) {
            System.out.println("Local minimum: " + min[0] + " " + min[1]);
        }
        sc.close();
    }
}
