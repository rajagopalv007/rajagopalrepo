package com.example.demo.domain;

import javax.persistence.AttributeConverter;

public class BooleanToStringConvertor  implements AttributeConverter<Boolean, String>{

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		// TODO Auto-generated method stub
		return attribute?"T":"F";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return dbData.equals("T")?new Boolean(true):new Boolean(false);
	}

}
