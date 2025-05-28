package org.garvk.payment_service.service;

import org.garvk.payment_service.Exception.PaymentAlreadyDone;
import org.garvk.payment_service.Exception.PaymentNotFound;
import org.garvk.payment_service.model.PaymentRecord;
import org.garvk.payment_service.repo.PaymentRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRecordService {

    private PaymentRecordRepo paymentRecordRepo;

    @Autowired
    public PaymentRecordService(PaymentRecordRepo aInPaymentRecordRepo){
        this.paymentRecordRepo = aInPaymentRecordRepo;
    }

    public PaymentRecord createNewPayment(PaymentRecord aInPaymentRecord){
        Long lOrderId = aInPaymentRecord.getOrderId();
        PaymentRecord lPaymentRecord = paymentRecordRepo.findByOrderId(lOrderId).orElse(null);
        if(null != lPaymentRecord) throw new PaymentAlreadyDone();

        return paymentRecordRepo.save(aInPaymentRecord);
    }

    public PaymentRecord getPaymentRecordByOrderId(Long aInOrderId){
        PaymentRecord aOutPaymentRecord = paymentRecordRepo.findByOrderId(aInOrderId).orElse(null);
        if(null == aOutPaymentRecord) throw new PaymentNotFound();
        return aOutPaymentRecord;
    }

    public PaymentRecord getPaymentRecord(Long aInId){
        PaymentRecord aOutPaymentRecord = paymentRecordRepo.findById(aInId).orElse(null);
        if(null == aOutPaymentRecord) throw new PaymentNotFound();
        return aOutPaymentRecord;
    }

    public void deletePaymentRecord(Long aInId){
        PaymentRecord lPaymentRecord = paymentRecordRepo.findById(aInId).orElse(null);
        if(null == lPaymentRecord) throw new PaymentNotFound();
        paymentRecordRepo.delete(lPaymentRecord);
    }

    public void deletePaymentRecordByOrderId(Long aInOrderId){
        PaymentRecord lPaymentRecord = paymentRecordRepo.findByOrderId(aInOrderId).orElse(null);
        if(null == lPaymentRecord) throw new PaymentNotFound();
        paymentRecordRepo.delete(lPaymentRecord);
    }

}
