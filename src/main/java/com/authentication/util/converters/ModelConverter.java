package com.authentication.util.converters;

import org.modelmapper.ModelMapper;

public class ModelConverter {

	private static ModelMapper mapper = new ModelMapper();

	public static <O, D> D convertObject(O source, Class<D> destination) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		return mapper.map(source, destination);
	}

}
