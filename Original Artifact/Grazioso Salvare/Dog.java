// Author:  Austin Brancheau

// IT 145 Section J7797

// 7-1 Project Two

// 10/13/2023


public class Dog extends RescueAnimal {

	// Instance variable
	private String breed;

	// Constructor
	public Dog(String name, String breed, String gender, String age, String weight, String acquisitionDate,
			String acquisitionCountry, String trainingStatus, boolean reserved, String inServiceCountry) {
		setName(name);
		setBreed(breed);
		setGender(gender);
		setAge(age);
		setWeight(weight);
		setAcquisitionDate(acquisitionDate);
		setAcquisitionLocation(acquisitionCountry);
		setTrainingStatus(trainingStatus);
		setReserved(reserved);
		setInServiceCountry(inServiceCountry);
		setAnimalType("dog");
	}

	// Accessor Method
	public String getBreed() {
		return breed;
	}

	// Mutator Method
	public void setBreed(String dogBreed) {
		breed = dogBreed;
	}

}
