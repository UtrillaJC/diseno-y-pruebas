
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.CommentClass;
import repositories.CommentClassRepository;

@Component
@Transactional
public class StringToCommentClassConverter implements Converter<String, CommentClass> {

	@Autowired
	CommentClassRepository commentClassRepository;


	@Override
	public CommentClass convert(final String text) {
		CommentClass result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.commentClassRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
