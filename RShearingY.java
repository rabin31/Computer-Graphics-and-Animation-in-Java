import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RShearingYTriangle extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabinx3, rabiny3;
    double rabinshy;
    int rabinsx1, rabinsy1, rabinsx2, rabinsy2, rabinsx3, rabinsy3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RShearingYTriangle() {
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
        System.out.println("Enter shearing factor in y-direction (shy)");
        rabinshy = sc.nextDouble();
        sc.close();

        rabinsx1 = rabinx1;
        rabinsy1 = (int) Math.round(rabiny1 + rabinshy * rabinx1);
        rabinsx2 = rabinx2;
        rabinsy2 = (int) Math.round(rabiny2 + rabinshy * rabinx2);
        rabinsx3 = rabinx3;
        rabinsy3 = (int) Math.round(rabiny3 + rabinshy * rabinx3);

        this.setTitle("Shearing in Y-Direction");
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
        g.drawString("before shearing", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_sheared_awt = toAWTCoords(rabinsx1, rabinsy1);
        Point p2_sheared_awt = toAWTCoords(rabinsx2, rabinsy2);
        Point p3_sheared_awt = toAWTCoords(rabinsx3, rabinsy3);
        g.drawLine(p1_sheared_awt.x, p1_sheared_awt.y, p2_sheared_awt.x, p2_sheared_awt.y);
        g.drawLine(p2_sheared_awt.x, p2_sheared_awt.y, p3_sheared_awt.x, p3_sheared_awt.y);
        g.drawLine(p3_sheared_awt.x, p3_sheared_awt.y, p1_sheared_awt.x, p1_sheared_awt.y);
        g.drawString("after shearing", p1_sheared_awt.x, p1_sheared_awt.y - 5);

        g.setColor(Color.GREEN);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Original Triangle", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Sheared Triangle", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border", legendX, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    public static void main(String[] args) {
        new RShearingYTriangle();
    }
}