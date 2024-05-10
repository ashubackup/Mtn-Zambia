package com.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="Param")
@XmlAccessorType(XmlAccessType.FIELD)

public class Param {

	
	@XmlAttribute
	private String name;
	
	@XmlValue
    private String value;

//	@XmlValue
//	private String value;

}