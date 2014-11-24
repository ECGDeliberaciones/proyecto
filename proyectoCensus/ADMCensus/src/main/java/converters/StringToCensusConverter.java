package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Census;

import repositories.CensusRepository;

@Component
@Transactional
public class StringToCensusConverter implements Converter<String,Census>{
	
	@Autowired
	CensusRepository censusRepository;
	
	@Override
	public Census convert(String text) {
		Census result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = censusRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
