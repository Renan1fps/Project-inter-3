package model.dao;

import model.entities.Car;
import model.entities.Location;

import java.util.List;

public interface LocationDao {
    void insert(Location obj);
    void update(Location obj);
    List<Location> findCondition(int idUser);
    List<Location> findAll();
}
