package model.dao;

import database.Provider;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(Provider.getConnection());
	}

}
