package vn.devpro.FinalProject.service;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.Contact;

@Service
public class ContactService extends BaseService<Contact>{
	@Override
	public Class<Contact> clazz() {
		return Contact.class;
	}
}
