import java.util.Scanner;
import java.awt.*;

class RReflectionArbitaryLine extends Frame {
    int x1, y1, x2, y2;
    double m, c;
    int rx1, ry1, rx2, ry2;

    public RReflectionArbitaryLine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter slope m of the line y = mx + c");
        m = sc.nextDouble();
        System.out.println("Enter intercept c of the line y = mx + c");
        c = sc.nextDouble();

        double x_int1, y_int1;
        if (m == 0) {
            rx1 = x1;
            ry1 = (int) Math.round(2 * c - y1);
        } else {
            x_int1 = (x1 + m * y1 - m * c) / (1 + m * m);
            y_int1 = m * x_int1 + c;
            rx1 = (int) Math.round(2 * x_int1 - x1);
            ry1 = (int) Math.round(2 * y_int1 - y1);
        }

        double x_int2, y_int2;
        if (m == 0) {
            rx2 = x2;
            ry2 = (int) Math.round(2 * c - y2);
        } else {
            x_int2 = (x2 + m * y2 - m * c) / (1 + m * m);
            y_int2 = m * x_int2 + c;
            rx2 = (int) Math.round(2 * x_int2 - x2);
            ry2 = (int) Math.round(2 * y_int2 - y2);
        }

        this.setTitle("Reflection Through Arbitrary Line y = mx + c");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original Line", x1, y1);

        g.setColor(Color.RED);
        g.drawLine(rx1, ry1, rx2, ry2);
        g.drawString("Reflected Line", rx1, ry1);

        g.setColor(Color.BLUE);
        int startX = 0;
        int endX = 800;
        int startY = (int) Math.round(m * startX + c);
        int endY = (int) Math.round(m * endX + c);

        if (startY > 800) startY = 800;
        if (startY < 0) startY = 0;
        if (endY > 800) endY = 800;
        if (endY < 0) endY = 0;

        g.drawLine(startX, startY, endX, endY);
        g.drawString("Line y = " + m + "x + " + c, 10, (int) Math.round(m * 10 + c) + 15);
    }

    public static void main(String[] args) {
        new RReflectionArbitaryLine();
    }
}