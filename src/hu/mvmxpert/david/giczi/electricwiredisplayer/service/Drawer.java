package hu.mvmxpert.david.giczi.electricwiredisplayer.service;

import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Drawer {

	public static final double MILLIMETER = 1000 / 224.0;
	public static final double MONITOR_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final double MONITOR_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final double PAGE_WIDTH =  211 * MILLIMETER;
	public static final double PAGE_HEIGHT =  MONITOR_HEIGHT - 40;
	public static final double PAGE_X = (MONITOR_WIDTH - PAGE_WIDTH) / 2;
	public static final double PAGE_Y = 10;
	public static final double MARGIN = 156 * MILLIMETER;
	private static double START_X;
	private static double START_Y = PAGE_Y + 650;
	private double lengthOfHorizontalAxis;
	private int scale;
	
	public Drawer(double lengthOfHorizontalAxis, int scale) {
		this.lengthOfHorizontalAxis = lengthOfHorizontalAxis;
		this.scale = scale;
		}

	public void drawPage(Pane root) {
		Rectangle page = new Rectangle(PAGE_X, PAGE_Y, PAGE_WIDTH, PAGE_HEIGHT);
		Line leftMargin = new Line(PAGE_X + (PAGE_WIDTH - MARGIN) / 2, PAGE_Y , PAGE_X +(PAGE_WIDTH - MARGIN) / 2, PAGE_HEIGHT + 10);
		Line rightMargin = new Line(PAGE_X + (PAGE_WIDTH - MARGIN) / 2 + MARGIN, PAGE_Y, 
				PAGE_X +(PAGE_WIDTH - MARGIN) / 2 + MARGIN, PAGE_HEIGHT + 10);
		leftMargin.setStroke(Color.LIGHTGRAY);
		leftMargin.getStrokeDashArray().addAll(4d);
		rightMargin.setStroke(Color.LIGHTGRAY);
		rightMargin.getStrokeDashArray().addAll(4d);
		page.setFill(Color.WHITE);
		root.getChildren().addAll(page, leftMargin, rightMargin);
	}
	
	public void drawVerticalAxis(Pane root, double startX, double startY) {
		Line leftBorder = new Line(PAGE_X + startX, PAGE_Y + startY, PAGE_X + startX, PAGE_Y + startY + 100 * MILLIMETER);
		Line rightBorder = new Line(PAGE_X + startX + 2 * MILLIMETER, 
				PAGE_Y + startY, PAGE_X + startX + 2 * MILLIMETER, PAGE_Y + startY + 100 * MILLIMETER);
		Line topBorder = new Line(PAGE_X + startX, PAGE_Y + startY, PAGE_X + startX + 2 * MILLIMETER, PAGE_Y + startY);
		root.getChildren().addAll(leftBorder, rightBorder, topBorder);
		for(int i = 0; i < 10; i++) {
		Rectangle axisComponent = new Rectangle(PAGE_X + startX, PAGE_Y + startY, 2 * MILLIMETER, 10 * MILLIMETER);
		if( i % 2 == 0) {
			axisComponent.setFill(Color.WHITE);
		}
		root.getChildren().add(axisComponent);
		startY += 10 * MILLIMETER;
		}
	}
	
	public void drawHorizontalAxis(Pane root, double startX, double startY) {
		Line topBorder = new Line(PAGE_X + startX, PAGE_Y + startY, 
				PAGE_X + startX + getScaledDownLengthValue(scale, lengthOfHorizontalAxis) * MILLIMETER, PAGE_Y + startY);
		Line rightBorder = new Line(PAGE_X + startX + getScaledDownLengthValue(scale, lengthOfHorizontalAxis) * MILLIMETER,
				PAGE_Y + startY +  MILLIMETER, 
				PAGE_X + startX + getScaledDownLengthValue(scale, lengthOfHorizontalAxis) * MILLIMETER, PAGE_Y + startY);
		Line downBorder = new Line(PAGE_X + startX + getScaledDownLengthValue(scale, lengthOfHorizontalAxis) * MILLIMETER,
				PAGE_Y + startY + MILLIMETER, 
				PAGE_X + startX, PAGE_Y + startY +  MILLIMETER);
		Line leftBorder = new Line(PAGE_X + startX, PAGE_Y + startY, PAGE_X + startX, PAGE_Y + startY + MILLIMETER);
		root.getChildren().addAll(topBorder, rightBorder, downBorder, leftBorder);
	}
	
	public void writeElevationValueForVerticalAxis(Pane root, double startX, int startValue, int scale) {
		double startY = START_Y;
		for(int i = 0; i <= 10; i++) {
		Text elevationValue = new Text();
		elevationValue.setX(PAGE_X + startX);
		elevationValue.setText(String.valueOf(startValue));
		elevationValue.setY(startY);
		elevationValue.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 20));
		startY -= 10 * MILLIMETER;
		startValue += scale;
		root.getChildren().add(elevationValue);
		}
	}
	
	public void writeDistanceValueForHorizontalAxis(Pane root, double startX) {
		Text zeroValue = new Text();
		zeroValue.setText("0");
		zeroValue.setX(PAGE_X + startX);
		zeroValue.setY(PAGE_Y + 700);
		zeroValue.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 20));
		root.getChildren().add(zeroValue);
		Text lengthValue = new Text();
		lengthValue.setText(String.valueOf(lengthOfHorizontalAxis) + "m");
		lengthValue.setX(PAGE_X + startX + getScaledDownLengthValue(scale, lengthOfHorizontalAxis) * MILLIMETER);
		lengthValue.setY(PAGE_Y + 700);
		lengthValue.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 20));
		root.getChildren().add(lengthValue);
	}
	
	private double getScaledDownLengthValue(int scale, double length) {
		return scale == 1000 ? length : 1000.0  * length / scale;
	}
	
}
