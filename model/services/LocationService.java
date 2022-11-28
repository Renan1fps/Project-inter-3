package model.services;

import model.dao.DaoFactory;
import model.dao.LocationDao;
import model.entities.Location;

import java.util.List;

public class LocationService implements LocationDao {

    private final LocationDao dao = DaoFactory.createLocationDao();

    @Override
    public void insert(Location obj) {
        dao.insert(obj);
    }

    @Override
    public void update(Location obj) {
        dao.update(obj);
    }

    @Override
    public List<Location> findCondition(int idUser) {
        return dao.findCondition(idUser);
    }

    @Override
    public List<Location> findAll() {
        return dao.findAll();
    }
}
