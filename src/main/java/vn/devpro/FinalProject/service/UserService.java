package vn.devpro.FinalProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.User;

@Service
public class UserService extends BaseService<User>{

	@Override
	public Class<User> clazz() {
		return User.class;
	}
	
	public List<User> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_user WHERE status=1");
	}
	
	@Transactional
	public void deleteUserById(int id) {
		super.deleteById(id);
	}
	
	@Transactional
	public void inactiveUser(User user) {
		super.saveOrUpdate(user);
	}
}
