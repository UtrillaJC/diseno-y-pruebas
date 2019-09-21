
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

	private Collection<Comment>	commentCreates;
	private Collection<Comment>	commentReceivers;


	@Valid
	@OneToMany(mappedBy = "author")
	public Collection<Comment> getCommentCreates() {
		return commentCreates;
	}

	public void setCommentCreates(Collection<Comment> commentCreates) {
		this.commentCreates = commentCreates;
	}

	@Valid
	@OneToMany(mappedBy = "receiver")
	public Collection<Comment> getCommentReceivers() {
		return commentReceivers;
	}

	public void setCommentReceivers(Collection<Comment> commentReceivers) {
		this.commentReceivers = commentReceivers;
	}

}
