package vn.devpro.FinalProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.Role;

@Service
public class RoleService extends BaseService<Role>{
	@Override
	public Class<Role> clazz() {
		return Role.class;
	}
	
	public List<Role> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_role WHERE status = 1");
	}
	
	@Transactional
	public void deleteProductId(int id) {
		super.deleteById(id);
	}
	
	@Transactional
	public void inactiveRole(Role role) {
		super.saveOrUpdate(role);
	}

	public Role getRoleByName(String name) {
		String sql = "SELECT * FROM tbl_role p Where name = '" + name + "'";
		
		List<Role> roles = super.executeNativeSql(sql);
		
		if(roles.size() > 0) {
			return roles.get(0);
		}
		else {
			return new Role();
		}
	}
}
