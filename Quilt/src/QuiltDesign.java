import java.awt.Color;
import java.awt.Graphics;

public class QuiltDesign {
	
	public final static int SIZE = 2000;
	public final static int ITERATIONS = 3;
	public final static int ROWS = 4;
	
	public static int determineBuffer(int containerHeight) {
		return containerHeight/80;
	}
	
	public static Color redshiftColor(Color color) {
		float[] rgb = {0, 0, 0};
		color.getRGBColorComponents(rgb);
		return new Color((float) (rgb[0] + .07), rgb[1], rgb[2]);
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
	
	public static void drawRowOfGrids(Graphics g, Color color, int containerX, int containerY, int buffer, int containerHeight, int gridCount, int rows) {
		int gridSize = containerHeight - 2 * buffer;
		int innerBuffer = determineBuffer(gridSize);
		for(int i = 0; i < gridCount; i++) {
			drawBrickGrid(g, color, containerX + (buffer * (i + 1)) + (gridSize * i), containerY + buffer, innerBuffer, gridSize, rows);
		}
	}
	
	public static void drawGridOfGrids(Graphics g, Color color, int containerX, int containerY, int buffer, int size, int rows) {
		int rowHeight = size / rows;
		for(int i = 0; i < rows; i++) {
			drawRowOfGrids(g, color, containerX, containerY + i * (rowHeight - buffer), buffer, rowHeight, i + 1, rows);
		}
	}
	
	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
		panel.setBackground(Color.WHITE);
		Graphics g = panel.getGraphics();
		
		drawGridOfGrids(g, Color.BLUE, 0, 0, determineBuffer(SIZE), SIZE, ROWS);
		for(int i = 0; i < ITERATIONS; i++) {
			
		}
		
		/*
		for(int i = 0; i < ROWS; i++) {
			drawRowOfGrids(g, Color.BLUE, 0, i * (400 - determineBuffer(SIZE)), determineBuffer(SIZE), 400, i + 1, ROWS);
		}	
		*/
	}

}
