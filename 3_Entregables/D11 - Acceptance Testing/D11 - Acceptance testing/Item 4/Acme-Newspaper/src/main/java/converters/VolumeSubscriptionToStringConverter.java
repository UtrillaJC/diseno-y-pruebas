
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.VolumeSubscription;

@Component
@Transactional
public class VolumeSubscriptionToStringConverter implements Converter<VolumeSubscription, String> {

	@Override
	public String convert(final VolumeSubscription volumeSubscription) {
		String result;

		if (volumeSubscription == null)
			result = null;
		else
			result = String.valueOf(volumeSubscription.getId());
		return result;
	}
}
