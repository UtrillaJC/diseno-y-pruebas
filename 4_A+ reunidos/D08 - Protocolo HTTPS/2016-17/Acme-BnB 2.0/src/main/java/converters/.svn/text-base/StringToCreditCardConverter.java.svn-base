
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;
import repositories.LessorRepository;

@Component
@Transactional
public class StringToCreditCardConverter implements Converter<String, CreditCard> {

	@Autowired
	private LessorRepository lessorRepository;


	@Override
	public CreditCard convert(String s) {

		CreditCard c;

		try {
			if (StringUtils.isEmpty(s)) {
				c = null;
			} else {
				c = lessorRepository.findCreditCardByNumber(s);
			}
		} catch (Throwable e) {

			throw new IllegalArgumentException(e);
		}

		return c;
	}
}
