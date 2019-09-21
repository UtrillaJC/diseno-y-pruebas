package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Text;

@Component
@Transactional
public class TextToStringConverter implements Converter<Text, String>{

	@Override
	public String convert(Text text) {
		String result;

		if (text == null)
			result = null;
		else
			result = String.valueOf(text.getId());

		return result;
	}
}
