/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.insurance.api.dao;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.insurance.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("insurance.OrganizationDao")
public class OrganizationDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Organization getOrganizationByUuid(String uuid) {
		return (Organization) getSession().createCriteria(Organization.class)
				.add(Restrictions.eq("uuid", uuid))
		        .uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganizations() throws DAOException {
		return sessionFactory.getCurrentSession().createQuery("from organization o order by o.id desc").list();
	}
	
	public List<Organization> getAllOrganizationNoneRetired() {
		Session session = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
		Root<Organization> root = cq.from(Organization.class);
		cq.orderBy(cb.asc(root.get("name")));
		return session.createQuery(cq).getResultList();
	}
	
	public void saveOrganization(Organization organization) throws APIException {
		sessionFactory.getCurrentSession().saveOrUpdate(organization);
	}
	
	public List<Organization> getOrganizationFilter(String searchQuery) {
		
		Session session = sessionFactory.getCurrentSession().getSessionFactory().getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
		Root<Organization> root = cq.from(Organization.class);
		
		cq.where(cb.like(cb.lower(root.<String> get("name")), MatchMode.START.toMatchString(searchQuery).toLowerCase()));
		cq.orderBy(cb.asc(root.get("name")));
		
		return session.createQuery(cq).getResultList();
	}
}
