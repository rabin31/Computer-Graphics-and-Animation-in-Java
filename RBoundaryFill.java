import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;

class RBoundaryFill extends Frame {
    private BufferedImage image;
    private int width, height;
    private Color fillColor = Color.GREEN;
    private Color boundaryColor = Color.BLUE;
    private int startX, startY;
    private boolean fillStarted = false;

    public RBoundaryFill() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting x-coordinate for boundary fill:");
        startX = sc.nextInt();
        System.out.println("Enter the starting y-coordinate for boundary fill:");
        startY = sc.nextInt();

        this.setTitle("Boundary Fill Algorithm");
        this.setBounds(100, 100, 600, 400);
        this.setVisible(true);

        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(boundaryColor);
        g.drawRect(150, 100, 300, 200);

        fillStarted = true;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this);
            if (fillStarted) {
                boundaryFill(g, startX, startY, fillColor, boundaryColor);
                fillStarted = false;
                repaint();
            }
        }
    }

    private void boundaryFill(Graphics g, int x, int y, Color fillColor, Color boundaryColor) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        int currentColorRGB = image.getRGB(x, y);
        int boundaryRGB = boundaryColor.getRGB();
        int fillRGB = fillColor.getRGB();

        if (currentColorRGB == boundaryRGB || currentColorRGB == fillRGB) {
            return;
        }

        image.setRGB(x, y, fillRGB);

        boundaryFill(g, x + 1, y, fillColor, boundaryColor);
        boundaryFill(g, x - 1, y, fillColor, boundaryColor);
        boundaryFill(g, x, y + 1, fillColor, boundaryColor);
        boundaryFill(g, x, y - 1, fillColor, boundaryColor);
    }

    public static void main(String[] args) {
        new RBoundaryFill();
    }
}