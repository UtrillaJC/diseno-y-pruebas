package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Taste;

public class TasteToStringConverter implements Converter<Taste, String>{
	
	@Override
	public String convert(Taste taste) {
		String result;

		if (taste == null)
			result = null;
		else
			result = String.valueOf(taste.getId());

		return result;
	}
}
