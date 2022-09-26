package hu.mvmxpert.david.giczi.electricwiredisplayer.service;

import java.util.ArrayList;
import java.util.List;

import hu.mvmxpert.david.giczi.electricwiredisplayer.model.DrawingSystemData;
import hu.mvmxpert.david.giczi.electricwiredisplayer.model.LineData;
import hu.mvmxpert.david.giczi.electricwiredisplayer.model.PillarData;
import hu.mvmxpert.david.giczi.electricwiredisplayer.model.TextData;
import hu.mvmxpert.david.giczi.electricwiredisplayer.model.WireData;

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
	
	public void removePillar(int id) {
		for (PillarData pillar : pillarData) {
			if( pillar.getId() == id ) {
				pillarData.remove(pillar);
			}
		}
	}
	
	public void removePillarText(int id) {
		for (PillarData pillar : pillarData) {
			for(TextData text : pillar.getPillarTextList()) {
				if(text.getId() == id) {
					pillar.getPillarTextList().remove(text);
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
	
	public void removeWire(int id) {
		for(WireData wire : wireData) {
			if( wire.getId() == id ) {
				wireData.remove(wire);
			}
		}
	}
	
	public void removeWireText(int id) {
		for(WireData wire : wireData) {
			for(TextData text : wire.getWireTextList()) {
				if( text.getId() == id ) {
					wire.getWireTextList().remove(text);
				}
			}
		}
	}
	
	public void addText(TextData text) {
		textData.add(text);
	}
	
	public void removeText(int id) {
		for(TextData text : textData) {
			if( text.getId() == id ) {
				textData.remove(text);
			}
		}
	}
	
	public TextData getTextData(int id) {
		TextData text = null;
		for (TextData data : textData) {
			if( data.getId() == id ) {
				text = data;
			}
		}
		return text;
	}
	
	public void addLine(LineData line) {
		lineData.add(line);
	}
	
	public void removeLine(int id) {
		for(LineData line : lineData) {
			if( line.getId() == id ) {
				lineData.remove(line);
			}
		}
	}	
	
	
	
	
	
}
