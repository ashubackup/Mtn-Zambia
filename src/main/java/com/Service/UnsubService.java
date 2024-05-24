package com.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Api.UnsubApi;
import com.Entity.TblSubscription;
import com.Entity.TblUnsubsciption;
import com.Repository.TblSubRepo;
import com.Repository.UnsubRepo;

@Service
public class UnsubService {

    @Autowired
    TblSubRepo subRepo;

    @Autowired
    UnsubApi unsubApi;

    @Autowired
    UnsubRepo unsubRepo;
    
//    @Autowired
//    SmsService smsService;

    public String getRequestUnsub(String msisdn) {
        String response = "Failed";
        try {
            final String[] atomicResponse = {response}; // Using one-element array as mutable object
            List<TblSubscription> subscription = subRepo.findByAni(msisdn);
            if (!subscription.isEmpty()) {
                System.out.println("Inside if statement");
                subscription.forEach(tblsubscription -> {
                    String apiresponse = unsubApi.hitUnsubApi(msisdn, tblsubscription.getPack());
                    System.out.println("apiResponse" + apiresponse);
                    if (apiresponse.equalsIgnoreCase("Success")) {
                        TblUnsubsciption tblUnsubsciption = new TblUnsubsciption();
                        tblUnsubsciption.setAmount(tblsubscription.getAmount());
                        tblUnsubsciption.setAni(msisdn);
                        tblUnsubsciption.setM_act(tblsubscription.getMode());
                        tblUnsubsciption.setPack(tblsubscription.getPack());
                        tblUnsubsciption.setM_deact("WEB");
                        tblUnsubsciption.setProcessDateTime(LocalDateTime.now());
                        tblUnsubsciption.setService("MTNZAMBIA");
                        tblUnsubsciption.setStatus("1");
                        tblUnsubsciption.setSubDateTime(tblsubscription.getSub_date_time());
                        tblUnsubsciption.setUnsubDateTime(LocalDateTime.now());
                        unsubRepo.save(tblUnsubsciption);
                        subRepo.delete(tblsubscription);
                        //smsService.getRequest(msisdn);
                    }
                    atomicResponse[0] = apiresponse;
                });
                response = atomicResponse[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
