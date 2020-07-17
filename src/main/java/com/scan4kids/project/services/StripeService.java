package com.scan4kids.project.services;


import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    public StripeService() {
    }
//
//    public String createCustomer(String email, String token) {
//        String id = null;
//        try {
//            Stripe.apiKey = API_SECRET_KEY;
//            Map<String, Object> customerParams = new HashMap<>();
//            // add customer unique id here to track them in your web application
//            customerParams.put("description", "Customer for " + email);
//            customerParams.put("email", email);
//
//            customerParams.put("source", token); // ^ obtained with Stripe.js
//            //create a new customer
//            Customer customer = Customer.create(customerParams);
//            id = customer.getId();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return id;
//    }

    public String createCharge(String email, String token, int amount) {
        String id = null;
        try {
            Stripe.apiKey = API_SECRET_KEY;
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Charge for " + email);
            chargeParams.put("source", token); // ^ obtained with Stripe.js

            //create a charge
            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}