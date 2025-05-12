import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RScalingArbitraryTriangle extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabinx3, rabiny3;
    float rabinsx, rabinsy;
    int rabintx, rabinty;
    int rabinpx1, rabinpx2, rabinpy1, rabinpy2, rabinpx3, rabinpy3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public RScalingArbitraryTriangle() {
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
        System.out.println("Enter scaling factor sx");
        rabinsx = sc.nextFloat();
        System.out.println("Enter scaling factor sy");
        rabinsy = sc.nextFloat();
        System.out.println("Enter arbitrary point tx (relative to center)");
        rabintx = sc.nextInt();
        System.out.println("Enter arbitrary point ty (relative to center)");
        rabinty = sc.nextInt();
        sc.close();

        rabinpx1 = (int)(rabinx1 * rabinsx + rabintx * (1 - rabinsx));
        rabinpy1 = (int)(rabiny1 * rabinsy + rabinty * (1 - rabinsy));
        rabinpx2 = (int)(rabinx2 * rabinsx + rabintx * (1 - rabinsx));
        rabinpy2 = (int)(rabiny2 * rabinsy + rabinty * (1 - rabinsy));
        rabinpx3 = (int)(rabinx3 * rabinsx + rabintx * (1 - rabinsx));
        rabinpy3 = (int)(rabiny3 * rabinsy + rabinty * (1 - rabinsy));

        this.setTitle("Triangle Scaling at Arbitrary Point");
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
        g.drawString("before scaling", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_scaled_awt = toAWTCoords(rabinpx1, rabinpy1);
        Point p2_scaled_awt = toAWTCoords(rabinpx2, rabinpy2);
        Point p3_scaled_awt = toAWTCoords(rabinpx3, rabinpy3);
        g.drawLine(p1_scaled_awt.x, p1_scaled_awt.y, p2_scaled_awt.x, p2_scaled_awt.y);
        g.drawLine(p2_scaled_awt.x, p2_scaled_awt.y, p3_scaled_awt.x, p3_scaled_awt.y);
        g.drawLine(p3_scaled_awt.x, p3_scaled_awt.y, p1_scaled_awt.x, p1_scaled_awt.y);
        g.drawString("after scaling", p1_scaled_awt.x, p1_scaled_awt.y - 5);

        g.setColor(Color.GREEN);
        Point arbitrary_point_awt = toAWTCoords(rabintx, rabinty);
        g.fillOval(arbitrary_point_awt.x - 3, arbitrary_point_awt.y - 3, 6, 6);
    }

    public static void main(String[] args) {
        new RScalingArbitraryTriangle();
    }
}