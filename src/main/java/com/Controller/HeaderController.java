package com.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin("*")
public class HeaderController {

    @GetMapping("/headers")
    public RedirectView getRequest(@RequestHeader Map<String, String> headers) {

        String newDomain = "http://www.bigcashmtnzm.com/headers"; // Specify the default domain

        try {
            if (!headers.containsKey("msisdn")) {
                // If "msisdn" key is not present in headers
                // You can handle this case here
                // For now, I'm setting newDomain to the default domain
            } else {
                String msisdn = headers.get("msisdn").toString();
                System.out.println("msisdn is: " + msisdn);
                // You can perform further processing with msisdn if needed
                newDomain = "http://www.bigcashmtnzm.com/headers?msisdn="+msisdn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions here
        }

        // Redirect to the appropriate domain
        return new RedirectView(newDomain);
    }
}
