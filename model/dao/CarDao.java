package model.dao;

import model.entities.Car;
import utils.SearchParams;

import java.util.List;

public interface CarDao {
    void insert(Car obj);
    void update(Car obj);
    void deleteById(int id);
    List<Car> findCondition(Car obj);
    List<Car> findAll(boolean tipoBasico);
    List<Car> finOne();
}
