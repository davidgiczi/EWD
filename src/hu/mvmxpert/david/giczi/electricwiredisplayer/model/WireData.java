package hu.mvmxpert.david.giczi.electricwiredisplayer.model;

import java.util.List;

public class WireData {

	
	private int id;
	private double groundElevation;
	private double topElevetaion;
	private double distanceOfPillar;
	private List<TextData> wireTextList;
	private boolean hasCap;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getGroundElevation() {
		return groundElevation;
	}
	public void setGroundElevation(double groundElevation) {
		this.groundElevation = groundElevation;
	}
	public double getTopElevetaion() {
		return topElevetaion;
	}
	public void setTopElevetaion(double topElevetaion) {
		this.topElevetaion = topElevetaion;
	}
	public double getDistanceOfPillar() {
		return distanceOfPillar;
	}
	public void setDistanceOfPillar(double distanceOfPillar) {
		this.distanceOfPillar = distanceOfPillar;
	}
	
	public List<TextData> getWireTextList() {
		return wireTextList;
	}
	public void setWireTextList(List<TextData> wireTextList) {
		this.wireTextList = wireTextList;
	}
	
	public boolean isHasCap() {
		return hasCap;
	}
	public void setHasCap(boolean hasCap) {
		this.hasCap = hasCap;
	}

}
