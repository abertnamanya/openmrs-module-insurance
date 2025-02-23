package org.openmrs.module.insurance;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.OpenmrsMetadata;

import javax.persistence.*;

@Entity(name = "organization")
@Table(name = "organization")
public class Organization extends BaseOpenmrsMetadata {
	
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "organization_id")
	private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}
	
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	@Override
	public Integer getId() {
		return getOrganizationId();
	}
	
	@Override
	public void setId(Integer id) {
		setOrganizationId(id);
	}
	
}
