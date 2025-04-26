import java.util.Scanner;
import java.awt.*;

class RDDALine extends Frame {
    int x1, y1, x2, y2;

    public RDDALine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1");
        x1 = sc.nextInt();
        System.out.println("Enter y1");
        y1 = sc.nextInt();
        System.out.println("Enter x2");
        x2 = sc.nextInt();
        System.out.println("Enter y2");
        y2 = sc.nextInt();

        this.setTitle("DDA Line Drawing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps;
        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }

        double xIncrement = (double) dx / steps;
        double yIncrement = (double) dy / steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {
            g.setColor(Color.BLACK);
            g.fillRect((int) Math.round(x), (int) Math.round(y), 1, 1);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public static void main(String[] args) {
        new RDDALine();
    }
}