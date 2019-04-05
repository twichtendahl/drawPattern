import java.awt.Color;
import java.awt.Graphics;

public class QuiltDesign {
	
	public final static int SIZE = 2000;
	public final static int LAYERS = 4;
	
	public int determineBuffer(int containerHeight) {
		return containerHeight/120;
	}
	
	public Color shiftColor(Color color) {
		float[] rgb = {0, 0, 0, 0};
		color.getRGBComponents(rgb);
		return new Color(rgb[0], rgb[1], rgb[2], (float) (rgb[3] + .075));
	}
	
	public static void drawBrickRow(Graphics g, Color color, int containerX, int containerY, int buffer, int containerWidth, int containerHeight, int brickCount) {
		g.setColor(color);
		int cellWidth = (containerWidth - buffer) / brickCount; //fencepost: there's one more buffer than brick.
		int brickWidth = cellWidth - buffer;
		int brickHeight = containerHeight - 2 * buffer;
		for(int i = 0; i < brickCount; i++) {
			g.fillRect(containerX + buffer + i * cellWidth, containerY + buffer, brickWidth, brickHeight);
		}
	}
	
	public static void drawBrickGrid(Graphics g, Color color, int containerX, int containerY, int buffer, int size, int rows) {
		int containerHeight = size / rows;
		int containerWidth;
		for(int i = 0; i < rows; i++) {
			containerWidth = (i + 1) * containerHeight - (i * buffer);
			drawBrickRow(g, color, containerX, containerY + i * (containerHeight - buffer), buffer, containerWidth, containerHeight, i + 1);
		}
	}
	
	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
		panel.setBackground(Color.WHITE);
		Graphics g = panel.getGraphics();
		
		g.setColor(Color.CYAN);
		g.fillRect(200, 100, 400, 400);
		drawBrickGrid(g, Color.RED, 200, 100, 8, 400, 6);
		/*
		*/
	}

}
