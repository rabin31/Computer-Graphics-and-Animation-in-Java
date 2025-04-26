import java.util.Scanner;
import java.awt.*;

class RMidCircle extends Frame {
    int centerX, centerY, radius;

    public RMidCircle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center:");
        centerX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center:");
        centerY = sc.nextInt();
        System.out.println("Enter the radius of the circle:");
        radius = sc.nextInt();

        this.setTitle("Midpoint Circle Drawing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        drawCircle(g, centerX, centerY, radius);
    }

    private void drawCircle(Graphics g, int centerX, int centerY, int radius) {
        int x = radius;
        int y = 0;
        int p = 1 - radius;

        drawCirclePoints(g, centerX, centerY, x, y);

        while (x > y) {
            y++;
            if (p <= 0) {
                p = p + 2 * y + 1;
            } else {
                x--;
                p = p + 2 * y - 2 * x + 1;
            }
            drawCirclePoints(g, centerX, centerY, x, y);
        }
    }

    private void drawCirclePoints(Graphics g, int centerX, int centerY, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(centerX + x, centerY + y, 1, 1);
        g.fillRect(centerX - x, centerY + y, 1, 1);
        g.fillRect(centerX + x, centerY - y, 1, 1);
        g.fillRect(centerX - x, centerY - y, 1, 1);
        g.fillRect(centerX + y, centerY + x, 1, 1);
        g.fillRect(centerX - y, centerY + x, 1, 1);
        g.fillRect(centerX + y, centerY - x, 1, 1);
        g.fillRect(centerX - y, centerY - x, 1, 1);
    }

    public static void main(String[] args) {
        new RMidCircle();
    }
}