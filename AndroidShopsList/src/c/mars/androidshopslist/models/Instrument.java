package c.mars.androidshopslist.models;

public class Instrument {
	public Integer id;
	public String brand;
	public String model;	
	public Type type;
	public Double price;
	
	public enum Type {
		GUITAR,
		PIANO,
		OTHER
	}
	
	public Instrument(String brand, String model, Type type, Double price) {
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.price = price;
	}
}
