package model.dao;

import model.entities.Unit;

import java.util.List;

public interface UnitDao {
    List<Unit> findAll();
}
