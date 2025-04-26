import java.util.Scanner;
import java.awt.*;

class RReflectionParallelY extends Frame {
    int x1, y1, x2, y2, a;
    int rx1, ry1, rx2, ry2;

    public RReflectionParallelY() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter x-intercept (a) of axis x = a");
        a = sc.nextInt();

        rx1 = 2 * a - x1;
        ry1 = y1;
        rx2 = 2 * a - x2;
        ry2 = y2;

        this.setTitle("Reflection Through Axis Parallel to Y-axis (x = a)");
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

        // Draw axis x = a
        g.setColor(Color.BLUE);
        g.drawLine(a, 0, a, 800);
        g.drawString("Axis x = " + a, a + 5, 20);
    }

    public static void main(String[] args) {
        new RReflectionParallelY();
    }
}
