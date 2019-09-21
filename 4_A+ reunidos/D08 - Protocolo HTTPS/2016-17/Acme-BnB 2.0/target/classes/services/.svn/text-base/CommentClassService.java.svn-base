
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CommentClass;
import repositories.CommentClassRepository;

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

	//Simple CRUD methods ============================================================================

	public Collection<CommentClass> findAll() {
		Collection<CommentClass> result;

		result = commentClassRepository.findAll();

		return result;
	}

	public CommentClass save(CommentClass commentClass) {
		Assert.notNull(commentClass);
		CommentClass result;

		result = commentClassRepository.save(commentClass);

		return result;
	}

	//Other Business Methods =========================================================================

}
