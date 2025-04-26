import java.util.Scanner;
import java.awt.*;

class RGeneralCircle extends Frame {
    int centerX, centerY, radius;

    public RGeneralCircle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center:");
        centerX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center:");
        centerY = sc.nextInt();
        System.out.println("Enter the radius of the circle:");
        radius = sc.nextInt();

        this.setTitle("General Circle Drawing");
        this.setLayout(null);
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);

        for (double angle = 0; angle <= 2 * Math.PI; angle += 0.001) {
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            g.fillRect(x, y, 1, 1);
        }
    }

    public static void main(String[] args) {
        new RGeneralCircle();
    }
}
