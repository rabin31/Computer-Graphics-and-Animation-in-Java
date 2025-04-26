import java.util.Scanner;
import java.awt.*;

class RShearingY extends Frame {
    int x1, y1, x2, y2;
    double shy;
    int sx1, sy1, sx2, sy2;

    public RShearingY() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter shearing factor in y-direction (shy)");
        shy = sc.nextDouble();

        sx1 = x1;
        sy1 = (int) Math.round(y1 + shy * x1);
        sx2 = x2;
        sy2 = (int) Math.round(y2 + shy * x2);

        this.setTitle("Shearing in Y-Direction");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        g.drawString("Original Line", x1, y1);

        g.setColor(Color.RED);
        g.drawLine(sx1, sy1, sx2, sy2);
        g.drawString("Sheared Line", sx1, sy1);

        g.setColor(Color.BLUE);
        g.drawLine(400, 0, 400, 800);
        g.drawString("Reference X=400", 405, 400);
    }

    public static void main(String[] args) {
        new RShearingY();
    }
}