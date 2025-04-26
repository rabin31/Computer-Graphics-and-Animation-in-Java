import java.util.Scanner;
import java.awt.*;

class RScalingOrigin extends Frame {
    int x1, y1, x2, y2;
    float sx, sy;
    int px1, px2, py1, py2;

    public RScalingOrigin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter scaling factor sx");
        sx = sc.nextFloat();
        System.out.println("Enter scaling factor sy");
        sy = sc.nextFloat();

        this.setTitle("Line Scaling");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);

        // Scaling about origin (0,0)
        px1 = (int)(x1 * sx);
        py1 = (int)(y1 * sy);
        px2 = (int)(x2 * sx);
        py2 = (int)(y2 * sy);
    }

    public void paint(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
        g.drawString("before scaling", x1, y1);
        
        g.setColor(Color.RED);
        g.drawLine(px1, py1, px2, py2);
        g.drawString("after scaling", px1, py1);
    }

    public static void main(String[] args) {
        new RScalingOrigin();
    }
}
