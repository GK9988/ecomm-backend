package org.garvk.payment_service.controller;

import jakarta.ws.rs.Path;
import org.garvk.payment_service.model.PaymentCredDTO;
import org.garvk.payment_service.model.PaymentCredentials;
import org.garvk.payment_service.service.PaymentCredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cred")
public class PaymentCredController {

    private PaymentCredService paymentCredService;

    @Autowired
    public PaymentCredController(PaymentCredService aInPaymentCredService){
        this.paymentCredService = aInPaymentCredService;
    }

    @PostMapping("/")
    public ResponseEntity<PaymentCredentials> addNewPaymentCredentials(
            @RequestBody PaymentCredentials aInPaymentCreds){
        return new ResponseEntity<>(
                paymentCredService.addCredentials(aInPaymentCreds), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCredentials> getPaymentCredentials(@PathVariable("id") Long aInId){
        return new ResponseEntity<>(
                paymentCredService.getPaymentCreds(aInId), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<PaymentCredentials> updatePaymentCredentials(
            @RequestBody PaymentCredentials aInPaymentCreds){
        return new ResponseEntity<>(
                paymentCredService.updatePaymentCreds(aInPaymentCreds), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePaymentCredentials(@PathVariable("id") Long aInId){
        paymentCredService.deletePaymentCreds(aInId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validatePaymentCredentials(@RequestBody PaymentCredDTO aInPaymentCredDTO){
        return new ResponseEntity<>(paymentCredService.validatePaymentCred(aInPaymentCredDTO), HttpStatus.OK);
    }

}
