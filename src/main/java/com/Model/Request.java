package com.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
    private String cellId;
    private String dateFormat;
    private String gsmMapUserSpecVal;
    private String hlr;
    private String imsi;
    private String lac;
    private String language;
    private String mcc;
    private String mnc;
    private String msc;
    private String msisdn;
    private String nodeIdentifier;
    private Parameters parameters;
    private String region;
    private FreeFlow freeflow;
    private String sessionId;
    private String transactionId;

    // Getters and setters
}

