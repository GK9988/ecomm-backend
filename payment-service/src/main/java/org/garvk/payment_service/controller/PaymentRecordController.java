package org.garvk.payment_service.controller;

import org.garvk.payment_service.model.PaymentRecord;
import org.garvk.payment_service.service.PaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentRecordController {

    private PaymentRecordService paymentRecordService;

    @Autowired
    public PaymentRecordController(PaymentRecordService aInPaymentRecordService){
        this.paymentRecordService = aInPaymentRecordService;
    }

    @PostMapping("/")
    public ResponseEntity<PaymentRecord> createPaymentRecord(@RequestBody PaymentRecord aInPaymentRecord){
        return new ResponseEntity<>(
                paymentRecordService.createNewPayment(aInPaymentRecord), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentRecord> getPaymentRecordById(@PathVariable("id") Long aInId){
        return new ResponseEntity<>(paymentRecordService.getPaymentRecord(aInId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PaymentRecord> getPaymentRecordByOrderId(@RequestParam("orderId") Long aInOrderId){
        return new ResponseEntity<>(paymentRecordService.getPaymentRecordByOrderId(aInOrderId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePaymentRecord(@PathVariable("id") Long aInId){
        paymentRecordService.deletePaymentRecord(aInId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deletePaymentRecordByOrderId(@RequestParam("orderId") Long aInOrderId){
        paymentRecordService.deletePaymentRecordByOrderId(aInOrderId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
