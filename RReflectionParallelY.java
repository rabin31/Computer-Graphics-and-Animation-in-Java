import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RReflectionParallelYTriangle extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabinx3, rabiny3;
    int rabina;
    int rabinrx1, rabinry1, rabinrx2, rabinry2, rabinrx3, rabinry3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public RReflectionParallelYTriangle() {
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
        System.out.println("Enter x-coordinate (a) of reflection axis x = a (relative to center)");
        rabina = sc.nextInt();
        sc.close();

        rabinrx1 = 2 * rabina - rabinx1;
        rabinry1 = rabiny1;
        rabinrx2 = 2 * rabina - rabinx2;
        rabinry2 = rabiny2;
        rabinrx3 = 2 * rabina - rabinx3;
        rabinry3 = rabiny3;

        this.setTitle("Reflection Through Axis Parallel to Y-axis (x = a)");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
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
        int axisAwtX = centerX + rabina;
        g.drawLine(axisAwtX, 0, axisAwtX, getHeight());
        g.drawString("Axis x = " + rabina, axisAwtX + 5, 20);
    }

    public static void main(String[] args) {
        new RReflectionParallelYTriangle();
    }
}