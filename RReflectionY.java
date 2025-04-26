import java.util.Scanner;
import java.awt.*;

class RReflectionY extends Frame {
    int x1, y1, x2, y2;
    int rx1, ry1, rx2, ry2;

    public RReflectionY() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();

        // Corrected reflection calculation across x = 400
        rx1 = 2 * 400 - x1;
        ry1 = y1;
        rx2 = 2 * 400 - x2;
        ry2 = y2;

        this.setTitle("Reflection Through Y-axis");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original Line", x1, y1);

        g.setColor(Color.RED);
        g.drawLine(rx1, ry1, rx2, ry2);
        g.drawString("Reflected Line", rx1, ry1);

        g.setColor(Color.BLUE);
        g.drawLine(400, 0, 400, 800);
        g.drawString("Y-axis", 415, 400);
    }

    public static void main(String[] args) {
        new RReflectionY();
    }
}