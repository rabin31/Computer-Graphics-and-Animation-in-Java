import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RReflectionArbitraryLineTriangle extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabinx3, rabiny3;
    double rabinm, rabinc;
    int rabinrx1, rabinry1, rabinrx2, rabinry2, rabinrx3, rabinry3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public RReflectionArbitraryLineTriangle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1 (relative to center)");
        rabinx1 = sc.nextInt();
        System.out.println("Enter y1 (relative to center)");
        rabiny1 = sc.nextInt();
        System.out.println("Enter x2 (relative to center)");
        rabinx2 = sc.nextInt();
        System.out.println("Enter y2 (relative to center)");
        rabiny2 = sc.nextInt();
        System.out.println("Enter x3 (relative to center)");
        rabinx3 = sc.nextInt();
        System.out.println("Enter y3 (relative to center)");
        rabiny3 = sc.nextInt();
        System.out.println("Enter slope m of the reflection line y = mx + c (relative to center)");
        rabinm = sc.nextDouble();
        System.out.println("Enter intercept c of the reflection line y = mx + c (relative to center)");
        rabinc = sc.nextDouble();
        sc.close();

        System.out.println("\n--- Input Values (Relative to Center) ---");
        System.out.println("Original Point 1: (" + rabinx1 + ", " + rabiny1 + ")");
        System.out.println("Original Point 2: (" + rabinx2 + ", " + rabiny2 + ")");
        System.out.println("Original Point 3: (" + rabinx3 + ", " + rabiny3 + ")");
        System.out.println("Reflection Line: y = " + rabinm + "x + " + rabinc);

        rabinReflectTriangle();

        System.out.println("\n--- Calculated Reflected Values (Relative to Center) ---");
        System.out.println("Reflected Point 1: (" + rabinrx1 + ", " + rabinry1 + ")");
        System.out.println("Reflected Point 2: (" + rabinrx2 + ", " + rabinry2 + ")");
        System.out.println("Reflected Point 3: (" + rabinrx3 + ", " + rabinry3 + ")");
        System.out.println("---------------------------------------------------------");


        this.setTitle("Reflection Through Arbitrary Line y = mx + c");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void rabinReflectTriangle() {
        double rabinx_int1, rabiny_int1;
        if (rabinm == 0) {
            rabinrx1 = rabinx1;
            rabinry1 = (int) Math.round(2 * rabinc - rabiny1);
        } else {
            rabinx_int1 = (rabinx1 + rabinm * rabiny1 - rabinm * rabinc) / (1 + rabinm * rabinm);
            rabiny_int1 = rabinm * rabinx_int1 + rabinc;
            rabinrx1 = (int) Math.round(2 * rabinx_int1 - rabinx1);
            rabinry1 = (int) Math.round(2 * rabiny_int1 - rabiny1);
        }

        double rabinx_int2, rabiny_int2;
        if (rabinm == 0) {
            rabinrx2 = rabinx2;
            rabinry2 = (int) Math.round(2 * rabinc - rabiny2);
        } else {
            rabinx_int2 = (rabinx2 + rabinm * rabiny2 - rabinm * rabinc) / (1 + rabinm * rabinm);
            rabiny_int2 = rabinm * rabinx_int2 + rabinc;
            rabinrx2 = (int) Math.round(2 * rabinx_int2 - rabinx2);
            rabinry2 = (int) Math.round(2 * rabiny_int2 - rabiny2);
        }

        double rabinx_int3, rabiny_int3;
        if (rabinm == 0) {
            rabinrx3 = rabinx3;
            rabinry3 = (int) Math.round(2 * rabinc - rabiny3);
        } else {
            rabinx_int3 = (rabinx3 + rabinm * rabiny3 - rabinm * rabinc) / (1 + rabinm * rabinm);
            rabiny_int3 = rabinm * rabinx_int3 + rabinc;
            rabinrx3 = (int) Math.round(2 * rabinx_int3 - rabinx3);
            rabinry3 = (int) Math.round(2 * rabiny_int3 - rabiny3);
        }
    }

    private Point toAWTCoords(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return new Point(centerX + x, centerY - y);
    }

    public void paint(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
        g.drawLine(centerX + 3, 10, centerX, 0);

        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(rabinx1, rabiny1);
        Point p2_orig_awt = toAWTCoords(rabinx2, rabiny2);
        Point p3_orig_awt = toAWTCoords(rabinx3, rabiny3);
        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("before reflection", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_reflected_awt = toAWTCoords(rabinrx1, rabinry1);
        Point p2_reflected_awt = toAWTCoords(rabinrx2, rabinry2);
        Point p3_reflected_awt = toAWTCoords(rabinrx3, rabinry3);
        g.drawLine(p1_reflected_awt.x, p1_reflected_awt.y, p2_reflected_awt.x, p2_reflected_awt.y);
        g.drawLine(p2_reflected_awt.x, p2_reflected_awt.y, p3_reflected_awt.x, p3_reflected_awt.y);
        g.drawLine(p3_reflected_awt.x, p3_reflected_awt.y, p1_reflected_awt.x, p1_reflected_awt.y);
        g.drawString("after reflection", p1_reflected_awt.x, p1_reflected_awt.y - 5);

        g.setColor(Color.GREEN);
        int x_start_std = -getWidth() / 2;
        int x_end_std = getWidth() / 2;

        double y_start_std = rabinm * x_start_std + rabinc;
        double y_end_std = rabinm * x_end_std + rabinc;

        Point p_start_awt = toAWTCoords(x_start_std, (int) Math.round(y_start_std));
        Point p_end_awt = toAWTCoords(x_end_std, (int) Math.round(y_end_std));

        g.drawLine(p_start_awt.x, p_start_awt.y, p_end_awt.x, p_end_awt.y);
        g.drawString("Line y = " + rabinm + "x + " + rabinc, p_start_awt.x + 10, p_start_awt.y + (rabinm > 0 ? 15 : -5));
    }

    public static void main(String[] args) {
        new RReflectionArbitraryLineTriangle();
    }
}