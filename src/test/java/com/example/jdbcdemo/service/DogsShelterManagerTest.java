package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Dog;
import com.example.jdbcdemo.domain.Shelter;

public class DogsShelterManagerTest {

	DogsShelterManager DogsShelterManager = new DogsShelterManager();

	private final static String NAME_SCHRONISKA_1 = "Animals";
	private final static int NRTEL_SCHRONISKA_1 = 5856222;
	private final static String HASLOREKLAMOWE_SCHRONISKA_1 = "Zaopiekuj sie zwierzeciem";
	
	private final static String NAME_SCHRONISKA_2 = "Promyk";
	private final static int NRTEL_SCHRONISKA_2 = 5822222;
	private final static String HASLOREKLAMOWE_SCHRONISKA_2 = "Przygarnij go";
	
	private final static Shelter SHELTER_ANIMALS = new Shelter(
			NAME_SCHRONISKA_1, NRTEL_SCHRONISKA_1, HASLOREKLAMOWE_SCHRONISKA_1);
	private final static Shelter SHELTER_2 = new Shelter(
			NAME_SCHRONISKA_2, NRTEL_SCHRONISKA_2, HASLOREKLAMOWE_SCHRONISKA_2);

	private final static String NAME_PSA_1 = "Sara";
	private final static int YOB_PSA_1 = 10102014;
	private final static String RASA_PSA_1 = "Jamnik";
	
	private final static String NAME_PSA_2 = "Stachu";
	private final static int YOB_PSA_2 = 222222;
	private final static String RASA_PSA_2 = "York";
	
	private final static String NAME_PSA_3 = "Borys";
	private final static int YOB_PSA_3 = 33333;
	private final static String RASA_PSA_3 = "Owczarek";

	@Test
	public void checkConnection() {
		assertNotNull(DogsShelterManager.getConnection());
	}

	@Test
	public void checkAddingSchronisko() {

		Shelter shelter = new Shelter(SHELTER_ANIMALS.getName(),
				SHELTER_ANIMALS.getNrTel(), SHELTER_ANIMALS.getAdvertisingLabel());

		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(shelter));

		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();
		Shelter ShelterRetrieved = schroniska.get(0);

		assertEquals(NAME_SCHRONISKA_1, ShelterRetrieved.getName());
		assertEquals(NRTEL_SCHRONISKA_1,
				ShelterRetrieved.getNrTel());
		assertEquals(HASLOREKLAMOWE_SCHRONISKA_1,
				ShelterRetrieved.getAdvertisingLabel());
	}

	@Test
	public void checkAddingPiesWithForeginID() {
		
		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));

		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();
		Shelter ShelterRetrieved = schroniska.get(0);

		Dog Dog = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,ShelterRetrieved.getId());

		DogsShelterManager.clearPiess();
		assertEquals(1, DogsShelterManager.addPies(Dog));

		List<Dog> Piess = DogsShelterManager.getAllPiess();
		Dog PiesRetrieved = Piess.get(0);

		assertEquals(NAME_PSA_1, PiesRetrieved.getName());
		assertEquals(YOB_PSA_1, PiesRetrieved.getYob());
		assertEquals(RASA_PSA_1, PiesRetrieved.getBreed());
		assertEquals(ShelterRetrieved.getId(), PiesRetrieved.getId_shelter());
	}
	
	@Test
	public void checkGettingPiesByShelter() {
		
		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_2));

		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();
		Shelter Shelter1 = schroniska.get(0);
		Shelter Shelter2 = schroniska.get(1);

		Dog Pies1 = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,Shelter1.getId());
		
		Dog Pies2 = new Dog(NAME_PSA_2, YOB_PSA_2, RASA_PSA_2,Shelter2.getId());
		Dog Pies3 = new Dog(NAME_PSA_3, YOB_PSA_3, RASA_PSA_3,Shelter2.getId());
		
		DogsShelterManager.clearPiess();
		assertEquals(1, DogsShelterManager.addPies(Pies1));
		assertEquals(1, DogsShelterManager.addPies(Pies2));
		assertEquals(1, DogsShelterManager.addPies(Pies3));

		List<Dog> PsyZSchronika1 = DogsShelterManager.GetPsyNalezacegoDoSchronika(Shelter1);
		List<Dog> PsyZSchronika2 = DogsShelterManager.GetPsyNalezacegoDoSchronika(Shelter2);			

		assertEquals(1, PsyZSchronika1.size());
		assertEquals(2, PsyZSchronika2.size());
	}
	
	@Test
	public void checkSettingNewShelterToPies() {
		
		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_2));

		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();

		Dog Pies1 = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,schroniska.get(0).getId());
		Dog Pies2 = new Dog(NAME_PSA_2, YOB_PSA_2, RASA_PSA_2,schroniska.get(0).getId());
		Dog Pies3 = new Dog(NAME_PSA_3, YOB_PSA_3, RASA_PSA_3,schroniska.get(0).getId());
		
		DogsShelterManager.clearPiess();
		assertEquals(1, DogsShelterManager.addPies(Pies1));
		assertEquals(1, DogsShelterManager.addPies(Pies2));
		assertEquals(1, DogsShelterManager.addPies(Pies3));
		
		Pies1 = DogsShelterManager.GetPiesByName(Pies1.getName());

		assertEquals(1,DogsShelterManager.SetSchroniskToPies(schroniska.get(1), Pies1));
			
		Dog zmieniony = DogsShelterManager.GetPiesByName(Pies1.getName());
		assertEquals(zmieniony.getId_shelter(),schroniska.get(1).getId());
		
		List<Dog> psyNIEzmienione = new ArrayList<Dog>();
		psyNIEzmienione.add(DogsShelterManager.GetPiesByName(Pies2.getName()));
		psyNIEzmienione.add(DogsShelterManager.GetPiesByName(Pies3.getName()));
		
		assertEquals(psyNIEzmienione.get(0).getId_shelter(),schroniska.get(0).getId());
		assertEquals(psyNIEzmienione.get(1).getId_shelter(),schroniska.get(0).getId());	
	}
	
	@Test
	public void checkDeletingPsyByShelter(){
		
		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_2));

		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();

		Dog Pies1 = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,schroniska.get(0).getId());
		Dog Pies2 = new Dog(NAME_PSA_2, YOB_PSA_2, RASA_PSA_2,schroniska.get(1).getId());
		Dog Pies3 = new Dog(NAME_PSA_3, YOB_PSA_3, RASA_PSA_3,schroniska.get(0).getId());
		
		DogsShelterManager.clearPiess();
		assertEquals(1, DogsShelterManager.addPies(Pies1));
		assertEquals(1, DogsShelterManager.addPies(Pies2));
		assertEquals(1, DogsShelterManager.addPies(Pies3));
		
		assertEquals(1,DogsShelterManager.DeletePsyWhereShelter(schroniska.get(1)));
		assertEquals(2,DogsShelterManager.DeletePsyWhereShelter(schroniska.get(0)));
	}
	
	@Test
	public void checkDeleteAllSchronika()
	{
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_2));
		DogsShelterManager.clearSchrniska();
		assertEquals(0, DogsShelterManager.getAllSchroniska().size());
	}
	
	@Test
	public void checkDeleteAllPsy()
	{
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();
		Dog Pies1 = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,schroniska.get(0).getId());
		Dog Pies2 = new Dog(NAME_PSA_2, YOB_PSA_2, RASA_PSA_2,schroniska.get(0).getId());
		assertEquals(1, DogsShelterManager.addPies(Pies1));
		assertEquals(1, DogsShelterManager.addPies(Pies2));
		DogsShelterManager.clearPiess();
		assertEquals(0, DogsShelterManager.getAllPiess().size());
	}
	
	@Test
	public void checkGetAllPsy()
	{
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		DogsShelterManager.clearPiess();
		List<Shelter> schroniska = DogsShelterManager.getAllSchroniska();
		Dog Pies1 = new Dog(NAME_PSA_1, YOB_PSA_1, RASA_PSA_1,schroniska.get(0).getId());
		Dog Pies2 = new Dog(NAME_PSA_2, YOB_PSA_2, RASA_PSA_2,schroniska.get(0).getId());	
		assertEquals(1, DogsShelterManager.addPies(Pies1));
		assertEquals(1, DogsShelterManager.addPies(Pies2));
		assertEquals(2, DogsShelterManager.getAllPiess().size());
	}
	
	@Test
	public void checkGetAllSchronika()
	{
		DogsShelterManager.clearSchrniska();
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));
		assertEquals(1, DogsShelterManager.addShelter(SHELTER_ANIMALS));		
		assertEquals(2, DogsShelterManager.getAllSchroniska().size());
	}

}
