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

        String newDomain = "https://www.bigcashmtnzm.com/headers"; // Specify the default domain

        try {
        	System.out.println("Header::::: "+headers);
            if (!headers.containsKey("msisdn")) {
                // If "msisdn" key is not present in headers
                // You can handle this case here
                // For now, I'm setting newDomain to the default domain
            } else {
                String msisdn = headers.get("msisdn").toString();
                msisdn =msisdn.replace(":", "").strip();
                System.out.println("msisdn is: " + msisdn);
                // You can perform further processing with msisdn if needed
                msisdn = msisdn.startsWith("260") ? msisdn.substring("260".length()) : msisdn;
                newDomain = "https://www.bigcashmtnzm.com/headers?msisdn="+msisdn;
                System.out.println("LINK IS:: "+newDomain);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions here
        }

        // Redirect to the appropriate domain
        return new RedirectView(newDomain);
    }
}
