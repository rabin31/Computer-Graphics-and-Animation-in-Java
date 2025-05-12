import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RMidpointCircle extends Frame {
    int rabinCenterX, rabinCenterY, rabinRadius;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RMidpointCircle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center (relative to window center):");
        rabinCenterX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center (relative to window center):");
        rabinCenterY = sc.nextInt();
        System.out.println("Enter the radius of the circle:");
        rabinRadius = sc.nextInt();
        sc.close();

        this.setTitle("Midpoint Circle Drawing");
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

        rabinDrawMidpointCircle(g, rabinCenterX, rabinCenterY, rabinRadius);

        g.setColor(Color.BLUE);
        Point center_awt = toAWTCoords(rabinCenterX, rabinCenterY);
        g.fillOval(center_awt.x - 4, center_awt.y - 4, 8, 8);
        g.drawString("Center (" + rabinCenterX + "," + rabinCenterY + ")", center_awt.x + 10, center_awt.y);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Circle Center", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Drawn Circle", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    private void rabinDrawMidpointCircle(Graphics rabing, int rabinPlotCenterX_std, int rabinPlotCenterY_std, int rabinPlotRadius) {
        int rabinx_offset = rabinPlotRadius;
        int rabiny_offset = 0;
        int rabinp = 1 - rabinPlotRadius;

        rabinPlotCirclePoints(rabing, rabinPlotCenterX_std, rabinPlotCenterY_std, rabinx_offset, rabiny_offset);

        while (rabinx_offset > rabiny_offset) {
            rabiny_offset++;
            if (rabinp <= 0) {
                rabinp = rabinp + 2 * rabiny_offset + 1;
            } else {
                rabinx_offset--;
                rabinp = rabinp + 2 * rabiny_offset - 2 * rabinx_offset + 1;
            }
            rabinPlotCirclePoints(rabing, rabinPlotCenterX_std, rabinPlotCenterY_std, rabinx_offset, rabiny_offset);
        }
    }

    private void rabinPlotCirclePoints(Graphics rabing, int rabinPlotCenterX_std, int rabinPlotCenterY_std, int rabinPlotX_offset, int rabinPlotY_offset) {
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

        p_awt = toAWTCoords(rabinPlotCenterX_std + rabinPlotY_offset, rabinPlotCenterY_std + rabinPlotX_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std - rabinPlotY_offset, rabinPlotCenterY_std + rabinPlotX_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std + rabinPlotY_offset, rabinPlotCenterY_std - rabinPlotX_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(rabinPlotCenterX_std - rabinPlotY_offset, rabinPlotCenterY_std - rabinPlotX_offset);
        rabing.fillRect(p_awt.x, p_awt.y, 1, 1);
    }

    public static void main(String[] args) {
        new RMidpointCircle();
    }
}