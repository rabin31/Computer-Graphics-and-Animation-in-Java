
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;

class RFloodFill extends Frame {
    private BufferedImage image;
    private int width, height;
    private Color fillColor = Color.RED;
    private Color originalColor;
    private int startX, startY;
    private boolean fillStarted = false;

    public RFloodFill() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting x-coordinate for flood fill:");
        startX = sc.nextInt();
        System.out.println("Enter the starting y-coordinate for flood fill:");
        startY = sc.nextInt();

        this.setTitle("Flood Fill Algorithm");
        this.setBounds(100, 100, 600, 400);
        this.setVisible(true);

        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLUE);
        g.fillRect(150, 100, 300, 200);
        originalColor = new Color(image.getRGB(startX, startY));

        fillStarted = true;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this);
            if (fillStarted) {
                Color targetColor = new Color(image.getRGB(startX, startY));
                if (targetColor.getRGB() == originalColor.getRGB()) {
                    floodFill(startX, startY, fillColor, originalColor);
                }
                fillStarted = false;
                repaint();
            }
        }
    }

    private void floodFill(int x, int y, Color fillColor, Color originalColor) {
        int originalRGB = originalColor.getRGB();
        int fillRGB = fillColor.getRGB();

        if (originalRGB == fillRGB) {
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        image.setRGB(x, y, fillRGB);

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int px = p.x;
            int py = p.y;

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    int pixelColor = image.getRGB(nx, ny);
                    if (pixelColor == originalRGB) {
                        image.setRGB(nx, ny, fillRGB);
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new RFloodFill();
    }
}