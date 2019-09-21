package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PersonRepository;
import domain.Person;

@Component
@Transactional
public class StringToPersonConverter implements Converter<String, Person>{
	@Autowired PersonRepository personRepository;
	@Override public Person convert(String text) {
		Person result;
		int id;

		try {
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = personRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
