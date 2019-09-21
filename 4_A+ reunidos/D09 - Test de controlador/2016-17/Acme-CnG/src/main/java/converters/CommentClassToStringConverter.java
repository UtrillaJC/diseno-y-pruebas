
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CommentClass;

@Component
@Transactional
public class CommentClassToStringConverter implements Converter<CommentClass, String> {

	@Override
	public String convert(final CommentClass commentClass) {
		String result;

		if (commentClass == null)
			result = null;
		else
			result = String.valueOf(commentClass.getId());

		return result;
	}
}
