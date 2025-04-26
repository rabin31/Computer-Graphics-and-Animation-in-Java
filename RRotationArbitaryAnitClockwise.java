
import java.util.Scanner;
import java.awt.*;

class RRotationArbitaryAntiClockwise extends Frame {
    int x1, y1, x2, y2, xp, yp;
    int px1, px2, py1, py2;
    double angle;

    public RRotationArbitaryAntiClockwise() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter pivot point x");
        xp = sc.nextInt();
        System.out.println("Enter pivot point y");
        yp = sc.nextInt();
        System.out.println("Enter rotation angle (degrees)");
        angle = Math.toRadians(sc.nextDouble());
        
        this.setTitle("Line Rotation (Anti-Clockwise)");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
        
        rotateLine();
    }

    private void rotateLine() {
        int x1t = x1 - xp;
        int y1t = y1 - yp;
        int x2t = x2 - xp;
        int y2t = y2 - yp;
        
        // Anti-clockwise rotation matrix
        px1 = (int)(x1t * Math.cos(angle) - y1t * Math.sin(angle));
        py1 = (int)(x1t * Math.sin(angle) + y1t * Math.cos(angle));
        px2 = (int)(x2t * Math.cos(angle) - y2t * Math.sin(angle));
        py2 = (int)(x2t * Math.sin(angle) + y2t * Math.cos(angle));
        
        px1 += xp;
        py1 += yp;
        px2 += xp;
        py2 += yp;
    }

    public void paint(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Before rotation", x1, y1);
        g.setColor(Color.BLUE);
        g.drawLine(px1, py1, px2, py2);
        g.drawString("After rotation", px1, py1);
    }

    public static void main(String[] args) {
        new RRotationArbitaryAntiClockwise();
    }
}
