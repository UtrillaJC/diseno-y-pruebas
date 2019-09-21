package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StepToCook;

@Component
@Transactional
public class StepToCookToStringConverter implements Converter<StepToCook, String>{

	@Override
	public String convert(StepToCook stepToCook) {
		String result;

		if (stepToCook == null)
			result = null;
		else
			result = String.valueOf(stepToCook.getId());

		return result;
	}
}
