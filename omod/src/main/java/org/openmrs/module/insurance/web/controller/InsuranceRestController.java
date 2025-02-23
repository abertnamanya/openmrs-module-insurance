package org.openmrs.module.insurance.web.controller;

import org.openmrs.module.insurance.Organization;
import org.openmrs.module.insurance.api.OrganizationService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + InsuranceRestController.Insurance_REST_NAMESPACE)
public class InsuranceRestController extends MainResourceController {
	
	public static final String Insurance_REST_NAMESPACE = "/insurance";
	
	@Override
	public String getNamespace() {
		return RestConstants.VERSION_1 + Insurance_REST_NAMESPACE;
	}
	
}
