import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RMidpointEllipse extends Frame {
    int rabinCenterX, rabinCenterY, rabinRx, rabinRy;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RMidpointEllipse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center (relative to window center):");
        rabinCenterX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center (relative to window center):");
        rabinCenterY = sc.nextInt();
        System.out.println("Enter the x-radius (rx):");
        rabinRx = sc.nextInt();
        System.out.println("Enter the y-radius (ry):");
        rabinRy = sc.nextInt();
        sc.close();

        this.setTitle("Midpoint Ellipse Drawing");
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

        rabinDrawMidpointEllipse(g, rabinCenterX, rabinCenterY, rabinRx, rabinRy);

        g.setColor(Color.BLUE);
        Point center_awt = toAWTCoords(rabinCenterX, rabinCenterY);
        g.fillOval(center_awt.x - 4, center_awt.y - 4, 8, 8);
        g.drawString("Center (" + rabinCenterX + "," + rabinCenterY + ")", center_awt.x + 10, center_awt.y);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Ellipse Center", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Drawn Ellipse", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    private void rabinDrawMidpointEllipse(Graphics rabing, int rabinPlotCenterX_std, int rabinPlotCenterY_std, int rabinPlotRx, int rabinPlotRy) {
        double rabindx, rabindy, rabind1, rabind2;
        int rabinx_offset, rabiny_offset;

        rabinx_offset = 0;
        rabiny_offset = rabinPlotRy;
        rabind1 = rabinPlotRy * rabinPlotRy - rabinPlotRx * rabinPlotRx * rabinPlotRy + 0.25 * rabinPlotRx * rabinPlotRx;
        rabindx = 2 * rabinPlotRy * rabinPlotRy * rabinx_offset;
        rabindy = 2 * rabinPlotRx * rabinPlotRx * rabiny_offset;

        rabinPlotEllipsePoints(rabing, rabinPlotCenterX_std, rabinPlotCenterY_std, rabinx_offset, rabiny_offset);

        while (rabindx < rabindy) {
            if (rabind1 < 0) {
                rabinx_offset++;
                rabindx = rabindx + 2 * rabinPlotRy * rabinPlotRy;
                rabind1 = rabind1 + rabindx + rabinPlotRy * rabinPlotRy;
            } else {
                rabinx_offset++;
                rabiny_offset--;
                rabindx = rabindx + 2 * rabinPlotRy * rabinPlotRy;
                rabindy = rabindy - 2 * rabinPlotRx * rabinPlotRx;
                rabind1 = rabind1 + rabindx - rabindy + rabinPlotRy * rabinPlotRy;
            }
            rabinPlotEllipsePoints(rabing, rabinPlotCenterX_std, rabinPlotCenterY_std, rabinx_offset, rabiny_offset);
        }

        rabind2 = rabinPlotRy * rabinPlotRy * (rabinx_offset + 0.5) * (rabinx_offset + 0.5) + rabinPlotRx * rabinPlotRx * (rabiny_offset - 1) * (rabiny_offset - 1) - rabinPlotRx * rabinPlotRx * rabinPlotRy * rabinPlotRy;
        while (rabiny_offset >= 0) {
            rabinPlotEllipsePoints(rabing, rabinPlotCenterX_std, rabinPlotCenterY_std, rabinx_offset, rabiny_offset);
            if (rabind2 > 0) {
                rabiny_offset--;
                rabindy = rabindy - 2 * rabinPlotRx * rabinPlotRx;
                rabind2 = rabind2 + rabinPlotRx * rabinPlotRx - rabindy;
            } else {
                rabiny_offset--;
                rabinx_offset++;
                rabindx = rabindx + 2 * rabinPlotRy * rabinPlotRy;
                rabindy = rabindy - 2 * rabinPlotRx * rabinPlotRx;
                rabind2 = rabind2 + rabindx - rabindy + rabinPlotRx * rabinPlotRx;
            }
        }
    }

    private void rabinPlotEllipsePoints(Graphics rabing, int rabinPlotCenterX_std, int rabinPlotCenterY_std, int rabinPlotX_offset, int rabinPlotY_offset) {
        rabing.setColor(Color.RED);

        Point p_awt;

        p_awt = toAWTCoords(rabinPlotCenterX_std + rabinPlotX_offset, rabinPlotCenterY_std + rabinPlotY_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std - rabinPlotX_offset, rabinPlotCenterY_std + rabinPlotY_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std + rabinPlotX_offset, rabinPlotCenterY_std - rabinPlotY_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std - rabinPlotX_offset, rabinPlotCenterY_std - rabinPlotY_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);
    }

    public static void main(String[] args) {
        new RMidpointEllipse();
    }
}