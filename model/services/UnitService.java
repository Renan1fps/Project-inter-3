package model.services;

import model.dao.DaoFactory;
import model.dao.UnitDao;
import model.entities.Unit;

import java.util.List;

public class UnitService implements UnitDao {

    private final UnitDao dao = DaoFactory.createUnitDao();

    @Override
    public List<Unit> findAll() {
        return dao.findAll();
    }
}
