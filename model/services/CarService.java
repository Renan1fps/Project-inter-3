package model.services;

import java.util.List;

import model.dao.CarDao;
import model.dao.DaoFactory;
import model.entities.Car;
import utils.SearchParams;

public class CarService implements CarDao {

    private final CarDao dao = DaoFactory.createCarDao();

    @Override
    public void insert(Car obj) {

    }

    @Override
    public void update(Car obj) {
        dao.update(obj);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Car> findCondition(Car obj) {
        return dao.findCondition(obj);
    }

    public List<Car> findAll(boolean tipoPremium) {
        return dao.findAll(tipoPremium);
    }

    @Override
    public List<Car> finOne() {
        return dao.finOne();
    }
}
