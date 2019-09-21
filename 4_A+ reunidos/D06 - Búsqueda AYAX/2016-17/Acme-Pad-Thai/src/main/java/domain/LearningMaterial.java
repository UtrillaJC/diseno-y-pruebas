package domain;

import java.net.URL;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public abstract class LearningMaterial extends DomainEntity{

	//Attributes ====================================================================================
	
	private String title;
	private String abstractText;	
	private Collection<URL> attachments;
	private String type;
	
	//Constructor ====================================================================================
	
	public LearningMaterial(){
		super();
	}
	
	//Getters & setters ====================================================================================

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	
	@ElementCollection
	public Collection<URL> getAttachments() {
		return attachments;
	}

	public void setAttachments(Collection<URL> attachments) {
		this.attachments = attachments;
	}
	
	@NotBlank
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	//Relationships ====================================================================================

	private MasterClass masterClass;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public MasterClass getMasterClass() {
		return masterClass;
	}

	public void setMasterClass(MasterClass masterClass) {
		this.masterClass = masterClass;
	}
}

