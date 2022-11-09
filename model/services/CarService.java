package model.services;

import java.util.List;

import model.dao.CarDao;
import model.dao.DaoFactory;
import model.entities.Car;

public class CarService {

	private final CarDao dao = DaoFactory.createCarDao();

	public List<Car> findAll() {
		return dao.findAll();
		//carList.add(new Car(1, "BMW", "NORMAL", 2021, "PRETO", 150000, true, true, true, true, true, true, true, true));
		//carList.add(new Car(2, "Volkswagen", "NORMAL", 2018, "BRANCO", 45000, true, false, true, true, true, true, false, false));
	}
	public List<Car> findOne() {
		return dao.finOne();
		//carList.add(new Car(1, "BMW", "NORMAL", 2021, "PRETO", 150000, true, true, true, true, true, true, true, true));
		//carList.add(new Car(2, "Volkswagen", "NORMAL", 2018, "BRANCO", 45000, true, false, true, true, true, true, false, false));
	}


}
