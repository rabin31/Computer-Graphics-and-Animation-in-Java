import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RRotationArbitraryAntiClockwiseTriangle extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabinx3, rabiny3;
    int rabinxp, rabinyp;
    int rabinpx1, rabinpx2, rabinpy1, rabinpy2, rabinpx3, rabinpy3;
    double rabinangle;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public RRotationArbitraryAntiClockwiseTriangle() {
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
        System.out.println("Enter pivot point xp (relative to center)");
        rabinxp = sc.nextInt();
        System.out.println("Enter pivot point yp (relative to center)");
        rabinyp = sc.nextInt();
        System.out.println("Enter rotation angle (degrees anti-clockwise)");
        rabinangle = Math.toRadians(sc.nextDouble());
        sc.close();

        rabinRotateTriangle();

        this.setTitle("Triangle Rotation about Arbitrary Point (Anti-Clockwise)");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private void rabinRotateTriangle() {
        int rabinx1t = rabinx1 - rabinxp;
        int rabiny1t = rabiny1 - rabinyp;
        int rabinx2t = rabinx2 - rabinxp;
        int rabiny2t = rabiny2 - rabinyp;
        int rabinx3t = rabinx3 - rabinxp;
        int rabiny3t = rabiny3 - rabinyp;

        rabinpx1 = (int)(rabinx1t * Math.cos(rabinangle) - rabiny1t * Math.sin(rabinangle));
        rabinpy1 = (int)(rabinx1t * Math.sin(rabinangle) + rabiny1t * Math.cos(rabinangle));
        rabinpx2 = (int)(rabinx2t * Math.cos(rabinangle) - rabiny2t * Math.sin(rabinangle));
        rabinpy2 = (int)(rabinx2t * Math.sin(rabinangle) + rabiny2t * Math.cos(rabinangle));
        rabinpx3 = (int)(rabinx3t * Math.cos(rabinangle) - rabiny3t * Math.sin(rabinangle));
        rabinpy3 = (int)(rabinx3t * Math.sin(rabinangle) + rabiny3t * Math.cos(rabinangle));

        rabinpx1 += rabinxp;
        rabinpy1 += rabinyp;
        rabinpx2 += rabinxp;
        rabinpy2 += rabinyp;
        rabinpx3 += rabinxp;
        rabinpy3 += rabinyp;
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
        g.drawString("before rotation", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_rotated_awt = toAWTCoords(rabinpx1, rabinpy1);
        Point p2_rotated_awt = toAWTCoords(rabinpx2, rabinpy2);
        Point p3_rotated_awt = toAWTCoords(rabinpx3, rabinpy3);
        g.drawLine(p1_rotated_awt.x, p1_rotated_awt.y, p2_rotated_awt.x, p2_rotated_awt.y);
        g.drawLine(p2_rotated_awt.x, p2_rotated_awt.y, p3_rotated_awt.x, p3_rotated_awt.y);
        g.drawLine(p3_rotated_awt.x, p3_rotated_awt.y, p1_rotated_awt.x, p1_rotated_awt.y);
        g.drawString("after rotation", p1_rotated_awt.x, p1_rotated_awt.y - 5);

        g.setColor(Color.GREEN);
        Point pivot_awt = toAWTCoords(rabinxp, rabinyp);
        g.fillOval(pivot_awt.x - 3, pivot_awt.y - 3, 6, 6);
    }

    public static void main(String[] args) {
        new RRotationArbitraryAntiClockwiseTriangle();
    }
}