import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RBresenhamLine extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RBresenhamLine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1 (relative to center)");
        rabinx1 = sc.nextInt();
        System.out.println("Enter y1 (relative to center)");
        rabiny1 = sc.nextInt();
        System.out.println("Enter x2 (relative to center)");
        rabinx2 = sc.nextInt();
        System.out.println("Enter y2 (relative to center)");
        rabiny2 = sc.nextInt();
        sc.close();

        this.setTitle("Bresenham's Line Drawing");
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

    private void rabinDrawBresenhamLine(Graphics g, Point p1_awt, Point p2_awt) {
        int rabincurrentX = p1_awt.x;
        int rabincurrentY = p1_awt.y;

        int rabindx = Math.abs(p2_awt.x - rabincurrentX);
        int rabindy = Math.abs(p2_awt.y - rabincurrentY);

        int rabinsx_step = (rabincurrentX < p2_awt.x) ? 1 : -1;
        int rabinsy_step = (rabincurrentY < p2_awt.y) ? 1 : -1;

        int rabinerr = rabindx - rabindy;

        while (true) {
            g.fillRect(rabincurrentX, rabincurrentY, 1, 1);

            if (rabincurrentX == p2_awt.x && rabincurrentY == p2_awt.y) {
                break;
            }

            int rabine2 = 2 * rabinerr;

            if (rabine2 > -rabindy) {
                rabinerr -= rabindy;
                rabincurrentX += rabinsx_step;
            }
            if (rabine2 < rabindx) {
                rabinerr += rabindx;
                rabincurrentY += rabinsy_step;
            }
        }
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

        Point p1_awt = toAWTCoords(rabinx1, rabiny1);
        Point p2_awt = toAWTCoords(rabinx2, rabiny2);

        g.setColor(Color.BLUE);
        g.fillOval(p1_awt.x - 3, p1_awt.y - 3, 6, 6);
        g.fillOval(p2_awt.x - 3, p2_awt.y - 3, 6, 6);
        g.drawString("Point 1", p1_awt.x + 5, p1_awt.y);
        g.drawString("Point 2", p2_awt.x + 5, p2_awt.y);

        g.setColor(Color.RED);
        rabinDrawBresenhamLine(g, p1_awt, p2_awt);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Original Points", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Drawn Line", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);
    }

    public static void main(String[] args) {
        new RBresenhamLine();
    }
}