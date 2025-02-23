/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.insurance.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.insurance.Organization;
import org.openmrs.module.insurance.api.OrganizationService;
import org.openmrs.module.insurance.api.dao.OrganizationDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrganizationServiceImpl extends BaseOpenmrsService implements OrganizationService {
	
	OrganizationDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(OrganizationDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Organization getOrganizationByUuid(String uuid) throws APIException {
		return dao.getOrganizationByUuid(uuid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Organization> getAllOrganizations() throws APIException {
		return dao.getAllOrganizations();
	}
	
	@Override
	public Organization saveOrganization(Organization organization) throws APIException {
		dao.saveOrganization(organization);
		return organization;
	}
	
	@Override
	public List<Organization> getOrganizationFilter(String query) throws APIException {
		return dao.getOrganizationFilter(query);
	}
	
	@Override
	public List<Organization> getAllOrganizationNoneRetired() throws APIException {
		return dao.getAllOrganizationNoneRetired();
	}
	
}
