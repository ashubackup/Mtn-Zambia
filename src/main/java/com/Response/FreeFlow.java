package com.Response;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FreeFlow")

public class FreeFlow {

    @XmlElement(name = "freeflowState")
    private String freeflowState;

    @XmlElement(name = "freeflowCharging")
    private String freeflowCharging;

    @XmlElement(name = "freeflowChargingAmount")
    private double freeflowChargingAmount;

    //@XmlElementWrapper(name = "parameters")
    //@XmlElement(name = "param")
    private Parameter parameters;

}
