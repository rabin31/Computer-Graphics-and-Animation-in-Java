import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RTranslation extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2, rabintx, rabinty;
    int rabinpx1, rabinpy1, rabinpx2, rabinpy2;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public RTranslation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1 (relative to center)");
        rabinx1 = sc.nextInt();
        System.out.println("Enter y1 (relative to center)");
        rabiny1 = sc.nextInt();
        System.out.println("Enter x2 (relative to center)");
        rabinx2 = sc.nextInt();
        System.out.println("Enter y2 (relative to center)");
        rabiny2 = sc.nextInt();
        System.out.println("Enter tx (translation in x)");
        rabintx = sc.nextInt();
        System.out.println("Enter ty (translation in y)");
        rabinty = sc.nextInt();
        sc.close();

        rabinpx1 = rabinx1 + rabintx;
        rabinpy1 = rabiny1 + rabinty;
        rabinpx2 = rabinx2 + rabintx;
        rabinpy2 = rabiny2 + rabinty;

        this.setTitle("Line Translation in Quadrants");
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
        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawString("before translation", p1_orig_awt.x, p1_orig_awt.y - 5);


        g.setColor(Color.RED);
        Point p1_trans_awt = toAWTCoords(rabinpx1, rabinpy1);
        Point p2_trans_awt = toAWTCoords(rabinpx2, rabinpy2);
        g.drawLine(p1_trans_awt.x, p1_trans_awt.y, p2_trans_awt.x, p2_trans_awt.y);
        g.drawString("after translation", p1_trans_awt.x, p1_trans_awt.y - 5);
    }

    public static void main(String[] args) {
        new RTranslation();
    }
}