package model.dao;

import model.entities.Car;

import java.util.List;

public interface CarDao {
    void insert(Car obj);
    void update(Car obj);
    void deleteById(int id);
    List<Car> findCondition(Car obj);
    List<Car> findAll(boolean tipoBasico);
    Car finOne(int id);
}
