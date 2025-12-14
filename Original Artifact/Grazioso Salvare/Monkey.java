// Author:  Austin Brancheau

// IT 145 Section J7797

// 7-1 Project Two

// 10/13/2023

public class Monkey extends RescueAnimal {
	
	// monkey class specific attributes
	// weight is assumed pounds, lengths 
	// and heights assumed inches
	private String tailLength;
	private String bodyHeight;
	private String bodyLength;
	private String species;
	
	
	
	// default constructor
	public Monkey() {}
	
	// full constructor
	public Monkey(String name, String gender, String age, String weight, String acquisitionDate,
			String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry,
			String tailLength, String bodyHeight, String bodyLength, String species) {
		
		// calls to superclass setter methods
		setName(name);
		setGender(gender);
		setAge(age);
		setWeight(weight);
		setAcquisitionDate(acquisitionDate);
		setAcquisitionLocation(acquisitionCountry);
		setTrainingStatus(trainingStatus);
		setReserved(reserved);
		setInServiceCountry(inServiceCountry);
		setAnimalType("monkey");
		
		// assignment of Monkey class specific attributes
		this.tailLength = tailLength;
		this.bodyHeight = bodyHeight;
		this.bodyLength = bodyLength;
		this.species = species;
	}
	
	
	// getters for class attributes
	public String getTailLength() {
		return tailLength;
	}
	public String getBodyHeight() {
		return bodyHeight;
	}
	public String getBodyLength() {
		return bodyLength;
	}
	public String getSpecies() {
		return species;
	}
	
	
	// setters for class attributes
	public void setTailLength(String tailLength) {
		this.tailLength = tailLength;
	}
	public void setBodyHeight(String bodyHeight) {
		this.bodyHeight = bodyHeight;
	}
	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
	}
	public void setSpecies(String species) {
		this.species = species;
	}


}