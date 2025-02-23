package org.openmrs.module.insurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.insurance.api.OrganizationService;
import org.openmrs.module.insurance.web.resource.OrganizationController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationControllerTest extends BaseDelegatingResourceTest<OrganizationController, Organization> {
	
	@Autowired
	OrganizationService organizationService;
	
	@Before
	public void before() {
		executeDataSet("organizationTestDataSet.xml");
	}
	
	@Override
	public Organization newObject() {
		return organizationService.getOrganizationByUuid(getUuidProperty());
	}
	
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertPropEquals("retired", getObject().getRetired());
	}
	
	@Override
	public void asRepresentation_shouldReturnValidFullRepresentation() throws Exception {
		super.asRepresentation_shouldReturnValidFullRepresentation();
		
		assertPropEquals("retired", getObject().getRetired());
	}
	
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertPropPresent("uuid");
		assertPropPresent("name");
		assertPropPresent("description");
		assertPropEquals("retired", getObject().getRetired());
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#validateFullRepresentation()
	 */
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest#getDisplayProperty()
	 */
	@Override
	public String getDisplayProperty() {
		return "test org";
	}
	
	@Override
	public String getUuidProperty() {
		return "c75074ec-06d9-435b-a195-a02ed83d891d";
	}
	
	@Test
	public void doGetAll_shouldReturnAllOrganizationsIncludingRetiredIfContextincludeAllIsSet() throws Exception {
		OrganizationController or = getResource();
		
		RequestContext ctx = new RequestContext();
		
		List orderList = or.getAll(ctx).get("results");
		
		Assert.assertEquals("getAll should return all not voided organizations from sample data", 1, orderList.size());
		
		orderList = or.getAll(ctx).get("results");
		Assert.assertEquals("getAll should return all not retired organizations from sample data", 1, orderList.size());
		
		ctx.setIncludeAll(true);
		
		orderList = or.getAll(ctx).get("results");
		Assert.assertEquals("getAll should return all organizations from sample data", 1, orderList.size());
		
	}
}
