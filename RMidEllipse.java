import java.util.Scanner;
import java.awt.*;

class RMidEllipse extends Frame {
    int centerX, centerY, rx, ry;

    public RMidEllipse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center:");
        centerX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center:");
        centerY = sc.nextInt();
        System.out.println("Enter the x-radius:");
        rx = sc.nextInt();
        System.out.println("Enter the y-radius:");
        ry = sc.nextInt();

        this.setTitle("Midpoint Ellipse Drawing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        drawEllipse(g, centerX, centerY, rx, ry);
    }

    private void drawEllipse(Graphics g, int centerX, int centerY, int rx, int ry) {
        double dx, dy, d1, d2;
        int x, y;

        x = 0;
        y = ry;
        d1 = ry * ry - rx * rx * ry + 0.25 * rx * rx;
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;

        while (dx < dy) {
            plotPoints(g, centerX, centerY, x, y);
            if (d1 < 0) {
                x++;
                dx = dx + 2 * ry * ry;
                d1 = d1 + dx + ry * ry;
            } else {
                x++;
                y--;
                dx = dx + 2 * ry * ry;
                dy = dy - 2 * rx * rx;
                d1 = d1 + dx - dy + ry * ry;
            }
        }

        d2 = ry * ry * (x + 0.5) * (x + 0.5) + rx * rx * (y - 1) * (y - 1) - rx * rx * ry * ry;
        while (y >= 0) {
            plotPoints(g, centerX, centerY, x, y);
            if (d2 > 0) {
                y--;
                dy = dy - 2 * rx * rx;
                d2 = d2 + rx * rx - dy;
            } else {
                y--;
                x++;
                dx = dx + 2 * ry * ry;
                dy = dy - 2 * rx * rx;
                d2 = d2 + dx - dy + rx * rx;
            }
        }
    }

    private void plotPoints(Graphics g, int centerX, int centerY, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(centerX + x, centerY + y, 1, 1);
        g.fillRect(centerX - x, centerY + y, 1, 1);
        g.fillRect(centerX + x, centerY - y, 1, 1);
        g.fillRect(centerX - x, centerY - y, 1, 1);
    }

    public static void main(String[] args) {
        new RMidEllipse();
    }
}