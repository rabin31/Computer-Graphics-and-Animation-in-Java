import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

class RBoundaryFill extends Frame {
    private BufferedImage rabinImage;
    private int rabinWidth, rabinHeight;
    private Color rabinFillColor = Color.RED;
    private Color rabinBoundaryColor = Color.BLUE;
    private int rabinStartX_std, rabinStartY_std;
    private int rabinStartX_awt, rabinStartY_awt;
    private boolean rabinFillStarted = false;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public RBoundaryFill() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the starting x-coordinate for boundary fill (relative to window center):");
        rabinStartX_std = sc.nextInt();
        System.out.println("Enter the starting y-coordinate for boundary fill (relative to window center):");
        rabinStartY_std = sc.nextInt();
        sc.close();

        this.setTitle("Boundary Fill Algorithm");
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        rabinWidth = getWidth();
        rabinHeight = getHeight();
        rabinImage = new BufferedImage(rabinWidth, rabinHeight, BufferedImage.TYPE_INT_RGB);
        Graphics grabinImage = rabinImage.getGraphics();
        grabinImage.setColor(Color.WHITE);
        grabinImage.fillRect(0, 0, rabinWidth, rabinHeight);

        grabinImage.setColor(rabinBoundaryColor);
        grabinImage.drawRect(200, 200, 400, 300);

        Point startPoint_awt = toAWTCoords(rabinStartX_std, rabinStartY_std);
        rabinStartX_awt = startPoint_awt.x;
        rabinStartY_awt = startPoint_awt.y;

        if (rabinStartX_awt >= 0 && rabinStartX_awt < rabinWidth && rabinStartY_awt >= 0 && rabinStartY_awt < rabinHeight) {
            rabinFillStarted = true;
        } else {
            System.out.println("Error: Start point (" + rabinStartX_std + ", " + rabinStartY_std + ") is outside window bounds and cannot start fill.");
        }
         repaint(); // Request repaint after initializing or if start point is invalid
    }

    private Point toAWTCoords(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return new Point(centerX + x, centerY - y);
    }

    @Override
    public void paint(Graphics rabing) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        rabing.setColor(Color.BLACK);
        rabing.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        rabing.drawLine(0, centerY, getWidth(), centerY);
        rabing.drawLine(centerX, 0, centerX, getHeight());

        rabing.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        rabing.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        rabing.drawLine(centerX - 3, 10, centerX, 0);
        rabing.drawLine(centerX + 3, 10, centerX, 0);

        if (rabinImage != null) {
            rabing.drawImage(rabinImage, 0, 0, this);
            if (rabinFillStarted) {
                rabinBoundaryFill(rabinStartX_awt, rabinStartY_awt, rabinFillColor, rabinBoundaryColor);
                rabinFillStarted = false;
                repaint(); 
            }
        }

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        rabing.setColor(Color.WHITE);
        rabing.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        rabing.setColor(Color.BLACK);
        rabing.drawString("Background Color", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        rabing.setColor(rabinBoundaryColor);
        rabing.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        rabing.setColor(Color.BLACK);
        rabing.drawString("Boundary Color", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);


        legendY += LEGEND_SPACING;
        rabing.setColor(rabinFillColor);
        rabing.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        rabing.setColor(Color.BLACK);
        rabing.drawString("Fill Color", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        rabing.setColor(Color.BLACK);
        rabing.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    private void rabinBoundaryFill(int rabinbfx_awt, int rabinbfy_awt, Color rabinbfillColor, Color rabinbboundaryColor) {
        if (rabinbfx_awt < 0 || rabinbfx_awt >= rabinWidth || rabinbfy_awt < 0 || rabinbfy_awt >= rabinHeight) {
            return;
        }

        int rabinCurrentColorRGB = rabinImage.getRGB(rabinbfx_awt, rabinbfy_awt);
        int rabinbboundaryRGB = rabinbboundaryColor.getRGB();
        int rabinbfillRGB = rabinbfillColor.getRGB();

        if (rabinCurrentColorRGB == rabinbboundaryRGB || rabinCurrentColorRGB == rabinbfillRGB) {
            return;
        }

        rabinImage.setRGB(rabinbfx_awt, rabinbfy_awt, rabinbfillRGB);

        rabinBoundaryFill(rabinbfx_awt + 1, rabinbfy_awt, rabinbfillColor, rabinbboundaryColor);
        rabinBoundaryFill(rabinbfx_awt - 1, rabinbfy_awt, rabinbfillColor, rabinbboundaryColor);
        rabinBoundaryFill(rabinbfx_awt, rabinbfy_awt + 1, rabinbfillColor, rabinbboundaryColor);
        rabinBoundaryFill(rabinbfx_awt, rabinbfy_awt - 1, rabinbfillColor, rabinbboundaryColor);
    }

    public static void main(String[] args) {
        new RBoundaryFill();
    }
}