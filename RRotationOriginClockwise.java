import java.util.Scanner;
import java.awt.*;

class RRotationOriginClockwise extends Frame {
    int x1, y1, x2, y2;
    int px1, px2, py1, py2;
    double angle;

    public RRotationOriginClockwise() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter rotation angle (degrees)");
        angle = Math.toRadians(sc.nextDouble()); // Convert to radians

        this.setTitle("Line Rotation about Origin");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);

        // Clockwise rotation matrix (negative angle)
        px1 = (int)(x1 * Math.cos(angle) + y1 * Math.sin(angle));
        py1 = (int)(y1 * Math.cos(angle) - x1 * Math.sin(angle));
        px2 = (int)(x2 * Math.cos(angle) + y2 * Math.sin(angle));
        py2 = (int)(y2 * Math.cos(angle) - x2 * Math.sin(angle));
    }

    public void paint(Graphics g) {
        // Draw original line
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original", x1, y1);
        
        // Draw rotated line
        g.setColor(Color.RED);
        g.drawLine(px1, py1, px2, py2);
        g.drawString("Rotated", px1, py1);
        
        // Draw origin marker
        g.setColor(Color.GREEN);
        g.fillOval(-3, -3, 6, 6); // Origin (0,0)
    }

    public static void main(String[] args) {
        new RRotationOriginClockwise();
    }
}
