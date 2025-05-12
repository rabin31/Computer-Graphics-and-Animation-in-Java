import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RDDALine extends Frame {
    int rabinx1, rabiny1, rabinx2, rabiny2;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RDDALine() {
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

        this.setTitle("DDA Line Drawing");
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

    private void rabinDrawDDALine(Graphics g, Point p1_awt, Point p2_awt) {
        int rabindx = p2_awt.x - p1_awt.x;
        int rabindy = p2_awt.y - p1_awt.y;

        int rabinsteps;
        if (Math.abs(rabindx) > Math.abs(rabindy)) {
            rabinsteps = Math.abs(rabindx);
        } else {
            rabinsteps = Math.abs(rabindy);
        }

         if (rabinsteps == 0) {
             g.fillRect(p1_awt.x, p1_awt.y, 1, 1);
             return;
         }


        double rabinxIncrement = (double) rabindx / rabinsteps;
        double rabinyIncrement = (double) rabindy / rabinsteps;

        double rabincurrentX = p1_awt.x;
        double rabincurrentY = p1_awt.y;

        for (int rabini = 0; rabini <= rabinsteps; rabini++) {
            g.fillRect((int) Math.round(rabincurrentX), (int) Math.round(rabincurrentY), 1, 1);
            rabincurrentX += rabinxIncrement;
            rabincurrentY += rabinyIncrement;
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
        rabinDrawDDALine(g, p1_awt, p2_awt);

        g.setColor(Color.GREEN);

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
        g.drawString("DDA Drawn Line", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    public static void main(String[] args) {
        new RDDALine();
    }
}