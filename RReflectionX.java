import java.util.Scanner;
import java.awt.*;

class RReflection extends Frame {
    int x1, y1, x2, y2;
    int rx1, ry1, rx2, ry2;

    public RReflection() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();

        // Corrected reflection calculation across y = 400
        rx1 = x1;
        ry1 = 2 * 400 - y1; // 800 - y1 is incorrect for reflection across y=400
        rx2 = x2;
        ry2 = 2 * 400 - y2; // 800 - y2 is incorrect for reflection across y=400

        this.setTitle("Reflection Through X-axis");
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
        g.drawLine(0, 400, 800, 400);
        g.drawString("X-axis", 400, 415);
    }

    public static void main(String[] args) {
        new RReflection();
    }
}