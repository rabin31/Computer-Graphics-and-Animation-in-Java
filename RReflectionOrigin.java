import java.util.Scanner;
import java.awt.*;

class RReflectionOrigin extends Frame {
    int x1, y1, x2, y2;
    int rx1, ry1, rx2, ry2;

    public RReflectionOrigin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();

        // Corrected reflection calculation through the visible origin (400, 400)
        rx1 = 2 * 400 - x1;
        ry1 = 2 * 400 - y1;
        rx2 = 2 * 400 - x2;
        ry2 = 2 * 400 - y2;

        this.setTitle("Reflection Through Origin");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        // Drawing is already consistent with a (0,0) at the top-left of the frame.
        // Let's keep the drawing relative to the *input* coordinates
        // and the visual origin at (400, 400).

        // Original line (black)
        g.setColor(Color.BLACK); // Best to explicitly set the color for clarity
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original Line", x1, y1);

        // Reflected line (red)
        g.setColor(Color.RED);
        g.drawLine(rx1, ry1, rx2, ry2);
        g.drawString("Reflected Line", rx1, ry1);

        // Draw origin reference (blue cross)
        g.setColor(Color.BLUE);
        g.drawLine(400, 0, 400, 800); // Y-axis
        g.drawLine(0, 400, 800, 400); // X-axis
        g.drawString("Origin (400,400)", 405, 415); // Update label
    }

    public static void main(String[] args) {
        new RReflectionOrigin();
    }
}