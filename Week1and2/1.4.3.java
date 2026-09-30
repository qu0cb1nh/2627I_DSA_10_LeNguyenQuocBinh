/******************************************************************************
 *  Compilation:  javac DoublingTest.java
 *  Execution:    java DoublingTest
 *  Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java StdDraw.java
 ******************************************************************************/

package edu.princeton.cs.algs4;

import java.util.ArrayList;

public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    // Hàm phụ trợ tính Log cơ số 2 (chuẩn theo sách Algs4)
    private static double lg(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String[] args) {
        ArrayList<Double> ns = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        // Mở rộng cửa sổ ra hình chữ nhật ngang
        StdDraw.setCanvasSize(1000, 500);

        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, time);

            ns.add((double) n);
            times.add(Math.max(time, 0.001)); // Tránh lỗi lg(0)

            drawDashboard(ns, times);
        }
    }

    private static void drawDashboard(ArrayList<Double> ns, ArrayList<Double> times) {
        StdDraw.clear();

        // Tọa độ không gian cố định chứa cả 2 không gian vẽ
        StdDraw.setXscale(-0.1, 2.2);
        StdDraw.setYscale(-0.2, 1.2);

        int size = ns.size();
        double maxN = ns.get(size - 1);
        double maxT = times.get(size - 1);

        double minLgN = lg(ns.get(0));
        double maxLgN = lg(maxN);
        if (maxLgN == minLgN) maxLgN = minLgN + 1; // Khắc phục lỗi chia 0 ở vòng lặp đầu

        double minLgT = lg(times.get(0));
        double maxLgT = lg(maxT);
        if (maxLgT == minLgT) maxLgT = minLgT + 1;

        // ==========================================
        // 1. VẼ KHUNG, TRỤC VÀ ĐIỀN SỐ
        // ==========================================
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);

        // --- ĐỒ THỊ CHUẨN (Nửa Trái) ---
        StdDraw.line(0.0, 0.0, 0.9, 0.0); // Trục X
        StdDraw.line(0.0, 0.0, 0.0, 1.0); // Trục Y
        StdDraw.text(0.45, 1.1, "Standard Plot: T(N) vs N"); // Tiêu đề
        StdDraw.text(0.45, -0.15, "N (Input Size)");          // Nhãn X
        StdDraw.text(-0.08, 0.5, "T(N)", 90);                 // Nhãn Y (xoay dọc)

        // Ghi số mốc min/max
        StdDraw.text(0.0, -0.05, "0");
        StdDraw.text(0.9, -0.05, String.format("%.0f", maxN));
        StdDraw.text(-0.05, 0.0, "0");
        StdDraw.text(-0.05, 1.0, String.format("%.1f", maxT));

        // --- ĐỒ THỊ LOG-LOG (Nửa Phải) ---
        StdDraw.line(1.2, 0.0, 2.1, 0.0); // Trục X
        StdDraw.line(1.2, 0.0, 1.2, 1.0); // Trục Y
        StdDraw.text(1.65, 1.1, "Log-Log Plot: lg(T(N)) vs lg(N)");
        StdDraw.text(1.65, -0.15, "lg(N)");
        StdDraw.text(1.12, 0.5, "lg(T(N))", 90);

        // Ghi số mốc min/max
        StdDraw.text(1.2, -0.05, String.format("%.1f", minLgN));
        StdDraw.text(2.1, -0.05, String.format("%.1f", maxLgN));
        StdDraw.text(1.15, 0.0, String.format("%.1f", minLgT));
        StdDraw.text(1.15, 1.0, String.format("%.1f", maxLgT));

        // ==========================================
        // 2. VẼ CÁC ĐIỂM DỮ LIỆU BÊN TRONG TRỤC
        // ==========================================
        for (int i = 0; i < size; i++) {
            double n = ns.get(i);
            double t = times.get(i);

            // Ép tỷ lệ tọa độ vào khoảng 0.0 -> 0.9
            double x1 = 0.0 + 0.9 * (n / maxN);
            double y1 = 0.0 + 1.0 * (t / maxT);

            // Ép tỷ lệ tọa độ vào khoảng 1.2 -> 2.1
            double lgn = lg(n);
            double lgt = lg(t);
            double x2 = 1.2 + 0.9 * ((lgn - minLgN) / (maxLgN - minLgN));
            double y2 = 0.0 + 1.0 * ((lgt - minLgT) / (maxLgT - minLgT));

            // Vẽ nút tròn đỏ
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x1, y1, 0.012);
            StdDraw.filledCircle(x2, y2, 0.012);

            // Vẽ đường nối đen
            if (i > 0) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.002);

                double prevN = ns.get(i-1);
                double prevT = times.get(i-1);

                double prevX1 = 0.0 + 0.9 * (prevN / maxN);
                double prevY1 = 0.0 + 1.0 * (prevT / maxT);
                StdDraw.line(prevX1, prevY1, x1, y1);

                double prevLgN = lg(prevN);
                double prevLgT = lg(prevT);
                double prevX2 = 1.2 + 0.9 * ((prevLgN - minLgN) / (maxLgN - minLgN));
                double prevY2 = 0.0 + 1.0 * ((prevLgT - minLgT) / (maxLgT - minLgT));
                StdDraw.line(prevX2, prevY2, x2, y2);
            }
        }
    }
}