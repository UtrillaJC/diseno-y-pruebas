
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentClassRepository;
import domain.CommentClass;

@Service
@Transactional
public class CommentClassService {

	//Managed Repository =============================================================================

	@Autowired
	private CommentClassRepository commentClassRepository;

	//Supported Services =============================================================================


	//Constructor methods ============================================================================

	public CommentClassService() {
		super();
	}

	public CommentClass findOne(final int commentClassId) {
		CommentClass result;

		result = this.commentClassRepository.findOne(commentClassId);
		Assert.notNull(result);

		return result;
	}

//	public CommentClass findByComment(Comment comment) {
//		CommentClass result;
//		result = this.commentClassRepository.findByComment(comment);
//		Assert.notNull(result);
//		
//		return result;
//	}
	public CommentClass save(CommentClass commentClass) {
		Assert.notNull(commentClass);
		CommentClass result;

		result = this.commentClassRepository.save(commentClass);
		return result;
	}

}
