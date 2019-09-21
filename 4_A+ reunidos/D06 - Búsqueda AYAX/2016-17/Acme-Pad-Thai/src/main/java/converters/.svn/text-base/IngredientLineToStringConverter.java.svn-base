package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.IngredientLine;

@Component
@Transactional
public class IngredientLineToStringConverter implements Converter<IngredientLine, String>{

	@Override
	public String convert(IngredientLine ingredientLine) {
		String result;

		if (ingredientLine == null)
			result = null;
		else
			result = String.valueOf(ingredientLine.getId());

		return result;
	}
}
