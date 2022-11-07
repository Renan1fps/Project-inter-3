package model.dao;

import database.Provider;
import model.dao.impl.CarDaoJDBC;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(Provider.getConnection());
	}

	public static CarDao createCarDao() {
		return new CarDaoJDBC(Provider.getConnection());
	}

}
