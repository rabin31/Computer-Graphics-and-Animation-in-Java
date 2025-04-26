import java.util.Scanner;
import java.awt.*;

class RReflectionParallelX extends Frame {
    int x1, y1, x2, y2, b;
    int rx1, ry1, rx2, ry2;

    public RReflectionParallelX() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter y-intercept (b) of axis y = b");
        b = sc.nextInt();

        rx1 = x1;
        ry1 = 2 * b - y1;
        rx2 = x2;
        ry2 = 2 * b - y2;

        this.setTitle("Reflection Through Axis Parallel to X-axis (y = b)");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        // Original line
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original Line", x1, y1);

        // Reflected line
        g.setColor(Color.RED);
        g.drawLine(rx1, ry1, rx2, ry2);
        g.drawString("Reflected Line", rx1, ry1);

        // Draw axis y = b
        g.setColor(Color.BLUE);
        g.drawLine(0, b, 800, b);
        g.drawString("Axis y = " + b, 10, b - 10);
    }

    public static void main(String[] args) {
        new RReflectionParallelX();
    }
}
