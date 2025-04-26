import java.util.Scanner;
import java.awt.*;

class RBLALine extends Frame {
    int x1, y1, x2, y2;

    public RBLALine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();

        this.setTitle("Bresenham's Line Drawing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        int x = x1;
        int y = y1;

        while (true) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 1, 1);

            if (x == x2 && y == y2) {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    public static void main(String[] args) {
        new RBLALine();
    }
}