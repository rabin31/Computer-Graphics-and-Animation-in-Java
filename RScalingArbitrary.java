import java.util.Scanner;
import java.awt.*;

class RScalingArbitrary extends Frame {
    int x1, y1, x2, y2;
    float sx, sy;
    int tx, ty;
    int px1, px2, py1, py2;

    public RScalingArbitrary() {
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
        System.out.println("Enter arbitrary point tx");
        tx = sc.nextInt();
        System.out.println("Enter arbitrary point ty");
        ty = sc.nextInt();

        this.setTitle("Line Scaling at Arbitrary Point");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);

        // Scaling about arbitrary point (tx, ty)
        px1 = (int)(x1 * sx + tx * (1 - sx));
        py1 = (int)(y1 * sy + ty * (1 - sy));
        px2 = (int)(x2 * sx + tx * (1 - sx));
        py2 = (int)(y2 * sy + ty * (1 - sy));
    }

    public void paint(Graphics g) {
        // Draw original line
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original", x1, y1);
        
        // Draw scaled line
        g.setColor(Color.RED);
        g.drawLine(px1, py1, px2, py2);
        g.drawString("Scaled", px1, py1);
        
        // Draw reference point
        g.setColor(Color.GREEN);
        g.fillOval(tx-3, ty-3, 6, 6);
    }

    public static void main(String[] args) {
        new RScalingArbitrary();
    }
}

