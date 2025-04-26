import java.util.Scanner;
import java.awt.*;

class RShearingX extends Frame {
    int x1, y1, x2, y2;
    double shx;
    int sx1, sy1, sx2, sy2;

    public RShearingX() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();
        System.out.println("Enter shearing factor in x-direction (shx)");
        shx = sc.nextDouble();

        sx1 = (int) Math.round(x1 + shx * y1);
        sy1 = y1;
        sx2 = (int) Math.round(x2 + shx * y2);
        sy2 = y2;

        this.setTitle("Shearing in X-Direction");
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
        g.drawLine(0, 400, 800, 400);
        g.drawString("Reference Y=400", 400, 415);
    }

    public static void main(String[] args) {
        new RShearingX();
    }
}