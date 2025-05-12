import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RParametricCircle extends Frame {
    int rabinCenterX, rabinCenterY, rabinRadius;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RParametricCircle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center (relative to window center):");
        rabinCenterX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center (relative to window center):");
        rabinCenterY = sc.nextInt();
        System.out.println("Enter the radius of the circle:");
        rabinRadius = sc.nextInt();
        sc.close();

        this.setTitle("Parametric Circle Drawing");
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

        g.setColor(Color.RED);
        for (double rabinangle = 0; rabinangle <= 2 * Math.PI; rabinangle += 0.001) {
            int rabinx_std = (int) Math.round(rabinCenterX + rabinRadius * Math.cos(rabinangle));
            int rabiny_std = (int) Math.round(rabinCenterY + rabinRadius * Math.sin(rabinangle));

            Point p_awt = toAWTCoords(rabinx_std, rabiny_std);
            g.fillRect(p_awt.x, p_awt.y, 1, 1);
        }

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

    public static void main(String[] args) {
        new RParametricCircle();
    }
}