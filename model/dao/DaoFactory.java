package model.dao;

import database.Provider;
import model.dao.impl.CarDaoJDBC;
import model.dao.impl.LocationDaoJDBC;
import model.dao.impl.UnitDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoJDBC(Provider.getConnection());
    }

    public static CarDao createCarDao() {
        return new CarDaoJDBC(Provider.getConnection());
    }

    public static UnitDao createUnitDao() {
        return new UnitDaoJDBC(Provider.getConnection());
    }

    public static LocationDao createLocationDao() {
        return new LocationDaoJDBC(Provider.getConnection());
    }

}
