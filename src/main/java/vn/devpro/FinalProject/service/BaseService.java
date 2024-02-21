package vn.devpro.FinalProject.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.BaseModel;

@Service
public abstract class BaseService<E extends BaseModel> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public abstract Class<E> clazz();
	
	// lay 1 ban ghi theo id
	public E getById(int id) {
		return entityManager.find(clazz(), id);
	}
	
	// lay tat ca cac ban ghi trong 1 table
	@SuppressWarnings("unchecked") // bỏ cảnh báo của database là có hay ko có
	public List<E> findAll() {
		Table table = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + table.name(),
				clazz()).getResultList();
	}
	
	// Them moi hoac sua mot ban ghi
	@Transactional
	public E saveOrUpdate (E entity) {
		if(entity.getId() == null || entity.getId() <= 0) { // Add new entity
			entityManager.persist(entity);
			return entity;
		} else { // update entity
			return entityManager.merge(entity);
		}
	}
	
	// Xoa 1 ban ghi theo entity
	public void delete(E entity) {
		entityManager.remove(entity);
	}
	
	// Delete theo id
	public void deleteById(int id) {
		E entity = this.getById(id);
		delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//search and paging
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql, int currentPage, int sizeOfPage) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			query.setFirstResult((currentPage - 1) * sizeOfPage); // Ban ghi dau trang
			query.setMaxResults(sizeOfPage); // So ban ghi tren 1 trang
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<E>();
		}
	}
	
	// Get entity
	public E getEntityByNativeSQL(String sql) {
		List<E> list = executeNativeSql(sql);
		if(list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
