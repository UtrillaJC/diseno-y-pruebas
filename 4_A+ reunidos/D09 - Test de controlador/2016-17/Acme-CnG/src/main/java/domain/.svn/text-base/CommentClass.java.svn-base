
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public abstract class CommentClass extends DomainEntity {

	//Attributes=====================================================================================

	//Constructors====================================================================================

	public CommentClass() {
		super();
	}


	// Relationships ====================================================================================

	private Collection<Comment>	receivedComments;


	@Valid
	@OneToMany
	public Collection<Comment> getReceivedComments() {
		return this.receivedComments;
	}

	public void setReceivedComments(Collection<Comment> receivedComments) {
		this.receivedComments = receivedComments;
	}
}
