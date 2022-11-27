package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class UserService {

	private final UserDao dao = DaoFactory.createUserDao();
	
	public List<User> findAll() {
		return dao.findAll();
	}
	
	
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public void saveOrUpdate(User obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(User obj) {
		dao.deleteById(obj.getId());
	}
}
