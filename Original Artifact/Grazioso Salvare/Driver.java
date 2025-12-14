// Author:  Austin Brancheau

// IT 145 Section J7797

// 7-1 Project Two

// 10/13/2023

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	// Arraylists for storing all dogs, all monkeys, and all available service animals
	// note: as there is no way to make an animal unreserved and no way to change 
	// training status, only test values from initializeDogList() and initializeMonkeyList()
	// may be added to the availableList
	private static ArrayList<Dog> dogList = new ArrayList<Dog>();
	private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
	private static ArrayList<RescueAnimal> availableList = new ArrayList<RescueAnimal>();

	public static void main(String[] args) {

		// initializes lists for feature testing
		initializeDogList();
		initializeMonkeyList();

		// variables for taking input from the console
		Scanner scnr = new Scanner(System.in);
		String userInput;
		
		// program's main loop
		do {
			// displays menu, takes input and calls desired method
			displayMenu();
			userInput = scnr.nextLine();

			switch (userInput) {
			case "1":
				intakeNewDog(scnr);
				break;

			case "2":
				intakeNewMonkey(scnr);
				break;

			case "3":
				reserveAnimal(scnr);
				break;

			case "4":
				printAnimals(dogList);
				break;

			case "5":
				printAnimals(monkeyList);
				break;

			case "6":
				printAnimals(availableList);
				break;

			case "q":
				break;

			default:
				System.out.println("Invalid input");
				break;
			}
		} while (!userInput.equalsIgnoreCase("q"));
		scnr.close();

	}

	// This method prints the menu options
	public static void displayMenu() {
		System.out.println("\n\n");
		System.out.println("\t\t\t\tRescue Animal System Menu");
		System.out.println("[1] Intake a new dog");
		System.out.println("[2] Intake a new monkey");
		System.out.println("[3] Reserve an animal");
		System.out.println("[4] Print a list of all dogs");
		System.out.println("[5] Print a list of all monkeys");
		System.out.println("[6] Print a list of all animals that are not reserved");
		System.out.println("[q] Quit application");
		System.out.println();
		System.out.println("Enter a menu selection");
	}

	// Adds dogs to a list for testing
	public static void initializeDogList() {
		Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake",
				false, "United States");
		Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false,
				"United States");
		Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true,
				"Canada");

		Dog dog4 = new Dog("Rover", "Beagle", "male", "4", "25.6", "12-12-2019", "United States", "in service", false,
				"United States");

		dogList.add(dog1);
		dogList.add(dog2);
		dogList.add(dog3);
		dogList.add(dog4);
		
		// if the above dogs are available for reservation, add to the available list
		// uses if statements in case test values are modified
		if(dog1.getReserved() == false && dog1.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(dog1);
		}
		if(dog2.getReserved() == false && dog2.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(dog2);
		}
		if(dog3.getReserved() == false && dog3.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(dog3);
		}
		if(dog4.getReserved() == false && dog4.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(dog4);
		}
	}

	// Adds monkeys to a list for testing
	public static void initializeMonkeyList() {
		Monkey monkey1 = new Monkey("George", "male", "2", "20", "01-01-06", "Morocco", "intake", false,
				"United States", "0", "24", "12", "macaque");
		Monkey monkey2 = new Monkey("Kong", "male", "5", "50", "01-01-33", "United States", "Phase I", false, "Japan",
				"0", "24", "12", "squirrel monkey");
		Monkey monkey3 = new Monkey("Tammy", "female", "7", "15", "01-01-10", "Brazil", "in service", false, "Mexico",
				"0", "24", "12", "tamarin");

		monkeyList.add(monkey1);
		monkeyList.add(monkey2);
		monkeyList.add(monkey3);
		
		// if the above monkeys are available for reservation, add to the available list
		// uses if statements in case test values are modified 
		if(monkey1.getReserved() == false && monkey1.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(monkey1);
		}
		if(monkey2.getReserved() == false && monkey2.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(monkey2);
		}
		if(monkey3.getReserved() == false && monkey3.getTrainingStatus().equalsIgnoreCase("in service")) {
			availableList.add(monkey3);
		}
		
	}

	// Instantiates and adds a new dog to the appropriate list
	public static void intakeNewDog(Scanner scanner) {

		// collects names, declares all variables needed for a dog
		System.out.println("What is the dog's name?");
		String name = scanner.nextLine();
		String breed;
		String gender = "";
		String age = "-1";
		String weight = "-1";
		String acquisitionDate;
		String acquisitionCountry;
		String trainingStatus = "intake";
		boolean reserved = false;
		String inServiceCountry = "n/a";
		boolean validNumber = false; // for validating user inputs

		// checks whether the specified dog is already in the system (duplicate names
		// not allowed)
		for (Dog dog : dogList) {
			if (dog.getName().equalsIgnoreCase(name)) {
				System.out.println("\n\nThis dog is already in our system\n\n");
				return; // returns to menu
			}
		}
		System.out.println("What is " + name + "'s breed?");
		breed = scanner.nextLine();

		// validates dog's gender
		while (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
			System.out.println("What is " + name + "'s gender?");
			gender = scanner.nextLine();
			if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
				System.out.println("Gender should be input as either male or female.");
			}
		}

		// validates dog's age by first testing whether the input is a number,
		// then checking if the number is within reasonable bounds. 40 was
		// chosen as the upper bound as the oldest living dog is 31 years old
		while (validNumber == false) {
			try {
				System.out.println("What is " + name + "'s age?");
				age = scanner.nextLine();

				if (Integer.parseInt(age) <= 0 || Integer.parseInt(age) >= 40) {
					System.out.println("Invalid age; must be a number greater than zero, less than 40.");
				} else {
					validNumber = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid age; must be a whole number.");
			}
		}
		// resets input validation variable for further use
		validNumber = false;

		// validates dog's weight by first testing whether the input is a number,
		// then checking if the number is within reasonable bounds. 400 was
		// chosen as the upper bound as the heaviest recorded dog was over 300lbs
		while (validNumber == false) {
			try {
				System.out.println("What is " + name + "'s weight?");
				weight = scanner.nextLine();

				if (Double.parseDouble(weight) <= 0 || Double.parseDouble(weight) >= 400) {
					System.out.println("Invalid weight; must be a number greater than zero, less than 400.");
				} else {
					validNumber = true;
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Invalid weight; must be a number.");
			}
		}

		// acquires acquisition date and country
		System.out.println("What is the acquisition date?");
		acquisitionDate = scanner.nextLine();

		System.out.println("What is the acquisition country?");
		acquisitionCountry = scanner.nextLine();

		Dog dog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus,
				reserved, inServiceCountry);
		
		dogList.add(dog);
	}

	// Instantiates and adds a new monkey to the appropriate list
	public static void intakeNewMonkey(Scanner scanner) {
		// collects names, declares all variables needed for a monkey
		System.out.println("What is the monkey's name?");
		String name = scanner.nextLine();
		String tailLength = "";
		String bodyHeight = "";
		String bodyLength = "";
		String species = "";
		String gender = "";
		String age = "-1";
		String weight = "-1";
		String acquisitionDate;
		String acquisitionCountry;
		String trainingStatus = "intake";
		boolean reserved = false;
		String inServiceCountry = "n/a";
		boolean validInput = false; // for validating user inputs

		// checks whether the specified monkey is already in the system (duplicate names
		// not allowed)
		if (!monkeyList.isEmpty()) {
			for (Monkey monkey : monkeyList) {
				if (monkey.getName().equalsIgnoreCase(name)) {
					System.out.println("\n\nThis monkey is already in our system\n\n");
					return; // returns to menu
				}
			}
		}

		// validates monkey species
		while (validInput == false) {
			System.out.println("What is " + name + "'s species?");
			species = scanner.nextLine();

			switch (species.toLowerCase()) {

			case "capuchin":
			case "guenon":
			case "macaque":
			case "marmoset":
			case "squirrel monkey":
			case "tamarin":
				validInput = true;
				break;
			default:
				System.out.println("Invalid species. Currently accepting Capuchin, Guenon, "
						+ "Macaque, Marmoset, Squirrel monkey, and Tamarin monkeys.");
				break;
			}
		}

		// validates monkey's gender
		while (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
			System.out.println("What is " + name + "'s gender?");
			gender = scanner.nextLine();
			if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
				System.out.println("Gender should be input as either male or female.");
			}
		}

		// validates monkey's age by first testing whether the input is a number,
		// then checking if the number is within reasonable bounds. 60 was
		// chosen as the upper bound as the oldest living monkey was 53 years old
		while (validInput == false) {
			try {
				System.out.println("What is " + name + "'s age?");
				age = scanner.nextLine();

				if (Integer.parseInt(age) <= 0 || Integer.parseInt(age) >= 60) {
					System.out.println("Invalid age; must be a number greater than zero, less than 60.");
				} else {
					validInput = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid age; must be a whole number.");
			}
		}
		// resets input validation variable for further use
		validInput = false;

		// validates monkey's weight by first testing whether the input is a number,
		// then checking if the number is within reasonable bounds. 80 was
		// chosen as the upper bound as the heaviest recorded monkey of selected
		// species was 66lbs
		while (validInput == false) {
			try {
				System.out.println("What is " + name + "'s weight?");
				weight = scanner.nextLine();

				if (Double.parseDouble(weight) <= 0 || Double.parseDouble(weight) >= 80) {
					System.out.println("Invalid weight; must be a number greater than zero, less than 80.");
				} else {
					validInput = true;
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Invalid weight; must be a number.");
			}
		}
		// resets input validation variable for further use
		validInput = false;

		// acquires acquisition date and country
		System.out.println("What is the acquisition date?");
		acquisitionDate = scanner.nextLine();

		System.out.println("What is the acquisition country?");
		acquisitionCountry = scanner.nextLine();

		// validates monkey's tail length. Certain species have no
		// tail, so zero length is accepted
		while (validInput == false) {
			try {
				System.out.println("What is " + name + "'s tail length?");
				tailLength = scanner.nextLine();

				if (Integer.parseInt(tailLength) < 0) {
					System.out.println("Invalid tail length; must be a number greater than or equal to zero.");
				} else {
					validInput = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid tail length; must be a whole number.");
			}
		}
		// resets input validation variable for further use
		validInput = false;

		// validates monkey's body height. allows any height above zero
		while (validInput == false) {
			try {
				System.out.println("What is " + name + "'s body height?");
				bodyHeight = scanner.nextLine();

				if (Integer.parseInt(bodyHeight) <= 0) {
					System.out.println("Invalid body height; must be a number greater than zero.");
				} else {
					validInput = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid body height; must be a whole number.");
			}
		}
		// resets input validation variable for further use
		validInput = false;

		// validates monkey's body length. Allows any length above zero
		while (validInput == false) {
			try {
				System.out.println("What is " + name + "'s body length?");
				bodyLength = scanner.nextLine();

				if (Integer.parseInt(bodyLength) <= 0) {
					System.out.println("Invalid body length; must be a number greater than zero.");
				} else {
					validInput = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid body length; must be a whole number.");
			}
		}

		// adds monkey to the array list
		Monkey monkey = new Monkey(name, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus,
				reserved, inServiceCountry, tailLength, bodyHeight, bodyLength, species);
		monkeyList.add(monkey);
	}

	// finds the animal by animal type and in service country, if found sets animal to reserved
	public static void reserveAnimal(Scanner scanner) {
		// variables for storing each user input and for checking whether a animal that 
		// meets the conditions is found
		String animalType = "";
		String animalCountry;
		boolean animalFound = false;
		
		// Takes in animal type from user, looping until either dog or monkey has been entered
		while((!animalType.equalsIgnoreCase("dog")) && (!animalType.equalsIgnoreCase("monkey"))) {
			
			System.out.println("Please enter desired animal type (dog / monkey)");
			animalType = scanner.nextLine();
			
			if((!animalType.equalsIgnoreCase("dog")) && (!animalType.equalsIgnoreCase("monkey"))) {
				System.out.println("Our current offering of rescue animals includes dogs and moneys.");
			}
		}
		// acquires service country
		System.out.println("Please enter country of service");
		animalCountry = scanner.nextLine();
		
		// Searches through either the dog or monkey list based on prior input to find the first
		// animal in the list of specified type that is both in service and not reserved
		if(animalType.equalsIgnoreCase("dog")) {
			for(Dog dog : dogList) {
				
				if(dog.getInServiceLocation().equalsIgnoreCase(animalCountry) && !dog.getReserved()
						&& dog.getTrainingStatus().equalsIgnoreCase("in service")) {
					System.out.println(dog.getName() + " is available for service. Reserving now...");
					dog.setReserved(true);
					animalFound = true;
					availableList.remove(dog);
					System.out.println(dog.getName() + " has been reserved for service.");
					break;
				}
			}
			if(animalFound == false) {
				System.out.println("There are currently no dogs available in specified country.");
			} 
		}
		else if(animalType.equalsIgnoreCase("monkey")) {
			for(Monkey monkey : monkeyList) {
				
				if(monkey.getInServiceLocation().equalsIgnoreCase(animalCountry) && !monkey.getReserved()
						&& monkey.getTrainingStatus().equalsIgnoreCase("in service")) {
					System.out.println(monkey.getName() + " is available for service. Reserving now...");
					monkey.setReserved(true);
					animalFound = true;
					availableList.remove(monkey);
					System.out.println(monkey.getName() + " has been reserved for service.");
					break;
				}
			}
			if(animalFound == false) {
				System.out.println("There are currently no monkeys available in specified country.");
			} 
		}
		
	}

	// prints animals, including the animal name, status, acquisition country and if the animal is reserved.
	// The printAnimals() method uses a generic parameter to accept an ArrayList of types RescueAnimal 
	// or one of its subclasses, Dog and Monkey. prints animals from specified list.
	public static void printAnimals(ArrayList<? extends RescueAnimal> animals) {
		
		// if the specified list has no animals in it, inform user
		if(animals.isEmpty()) {
			System.out.println("There are no animals that match your search.");
		}
		
		// prints all animals in the list
		for(RescueAnimal animal : animals) {
			System.out.println("Animal name: " + animal.getName()
					+ "\nStatus: " + animal.getTrainingStatus()
					+ "\nAcquisition Country: " + animal.getAcquisitionLocation()
					+ "\nReserved: " + animal.getReserved() + "\n");
		}
		
	}
}
