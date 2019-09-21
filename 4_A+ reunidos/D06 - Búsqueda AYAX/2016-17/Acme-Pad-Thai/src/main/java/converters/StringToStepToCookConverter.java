package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.StepToCookRepository;
import domain.StepToCook;

@Component
@Transactional
public class StringToStepToCookConverter implements Converter<String, StepToCook>{
	@Autowired StepToCookRepository stepToCookRepository;
	@Override public StepToCook convert(String text) {
		StepToCook result;
		int id;

		try {
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = stepToCookRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
