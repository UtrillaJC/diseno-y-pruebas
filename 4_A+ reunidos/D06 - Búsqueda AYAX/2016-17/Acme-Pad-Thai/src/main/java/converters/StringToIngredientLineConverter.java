package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.IngredientLineRepository;
import domain.IngredientLine;

@Component
@Transactional
public class StringToIngredientLineConverter implements Converter<String, IngredientLine>{
	@Autowired IngredientLineRepository ingredientLineRepository;
	@Override public IngredientLine convert(String text) {
		IngredientLine result;
		int id;

		try {
			if(StringUtils.isEmpty(text))
				result = null;
			else{
				id = Integer.valueOf(text);
				result = ingredientLineRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
