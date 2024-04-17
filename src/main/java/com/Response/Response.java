package com.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

	private String msisdn;

    @XmlElement(name = "applicationResponse")
    private String applicationResponse;

    @XmlElement(name = "appDrivenMenuCode")
    private String appDrivenMenuCode;

    @XmlElement(name = "freeflow")
    private FreeFlow freeflow;
}
