package hu.mvmxpert.david.giczi.electricwireeditor.service;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hu.mvmxpert.david.giczi.electricwireeditor.model.DrawingSystemData;
import hu.mvmxpert.david.giczi.electricwireeditor.model.LineData;
import hu.mvmxpert.david.giczi.electricwireeditor.model.PillarData;
import hu.mvmxpert.david.giczi.electricwireeditor.model.SavedWirePoint;
import hu.mvmxpert.david.giczi.electricwireeditor.model.TextData;
import hu.mvmxpert.david.giczi.electricwireeditor.model.WireData;
import hu.mvmxpert.david.giczi.electricwireeditor.model.WirePoint;
import javafx.scene.layout.BorderPane;



public class ArchivFileBuilder {
	
	public static int id;
	private DrawingSystemData systemData;
	private List<PillarData> pillarData;
	private List<WireData> wireData;
	private List<TextData> textData;
	private List<LineData> lineData;
	
	public DrawingSystemData getSystemData() {
		return systemData;
	}
	
	public void setSystemData(DrawingSystemData systemData) {
		this.systemData = systemData;
	}

	public List<PillarData> getPillarData() {
		return pillarData;
	}

	public List<WireData> getWireData() {
		return wireData;
	}

	public List<TextData> getTextData() {
		return textData;
	}

	public List<LineData> getLineData() {
		return lineData;
	}

	public void init() {
		id = 0;
		systemData = new DrawingSystemData();
		pillarData = new ArrayList<>();
		wireData = new ArrayList<>();
		textData = new ArrayList<>();
		lineData = new ArrayList<>();
	}
	
	public static int addID() {
		id++;
		return id;
	}
	
	public void setSystemData(double lengthOfHorizontalAxis, int horizontalScale, int elevationStartValue, int verticalScale) {
		systemData.setLengthOfHorizontalAxis(lengthOfHorizontalAxis);
		systemData.setHorizontalScale(horizontalScale);
		systemData.setElevationStartValue(elevationStartValue);
		systemData.setVerticalScale(verticalScale);
	}
	
	public void addPillar(PillarData pillar) {
		pillarData.add(pillar);
	}
	
	
	public PillarData getPillarData(int id) {
		
		PillarData data = null;
		
		for (PillarData pillar : pillarData) {
			if( pillar.getId() == id) {
				data = pillar;
			}
		}

		return data;
	}
	
	public PillarData getBeginnerPillar() {
		
		if( pillarData == null || pillarData.isEmpty())
			return null;
		
		Collections.sort(pillarData);
		
		return pillarData.get(0);
	}
	
	public PillarData getLastPillar() {
		
		if( pillarData == null || pillarData.isEmpty())
			return null;
		
		Collections.sort(pillarData);
	
		return pillarData.get( pillarData.size() - 1 );
	}
	
	public void removePillar(int id, BorderPane root) {
		
		PillarData pillar = getPillarData(id);
		if( pillar == null )
			return;
		
		for(TextData pillarText : pillar.getPillarTextList()) {
		
			for(int i = root.getChildren().size() - 1; i >= 0; i--) {
				if(root.getChildren().get(i).getId() != null && 
						(Integer.valueOf(root.getChildren().get(i).getId()) == pillarText.getId() ||
						Integer.valueOf(root.getChildren().get(i).getId()) == id)) {
				root.getChildren().remove(i);
				}
			}
		}
		
		for( int i = pillarData.size() - 1; i >= 0; i-- ) {
			if( pillarData.get(i).getId() == id ) {
				pillarData.remove(i);
			}
	}
}
	public void removePillarText(int id) {
		for (PillarData pillar : pillarData) {
			for(int i = pillar.getPillarTextList().size() - 1; i >= 0; i--) {
				if( pillar.getPillarTextList().get(i).getId() == id ) {
					pillar.getPillarTextList().remove(i);
				}
			}
		}
	}
	
	public void addWire(WireData wire) {
		wireData.add(wire);
	}
	
	public WireData getWireData(int id) {
		
		WireData data = null;
		
		for (WireData wire : wireData) {
			if( wire.getId() == id ) {
				data = wire;
			}
		}
		
		return data;
	}
	
	public void removeWire(int id, BorderPane root) {
		
		WireData wire = getWireData(id);
		if( wire == null )
			return;
		
		for(TextData wireText : wire.getWireTextList()) {
			for(int i = root.getChildren().size() - 1; i >= 0; i--) {
				if( root.getChildren().get(i).getId() != null && 
						(Integer.valueOf(root.getChildren().get(i).getId()) == wireText.getId() ||
						Integer.valueOf(root.getChildren().get(i).getId()) == id)) {
					root.getChildren().remove(i);
				}
				
		}
	}
		for( int i = wireData.size() - 1; i >= 0; i-- ) {
			if( wireData.get(i).getId() == id ) {
				wireData.remove(i);
			}
		}
	}
	
	public void removeWireText(int id) {
		for(WireData wire : wireData) {
			for(int i = wire.getWireTextList().size() - 1; i >= 0; i--) {
				if(  wire.getWireTextList().get(i).getId() == id ) {
					wire.getWireTextList().remove(i);
			}
		}
	}
}
	public void addText(TextData text) {
		textData.add(text);
	}
	
	public void removeText(int id) {
		for(int i = textData.size() - 1; i >= 0; i--) {
			if( textData.get(i).getId() == id ) {
				textData.remove(textData.get(i));
			}
		}
	}
	
	public TextData getTextData(int id) {
		TextData text = null;
		for (TextData textData : textData) {
			if( textData.getId() == id ) {
				text = textData;
			}
		}
		
		for(PillarData pillarData : pillarData) {
			for(TextData textData : pillarData.getPillarTextList()) {
				if( textData.getId() == id ) {
					text = textData;
				}
			}
		}
		
		for(WireData wireData : wireData) {
			for(TextData textData : wireData.getWireTextList()) {
				if( textData.getId() == id ) {
					text = textData;
				}
			}
		}

		return text;
	}
	
	public void addLine(LineData line) {
		lineData.add(line);
	}
	
	public void removeLine(int id, BorderPane root) {
		for(int i = lineData.size() - 1; i >= 0; i--) {
			if( lineData.get(i).getId() == id ) {
				lineData.remove(lineData.get(i));
			}
		}
		for(int i = root.getChildren().size() - 1; i >= 0; i--) {
			if(root.getChildren().get(i).getId() != null && Integer.valueOf(root.getChildren().get(i).getId()) == id) {
				root.getChildren().remove(i);
			}
		}
	}	
	
	public LineData getLineData(int id) {
		
		LineData line = null;
		
		for (LineData lineData : lineData) {
			if( lineData.getId() == id) {
				line = lineData;
			}
		}
	
		return line;
	}
	
	public void changePillarDistanceText(int id) {
			
			PillarData pillarData = getPillarData(id);
			if( pillarData.getDistanceOfPillar() == 0)
				return;
			DecimalFormat df = new DecimalFormat("0.00");
			for (TextData pillarText : pillarData.getPillarTextList()) {
				String[] values = pillarText.getTextValue().split("\\s+");
			if( pillarText.getTextValue().startsWith("bal") && values.length == 2 ) {
				Double leftDistance = systemData.getLengthOfHorizontalAxis() 
						- getDistance(pillarData.getPillarTextList(), "bal");
				pillarText.setTextValue("bal " + df.format(leftDistance).replace(",", ".") + "m");
			}
			else if( pillarText.getTextValue().startsWith("közép") && values.length == 2 ) {
				Double middleDistance =  systemData.getLengthOfHorizontalAxis() 
						- getDistance(pillarData.getPillarTextList(), "közép");
				pillarText.setTextValue("közép " + df.format(middleDistance).replace(",", ".") + "m");
			}
			else if(pillarText.getTextValue().startsWith("jobb") && values.length == 2 ) {
				Double rightDistance =  systemData.getLengthOfHorizontalAxis() 
						- getDistance(pillarData.getPillarTextList(), "jobb");
				pillarText.setTextValue("jobb " + df.format(rightDistance).replace(",", ".") + "m");
		}	
	}
}	
	public void changeWireDistanceText(int id) {
		WireData wireData = getWireData(id);
		DecimalFormat df = new DecimalFormat("0.00");
		for (TextData wireText : wireData.getWireTextList()) {
	
			String[] values = wireText.getTextValue().split("\\s+");
			if( wireText.getTextValue().startsWith("bal") && values.length == 2 ) {	
				Double leftDistance = systemData.getLengthOfHorizontalAxis() 
						- getDistance(wireData.getWireTextList(), "bal");
				wireText.setTextValue("bal " + df.format(leftDistance).replace(",", ".") + "m");
			}
			else if( wireText.getTextValue().startsWith("közép") && values.length == 2 ) {
				Double middleDistance = systemData.getLengthOfHorizontalAxis() 
						- getDistance(wireData.getWireTextList(), "közép");
				wireText.setTextValue("közép " + df.format(middleDistance).replace(",", ".") + "m");
			}
			else if(wireText.getTextValue().startsWith("jobb") && values.length == 2 ) {
				Double rightDistance = systemData.getLengthOfHorizontalAxis() 
						- getDistance(wireData.getWireTextList(), "jobb");
				wireText.setTextValue("jobb " + df.format(rightDistance).replace(",", ".") + "m");
		}	
	}
}
	
	public void changeBeginnerAndLastPillarDistanceTexts() {
		PillarData lastPillar = getLastPillar();
		PillarData beginnerPillar = getBeginnerPillar();
		for( int i = lastPillar.getPillarTextList().size() - 1; i >= 0; i--) {
		String[] values = lastPillar.getPillarTextList().get(i).getTextValue().split("\\s+");
		if( (lastPillar.getPillarTextList().get(i).getTextValue().startsWith("bal") ||
			lastPillar.getPillarTextList().get(i).getTextValue().startsWith("közép") ||
			lastPillar.getPillarTextList().get(i).getTextValue().startsWith("jobb")) && values.length == 2 ) {
			beginnerPillar.getPillarTextList().add(lastPillar.getPillarTextList().get(i));
			lastPillar.getPillarTextList().remove(i);
	}
		}
	}
	
	public TextData getChosenTextData(String chosenText) {
		
		TextData chosenTextData = null;
		for ( PillarData pillarData : pillarData ) {
			for( TextData textData : pillarData.getPillarTextList() ) 
					if( textData.getTextValue().equals(chosenText))
							chosenTextData = textData;	
		}
		for(WireData wireData : wireData) {
			for( TextData textData : wireData.getWireTextList() )
					if( textData.getTextValue().equals(chosenText) )
						chosenTextData = textData;
		}
		
		for( TextData textData : textData) {
			if(textData.getTextValue().equals(chosenText))
				chosenTextData = textData;
		}	
		
		return chosenTextData;
	}
	
	
	public void addChosenTextToOwnerTextList(TextData textData, int chosenTextDataID) {
		
		textData.setOnLeftSide(false);
		for (PillarData pillar: pillarData) {
			for (TextData pillarText: pillar.getPillarTextList()) {
				if( pillarText.getId() == chosenTextDataID ) {
					textData.setType("PillarText");
					pillar.getPillarTextList().add(textData);
					return;
			}
		}
	}
		for (WireData wire : wireData) {
			for (TextData wireText : wire.getWireTextList()) {
				if( wireText.getId() == chosenTextDataID ) {
					textData.setType("WireText");
					wire.getWireTextList().add(textData);
					return;
			}
		}
	}	
		textData.setType("SingleText");
		textData.setOnLeftSide(false);
		this.textData.add(textData);
	}
	
	public double getMinElevationStartValue() {
		
	double minValue = systemData.getElevationStartValue() + 10 * systemData.getVerticalScale();
	
	for (PillarData pillarData : pillarData) {
		if( minValue > pillarData.getGroundElevation() )
			minValue = pillarData.getGroundElevation();
	}
	for(WireData wireData : wireData) {
		if( minValue > wireData.getGroundElevation() )
			minValue = wireData.getGroundElevation();
	}
		
		return minValue;
	}
	
	public double getMaxElevationStartValue() {
		
		double maxValue = 0;
		
		for (PillarData pillarData : pillarData) {
			if( maxValue < pillarData.getTopElevetaion() )
				maxValue = pillarData.getTopElevetaion();
		}
		for(WireData wireData : wireData) {
			if( maxValue < wireData.getTopElevetaion() )
				maxValue = wireData.getTopElevetaion();
		}
		
		return maxValue;
	}
	
	public List<WirePoint> getLeftWirePoints(){
		List<WirePoint> leftWirePoints = new ArrayList<>();
		
		for (PillarData pillarData : pillarData) {
			if( !isRightPillar(pillarData) )
			leftWirePoints.add(new WirePoint(pillarData.getDistanceOfPillar(), 
					pillarData.getTopElevetaion() - systemData.getElevationStartValue()));
		}
		for (WireData wireData : wireData) {
			if( !isRightWire(wireData) )
			leftWirePoints.add(new WirePoint(wireData.getDistanceOfWire(), 
					wireData.getTopElevetaion() - systemData.getElevationStartValue()));
		}
		Collections.sort(leftWirePoints);
		
		for(int i = leftWirePoints.size() - 1; i > 0; i--) {
			
			if( leftWirePoints.get(i).getDistanceOfWirePoint() == leftWirePoints.get(i - 1).getDistanceOfWirePoint()) {
				if(leftWirePoints.get(i).getElevationOfWirePoint() < leftWirePoints.get(i - 1).getElevationOfWirePoint()) {
					leftWirePoints.remove(i);
				}
				else {
					leftWirePoints.remove(--i);
				}
			}
			
		}		
		return leftWirePoints;
	}
	
	private boolean isRightPillar(PillarData pillar) {
		int leftPillarText = 0;
		for (TextData text : pillar.getPillarTextList()) {
			if(text.getTextValue().startsWith("bal"))
				leftPillarText++;
		}
		return leftPillarText == 0;
	}
	
	
	private boolean isRightWire(WireData wire) {
		int leftWireText = 0;
		for (TextData text : wire.getWireTextList()) {
			if(text.getTextValue().startsWith("bal"))
				leftWireText++;
		}
		return leftWireText == 0;
	}
	
	public List<WirePoint> getRightWirePoints(){
		List<WirePoint> rightWirePoints = new ArrayList<>();
		for (PillarData pillarData : pillarData) {
			if( !isLeftPillar(pillarData)) {
				for (TextData text : pillarData.getPillarTextList()) {
					if( text.getTextValue().startsWith("jobb") && text.isAtTop()) {
						rightWirePoints.add(new WirePoint(pillarData.getDistanceOfPillar(), 
								Double.parseDouble(text.getTextValue().substring(14, text.getTextValue().indexOf('m')))
								- systemData.getElevationStartValue()));
					}
				}	
			}
		}
		for (WireData wireData : wireData) {
			if( !isLeftWire(wireData)) {
				for (TextData text : wireData.getWireTextList()) {
					if( text.getTextValue().startsWith("jobb") && text.isAtTop()) {
						rightWirePoints.add(new WirePoint(wireData.getDistanceOfWire(), 
								Double.parseDouble(text.getTextValue().substring(14, text.getTextValue().indexOf('m')))
								- systemData.getElevationStartValue()));
					}
				}	
			}
		}
		Collections.sort(rightWirePoints);
		for(int i = rightWirePoints.size() - 1; i > 0; i--) {
			
			if( rightWirePoints.get(i).getDistanceOfWirePoint() == rightWirePoints.get(i - 1).getDistanceOfWirePoint()) {
				if(rightWirePoints.get(i).getElevationOfWirePoint() < rightWirePoints.get(i - 1).getElevationOfWirePoint()) {
					rightWirePoints.remove(i);
				}
				else {
					rightWirePoints.remove(--i);
				}
			}
		}
		
		return rightWirePoints;
	}
	
	private boolean isLeftPillar(PillarData pillar) {
		int rightPillarText = 0;
		for (TextData text : pillar.getPillarTextList()) {
			if(text.getTextValue().startsWith("jobb"))
				rightPillarText++;
		}
		return rightPillarText == 0;
	}
	
	
	private boolean isLeftWire(WireData wire) {
		int rightWireText = 0;
		for (TextData text : wire.getWireTextList()) {
			if(text.getTextValue().startsWith("jobb"))
				rightWireText++;
		}
		return rightWireText == 0;
	}
	
	public List<SavedWirePoint> getWirePointsForSaving(String type){
		
		List<SavedWirePoint> points = new ArrayList<>();
		if( pillarData.isEmpty() )
			return points;
		Collections.sort(pillarData);
		SavedWirePoint.START_ELEVATION = pillarData.get(0).getGroundElevation();
		for (PillarData pillarData : pillarData) {
			for (TextData pillarText : pillarData.getPillarTextList()) {
		SavedWirePoint savedPoint = null;
		if( pillarText.getTextValue().startsWith(type) && pillarText.isAtTop() ) {
			savedPoint = 
						new SavedWirePoint(	!pillarData.getPillarTextList().get(0).getTextValue().startsWith(type) ?
						pillarData.getPillarTextList().get(0).getTextValue().replace('.', '_') + "oszlop_" + pillarText.getTextValue().substring(0,
						pillarText.getTextValue().indexOf('.')).replace(' ', '_') : "Noname", 
						getDistance(pillarData.getPillarTextList(), type) == null ? pillarData.getDistanceOfPillar()
								: getDistance(pillarData.getPillarTextList(), type),
						Double.parseDouble(pillarText.getTextValue()
								.substring(pillarText.getTextValue().indexOf("Bf.") + 4, pillarText.getTextValue().indexOf("m"))));
		}
		if( savedPoint != null )
		points.add(savedPoint);
	}
}
		for (WireData wireData : wireData) {
			for (TextData wireText: wireData.getWireTextList()) {
		SavedWirePoint savedPoint = null;
		if( wireText.getTextValue().startsWith(type) && wireText.isAtTop() ) {
			savedPoint = 
						new SavedWirePoint( !wireData.getWireTextList().get(0).getTextValue().startsWith(type) ?
						wireData.getWireTextList().get(0).getTextValue().replace(' ', '_') + "_" + wireText.getTextValue().substring(0,
						wireText.getTextValue().indexOf('.')).replace(' ', '_') : "Noname", 
						getDistance(wireData.getWireTextList(), type) == null ? wireData.getDistanceOfWire() 
								: getDistance(wireData.getWireTextList(), type),
						Double.parseDouble(wireText.getTextValue()
								.substring(wireText.getTextValue().indexOf("Bf.") + 4, wireText.getTextValue().indexOf("m"))));
		}
		if( savedPoint != null )
		points.add(savedPoint);
			}	
	}
		return points;
	}
	
	public Double getDistance(List<TextData> textList, String type) {
		Double distance = null;
		for (TextData textData : textList) {
			String[] values = textData.getTextData().split("\\s+");
			if( textData.getTextValue().startsWith(type) && values.length == 2) {
				
				try {
					distance = Double.parseDouble(values[1].substring(0, values[1].indexOf("m")));
					
				} catch (Exception e) {
					return distance;
				}
			}
		}
		
		return distance;
	}
	
	public Double getPillarElevation(PillarData pillar, String type) {
		Double elevation = null;
		
		for (TextData pillarText: pillar.getPillarTextList()) {
			if( pillarText.getTextValue().startsWith(type) && pillarText.isAtTop() ) {
				try {
				elevation = Double.parseDouble(pillarText.getTextValue()
						.substring(pillarText.getTextValue().indexOf("Bf.") + 4, pillarText.getTextValue().indexOf("m")));
				}catch (Exception e) {
					elevation = pillar.getDistanceOfPillar();
				}
			}
		}
		
		return elevation;
	}
	
}
