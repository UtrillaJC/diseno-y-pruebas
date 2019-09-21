package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ValueAttributeRepository;
import domain.ValueAttribute;

@Component
@Transactional
public class StringToValueAttributeConverter implements Converter<String, ValueAttribute>{
	@Autowired ValueAttributeRepository valueAttributeRepository;
	@Override public ValueAttribute convert(String text) {
		ValueAttribute result;
		int id;

		try {
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = valueAttributeRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
