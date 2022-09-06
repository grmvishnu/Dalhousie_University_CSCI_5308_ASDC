package com.group18.controller;
import com.group18.entity.dto.ChargeRequest;
import com.group18.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public void charge(HttpServletResponse httpServletResponse)
            throws StripeException {
        httpServletResponse.setHeader("Location", "https://main--graceful-axolotl-b72d1c.netlify.app/");
        httpServletResponse.setStatus(302);
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
