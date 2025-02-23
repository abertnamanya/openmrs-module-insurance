package org.openmrs.module.insurance.web.resource;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.*;
import org.apache.commons.lang3.StringUtils;
import org.openmrs.LocationTag;
import org.openmrs.VisitType;
import org.openmrs.api.context.Context;
import org.openmrs.module.insurance.Organization;
import org.openmrs.module.insurance.api.OrganizationService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.*;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openmrs.module.insurance.web.controller.InsuranceRestController.Insurance_REST_NAMESPACE;

@Resource(name = RestConstants.VERSION_1 + Insurance_REST_NAMESPACE + "/organization", supportedClass = Organization.class, supportedOpenmrsVersions = { "2.5.* - 9.*" })
public class OrganizationController extends MetadataDelegatingCrudResource<Organization> {
	
	OrganizationService organizationService = Context.getService(OrganizationService.class);
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("name");
		description.addProperty("description");
		
		description.addProperty("retired");
		description.addProperty("retireReason");
		return description;
	}
	
	@Override
	public Model getCREATEModel(Representation rep) {
		return ((ModelImpl) super.getCREATEModel(rep)).property("retired", new BooleanProperty()).property("retiredReason",
		    new StringProperty());
	}
	
	@Override
	public Organization getByUniqueId(String uuid) {
		return organizationService.getOrganizationByUuid(uuid);
	}
	
	@Override
	public Organization newDelegate() {
		return new Organization();
	}
	
	@Override
	public Organization save(Organization organization) {
		return organizationService.saveOrganization(organization);
	}
	
	@Override
	protected NeedsPaging<Organization> doGetAll(RequestContext context) {
		return new NeedsPaging<Organization>(organizationService.getAllOrganizationNoneRetired(), context);
	}
	
	@Override
	protected NeedsPaging<Organization> doSearch(RequestContext context) {
		return new NeedsPaging<Organization>(organizationService.getOrganizationFilter(context.getParameter("q")), context);
	}
	
	@Override
	public void purge(Organization organization, RequestContext requestContext) throws ResponseException {
		throw new UnsupportedOperationException("Cannot purge organization");
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		return null;
	}
	
	@PropertyGetter("name")
	@Override
	public String getDisplayString(Organization delegate) {
		return delegate.getName();
	}
}
