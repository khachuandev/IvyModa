package vn.devpro.FinalProject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.User;

@Service
public class UserDetailsServiceImpl extends BaseService<User> implements UserDetailsService{
	
	@Override
	public Class<User> clazz() {
		return User.class;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Lay user trong tbl_user theo username (username tu form login)
		String sql = "select * from tbl_user u where u.username='" + username + "' and u.status=1";
		User user = this.getEntityByNativeSQL(sql);
		if(user != null) {
			return user;
		}
		else return new User(); // UserDetails khong duoc null
	}	
}
