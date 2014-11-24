package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Census;

@Component
@Transactional
public class CensusToStringConverter implements Converter<Census, String>{
	
	@Override
	public String convert(Census census) {
		String result;

		if (census == null)
			result = null;
		else
			result = String.valueOf(census.getId());

		return result;
	}

}
