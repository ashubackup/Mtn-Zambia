package com.Response;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor@NoArgsConstructor
@XmlRootElement(name = "parameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter {
	 @XmlElement(name = "param")
	    private Map<String, Param> params = new HashMap<>();
}
