package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Car;

public class CarService {
	
	public List<Car> findAll() {
		List<Car> carList = new ArrayList<>();
		carList.add(new Car(1,"Nome1", "Marca1", "Premium"));
		carList.add(new Car(2,"Nome2", "Marca2", "Premium"));
		carList.add(new Car(3,"Nome3", "Marca3", "basico"));
		
		return carList;
	}

}
