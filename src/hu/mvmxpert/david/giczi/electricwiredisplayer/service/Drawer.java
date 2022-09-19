package hu.mvmxpert.david.giczi.electricwiredisplayer.service;


import hu.mvmxpert.david.giczi.electricwiredisplayer.controller.HomeController;
import hu.mvmxpert.david.giczi.electricwiredisplayer.view.ModifyTextWindow;
import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Drawer {
	
	private BorderPane root;
	public static double MILLIMETER = 1000 / 224.0;
	private final double HOR_SHIFT = 12;
	private final double A4_WIDTH =  211 * MILLIMETER;
	private final double PAGE_Y = 25;
	private final double MARGIN = 156 * MILLIMETER;
	private final double VER_SHIFT = 5;
	private final double START_X = 45 * MILLIMETER;
	private final double START_Y = 550.0;
	private double lengthOfHorizontalAxis;
	private int horizontalScale;
	private int verticalScale;
	private int elevationStartValue;
	private ModifyTextWindow modifyTextWindow;
	

	public void setLengthOfHorizontalAxis(double lengthOfHorizontalAxis) {
		this.lengthOfHorizontalAxis = lengthOfHorizontalAxis;
	}

	public void setHorizontalScale(int horizontalScale) {
		this.horizontalScale = horizontalScale;
	}

	public void setVerticalScale(int verticalScale) {
		this.verticalScale = verticalScale;
	}
	
	public int getVerticalScale() {
		return verticalScale;
	}

	public void setElevationStartValue(int elevationStartValue) {
		this.elevationStartValue = elevationStartValue;
	}
	
	public int getElevationStartValue() {
		return elevationStartValue;
	}

	public double getLengthOfHorizontalAxis() {
		return lengthOfHorizontalAxis;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public void drawPage() {
		Rectangle page = new Rectangle();
		page.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2));
		page.setY(PAGE_Y);
		page.setWidth(A4_WIDTH);
		page.heightProperty().bind(root.widthProperty().subtract(50));
		Line leftMargin = new Line();
		leftMargin.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add((A4_WIDTH - MARGIN) / 2));
		leftMargin.setStartY(PAGE_Y);
		leftMargin.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add((A4_WIDTH - MARGIN) / 2));
		leftMargin.endYProperty().bind(root.heightProperty().add(20));
		Line rightMargin = new Line();
		rightMargin.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add((A4_WIDTH - MARGIN) / 2).add(MARGIN));
		rightMargin.setStartY(PAGE_Y);
		rightMargin.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add((A4_WIDTH - MARGIN) / 2).add(MARGIN));
		rightMargin.endYProperty().bind(root.heightProperty().add(20));
		leftMargin.setStroke(Color.LIGHTGRAY);
		leftMargin.getStrokeDashArray().addAll(4d);
		rightMargin.setStroke(Color.LIGHTGRAY);
		rightMargin.getStrokeDashArray().addAll(4d);
		page.setFill(Color.WHITE);
		root.getChildren().addAll(page, leftMargin, rightMargin);
	}
	
	public void drawVerticalAxis() {
		double startY = START_Y - 10 * MILLIMETER;
		Line leftBorder = new Line();
		leftBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X));
		leftBorder.setStartY(PAGE_Y + START_Y);
		leftBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X));
		leftBorder.setEndY(PAGE_Y + START_Y - 100  * MILLIMETER);
		Line rightBorder = new Line();
		rightBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(2 * MILLIMETER));
		rightBorder.setStartY(PAGE_Y + START_Y);
		rightBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(2 * MILLIMETER));
		rightBorder.setEndY(PAGE_Y + START_Y - 100  * MILLIMETER);
		Line topBorder = new Line();
		topBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X));
		topBorder.setStartY(PAGE_Y + START_Y - 100 * MILLIMETER);
		topBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(2 * MILLIMETER));
		topBorder.setEndY(PAGE_Y + START_Y - 100 * MILLIMETER);
		root.getChildren().addAll(leftBorder, rightBorder, topBorder);
		for(int i = 1; i < 11; i++) {
		Rectangle axisComponent = new Rectangle();
		axisComponent.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X));
		axisComponent.setY(PAGE_Y + startY);
		axisComponent.setWidth(2 * MILLIMETER);
		axisComponent.setHeight(10 * MILLIMETER);
		if( i % 2 == 0) {
			axisComponent.setFill(Color.WHITE);
		}
		startY -= 10 * MILLIMETER;
		root.getChildren().add(axisComponent);
		}
	}
	
	public void drawHorizontalAxis() {
		Line topBorder = new Line();
		topBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(HOR_SHIFT * MILLIMETER));
		topBorder.setStartY(PAGE_Y + START_Y + VER_SHIFT * MILLIMETER);
		topBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(lengthOfHorizontalAxis) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		topBorder.setEndY(PAGE_Y + START_Y + VER_SHIFT * MILLIMETER);
		Line rightBorder = new Line();
		rightBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(lengthOfHorizontalAxis) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		rightBorder.setStartY(PAGE_Y + START_Y + (VER_SHIFT  + 1 ) * MILLIMETER);
		rightBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(lengthOfHorizontalAxis) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		rightBorder.setEndY(PAGE_Y + START_Y + VER_SHIFT * MILLIMETER);
		Line downBorder = new Line();
		downBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(HOR_SHIFT * MILLIMETER));
		downBorder.setStartY(PAGE_Y + START_Y + (VER_SHIFT + 1) * MILLIMETER);
		downBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(lengthOfHorizontalAxis) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		downBorder.setEndY(PAGE_Y + START_Y + (VER_SHIFT + 1) * MILLIMETER);
		Line leftBorder = new Line();
		leftBorder.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(HOR_SHIFT * MILLIMETER));
		leftBorder.setStartY(PAGE_Y + START_Y + VER_SHIFT * MILLIMETER);
		leftBorder.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(HOR_SHIFT * MILLIMETER));
		leftBorder.setEndY(PAGE_Y + START_Y + (VER_SHIFT + 1) * MILLIMETER);
		root.getChildren().addAll(topBorder, rightBorder, downBorder, leftBorder);
	}
	
	public void writeElevationValueForVerticalAxis() {
		double startY = START_Y;
		int startValue = elevationStartValue;
		for(int i = 0; i <= 10; i++) {
		setText(String.valueOf(startValue) + "m", - 70, PAGE_Y + startY, 18, 0);
		startY -= 10 * MILLIMETER;
		startValue += verticalScale;
		}
		setText("Oszlopszám:", - 17 * MILLIMETER, PAGE_Y + START_Y + 30 * MILLIMETER, 18, 0);
	}
	
	public void writeDistanceValueForHorizontalAxis() {
		setText("0", (HOR_SHIFT - 1) * MILLIMETER, PAGE_Y + START_Y + 50, 18, 0);
		setText(String.valueOf(lengthOfHorizontalAxis) + "m", 
				getHorizontalScaledDownLengthValue(lengthOfHorizontalAxis) * MILLIMETER + (HOR_SHIFT - 8) * MILLIMETER, 
				PAGE_Y + START_Y + 50, 18, 0);
	}
	
	public void drawPillar(String id, double groundElevation, double topElevation, double distance, boolean isHooded) {
		Line pillar = new Line();
		pillar.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		pillar.setStartY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(groundElevation - elevationStartValue) * MILLIMETER);
		pillar.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		pillar.setEndY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
		pillar.setStroke(Color.BLUE);
		pillar.setStrokeWidth(3);
		pillar.setCursor(Cursor.HAND);
		pillar.setOnMouseClicked( h -> {
			Line line = (Line) h.getSource();
			deleteLine(line);
			});
		root.getChildren().add(pillar);
		writePillarId(id, 
				getHorizontalScaledDownLengthValue(distance) * MILLIMETER + (HOR_SHIFT - 1) * MILLIMETER);
		if( isHooded ) {
			Line hood = new Line();
			hood.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
					.add(START_X)
					.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
					.add((HOR_SHIFT - 1) * MILLIMETER));
			hood.setStartY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
			hood.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
					.add(START_X)
					.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
					.add((HOR_SHIFT + 1) * MILLIMETER));
			hood.setEndY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
			hood.setCursor(Cursor.HAND);
			hood.setOnMouseClicked( h -> {
				Line line = (Line) h.getSource();
				deleteLine(line);
				});
			hood.setStroke(Color.BLUE);
			hood.setStrokeWidth(3);
			root.getChildren().add(hood);
		}
		setText("jobb ak.: Bf. " + groundElevation + "m", pillar.getStartX(), pillar.getStartY(), 18, -90);
		setText("jobb ak.: Bf. " + topElevation + "m",  pillar.getEndX(), pillar.getEndY(), 18, -90);
		setText(distance == 0 || distance == lengthOfHorizontalAxis ? "" :
				distance + "m", 
				getHorizontalScaledDownLengthValue(distance) * MILLIMETER + (HOR_SHIFT - 5) * MILLIMETER, PAGE_Y + START_Y + 50, 18, 0);
	}
	
	private void writePillarId(String id, double x) { 
		setText(id + ".", x, PAGE_Y + START_Y + 30 * MILLIMETER, 18, 0);
	}
	
	public void drawElectricWire(String text, double groundElevation, double topElevation, double distance, boolean isHooded) {
		Line wire = new Line();
		wire.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		wire.setStartY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(groundElevation - elevationStartValue) * MILLIMETER);
		wire.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
				.add(START_X)
				.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
				.add(HOR_SHIFT * MILLIMETER));
		wire.setEndY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
		wire.setStroke(Color.RED);
		wire.setStrokeWidth(3);
		wire.setCursor(Cursor.HAND);
		wire.setOnMouseClicked( h -> {
			Line line = (Line) h.getSource();
			deleteLine(line);
			});
		root.getChildren().add(wire);
		if( isHooded ) {
			Line hood = new Line();
			hood.startXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
					.add(START_X)
					.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
					.add((HOR_SHIFT - 1) * MILLIMETER));
			hood.setStartY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
			hood.endXProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2)
					.add(START_X)
					.add(getHorizontalScaledDownLengthValue(distance) * MILLIMETER)
					.add((HOR_SHIFT + 1) * MILLIMETER));
			hood.setEndY(PAGE_Y + START_Y - getVerticalScaledDownHeightValue(topElevation - elevationStartValue) * MILLIMETER);
			hood.setCursor(Cursor.HAND);
			hood.setStroke(Color.RED);
			hood.setStrokeWidth(3);
			hood.setCursor(Cursor.HAND);
			hood.setOnMouseClicked( h -> {
				Line line = (Line) h.getSource();
				deleteLine(line);
				});
			root.getChildren().add(hood);
		}
		setText("jobb af.: Bf. " + groundElevation + "m", wire.getStartX(), wire.getStartY(), 18, -90);
		setText("jobb af.: Bf. " + topElevation + "m", wire.getEndX(), wire.getEndY(), 18, -90);
		setText(distance == 0 || distance == lengthOfHorizontalAxis ? "" :
				distance + "m", getHorizontalScaledDownLengthValue(distance) * MILLIMETER + (HOR_SHIFT - 5) * MILLIMETER, 
				PAGE_Y + START_Y + 50, 18, 0);
		setText(text, getHorizontalScaledDownLengthValue(distance) * MILLIMETER + (HOR_SHIFT - 7) * MILLIMETER, 
				PAGE_Y + START_Y + 65, 18, 0);
	}
	
	public void writeText(String text, double startX, double startY) {
		Text txt = new Text(text);
		txt.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, 18));
		txt.xProperty().bind(root.widthProperty().subtract(root.widthProperty()).add(startX * MILLIMETER));
		txt.setY(startY * MILLIMETER);
		txt.setCursor(Cursor.HAND); 
		txt.setOnMouseClicked( t -> {
		Text inputText = (Text) t.getSource();
		getModifyTextWindow(inputText); });
		root.getChildren().add(txt);
	}
	
	private void setText(String text, double startX, double startY, int size, double rotate) {
		Text txt = new Text(text);
		txt.setFont(Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, size));
		
		if( rotate == 0 ) {
		txt.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(START_X).add(startX));
		txt.setY(startY);
		}
		else if( rotate == -90 ) {
		txt.getTransforms().add(new Rotate(rotate, startX, startY));
		txt.setX(startX);
		double yDistance = PAGE_Y - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		txt.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).add(startY));
		}
		txt.setCursor(Cursor.HAND); 
		txt.setOnMouseClicked( t -> {
		Text inputText = (Text) t.getSource();
		getModifyTextWindow(inputText); });
		root.getChildren().add(txt);
	}
	
	public boolean deleteText(Text text) {
		if( HomeController.getConfirmationAlert("Felirat törlése", "Biztos, hogy törlöd a kiválaszott feliratot?") ) {
			root.getChildren().remove(text);
			return true;
		}
		return false;
		}
	
	public void rotateText(Text text) {
		double xDistance = text.getX() - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		double yDistance = text.getY() - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		text.getTransforms().add(new Rotate(-90, text.getX(), text.getY()));
		text.xProperty().unbind();
		text.yProperty().unbind();
		int rotateStatus = text.getTransforms().size() % 4;
		
		switch ( rotateStatus ) {
		case 1:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance));
			break;
		case 2:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance));
			break;
		case 3:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance));
			break;
		default:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance));
		}
	}
	
	public void modifyText(Text text, String txt) {
		text.setText(txt);
	}
	
	public void moveTextLeft(Text text) {
		
		double actualXPosition = text.getX();
		double actualYPosition = text.getY();
		text.xProperty().unbind();
		text.yProperty().unbind();
		double xDistance = actualXPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		double yDistance = actualYPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		int rotateStatus = text.getTransforms().size() % 4;
		
		switch ( rotateStatus ) {
		case 1:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).subtract(MILLIMETER));
			break;
		case 2:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).add(MILLIMETER));
			break;
		case 3:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).add(MILLIMETER));
			break;
		default:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).subtract(MILLIMETER));
		}
	}
	
	public void moveTextRight(Text text) {
		
		double actualXPosition = text.getX();
		double actualYPosition = text.getY();
		text.xProperty().unbind();
		text.yProperty().unbind();
		double xDistance = actualXPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		double yDistance = actualYPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		int rotateStatus = text.getTransforms().size() % 4;
		
		switch ( rotateStatus ) {
		case 1:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).add(MILLIMETER));
			break;
		case 2:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).subtract(MILLIMETER));
			break;
		case 3:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).subtract(MILLIMETER));
			break;
		default:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).add(MILLIMETER));
		}
	}
	
	public void moveTextUp(Text text) {
		
		double actualXPosition = text.getX();
		double actualYPosition = text.getY();
		text.xProperty().unbind();
		text.yProperty().unbind();
		double xDistance = actualXPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		double yDistance = actualYPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		int rotateStatus = text.getTransforms().size() % 4;
		
		switch ( rotateStatus ) {
		case 1:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).add(MILLIMETER));
			break;
		case 2:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).add(MILLIMETER));
			break;
		case 3:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).subtract(MILLIMETER));
			break;
		default:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).subtract(MILLIMETER));
		}
	}
	
	public void moveTextDown(Text text) {
		
		double actualXPosition = text.getX();
		double actualYPosition = text.getY();
		text.xProperty().unbind();
		text.yProperty().unbind();
		double xDistance = actualXPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		double yDistance = actualYPosition - root.widthProperty().divide(2).subtract(A4_WIDTH / 2).get();
		int rotateStatus = text.getTransforms().size() % 4;
		
		switch ( rotateStatus ) {
		case 1:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).subtract(MILLIMETER));
			break;
		case 2:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).subtract(MILLIMETER));
			break;
		case 3:
			text.xProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(xDistance).add(MILLIMETER));
			break;
		default:
			text.yProperty().bind(root.widthProperty().divide(2).subtract(A4_WIDTH / 2).add(yDistance).add(MILLIMETER));
		}
	}
	
	public void setTextSize(Text text, int size) {
		Font font = Font.font("ariel", FontWeight.BOLD, FontPosture.REGULAR, size);
		text.setFont(font);
	}
	
	private void deleteLine(Line line) {
	if( HomeController.getConfirmationAlert("Oszlop/vezeték törlése", "Biztos, hogy törlöd a kiválaszott vonalat?") ) {
		root.getChildren().remove(line);
	}
	}
	
	private void getModifyTextWindow(Text text) {
		
		if( modifyTextWindow == null ) {
			modifyTextWindow = new ModifyTextWindow(this);
		}
		else {
			modifyTextWindow.getStage().show();
		}
		modifyTextWindow.getStage().setAlwaysOnTop(true);
		modifyTextWindow.setInputText(text);
	}

	private double getHorizontalScaledDownLengthValue(double length) {
		return horizontalScale == 1000 ? length : 1000.0  * length / horizontalScale;
	}
	
	private double getVerticalScaledDownHeightValue(double height) {
		return verticalScale == 10 ? height : 10.0 * height / verticalScale;
	}
	
		
}
