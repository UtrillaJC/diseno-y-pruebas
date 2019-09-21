package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ValueAttribute;

@Component
@Transactional
public class ValueAttributeToStringConverter implements Converter<ValueAttribute, String>{

	@Override
	public String convert(ValueAttribute valueAttribute) {
		String result;

		if (valueAttribute == null)
			result = null;
		else
			result = String.valueOf(valueAttribute.getId());

		return result;
	}
}
