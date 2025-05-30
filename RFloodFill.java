import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class RDrawFilledRectangle extends Frame {
    int rabinCenterX, rabinCenterY;
    int rabinRectWidth, rabinRectHeight;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RDrawFilledRectangle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the rectangle center (relative to window center):");
        rabinCenterX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the rectangle center (relative to window center):");
        rabinCenterY = sc.nextInt();
        System.out.println("Enter the width of the rectangle:");
        rabinRectWidth = sc.nextInt();
        System.out.println("Enter the height of the rectangle:");
        rabinRectHeight = sc.nextInt();
        sc.close();

        this.setTitle("Draw Filled Rectangle");
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

        // Calculate the top-left corner of the rectangle in standard coordinates
        int rectTopLeftX_std = rabinCenterX - rabinRectWidth / 2;
        int rectTopLeftY_std = rabinCenterY + rabinRectHeight / 2; // Y is inverted in AWT

        // Convert the top-left corner to AWT coordinates
        Point rectTopLeft_awt = toAWTCoords(rectTopLeftX_std, rectTopLeftY_std);

        // Draw the filled rectangle
        g.setColor(Color.RED);
        g.fillRect(rectTopLeft_awt.x, rectTopLeft_awt.y, rabinRectWidth, rabinRectHeight);

        // Draw the center marker
        g.setColor(Color.BLUE);
        Point center_awt = toAWTCoords(rabinCenterX, rabinCenterY);
        g.fillOval(center_awt.x - 4, center_awt.y - 4, 8, 8);
        g.drawString("Center (" + rabinCenterX + "," + rabinCenterY + ")", center_awt.x + 10, center_awt.y);


        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Rectangle Center", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Drawn Filled Rectangle", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    public static void main(String[] args) {
        new RDrawFilledRectangle();
    }
}