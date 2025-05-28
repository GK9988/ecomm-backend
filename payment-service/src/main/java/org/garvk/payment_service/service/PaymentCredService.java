package org.garvk.payment_service.service;

import org.garvk.payment_service.Exception.PaymentCredPresent;
import org.garvk.payment_service.Exception.PaymentCredsNotFound;
import org.garvk.payment_service.Exception.PaymentInvalidCred;
import org.garvk.payment_service.model.PaymentCredDTO;
import org.garvk.payment_service.model.PaymentCredentials;
import org.garvk.payment_service.repo.PaymentCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentCredService {

    private PaymentCredRepo paymentCredRepo;

    @Autowired
    public PaymentCredService(PaymentCredRepo aInPaymentCredRepo){
        this.paymentCredRepo = aInPaymentCredRepo;
    }

    public PaymentCredentials addCredentials(PaymentCredentials aInPaymentCreds) {

        Long lPayId = aInPaymentCreds.getPayId();
        if(paymentCredRepo.existsByPayId(lPayId)){
            throw new PaymentCredPresent();
        }

        return paymentCredRepo.save(aInPaymentCreds);

    }

    public PaymentCredentials getPaymentCreds(Long aInId){
        PaymentCredentials lPaymentCreds = paymentCredRepo.findById(aInId).orElse(null);
        if(null == lPaymentCreds) throw new PaymentCredsNotFound();
        return lPaymentCreds;
    }

    public PaymentCredentials updatePaymentCreds(PaymentCredentials aInNewPaymentCreds){
        PaymentCredentials aOldPaymentCreds = null;

        Long lId = aInNewPaymentCreds.getId();
        if(null != lId) aOldPaymentCreds = paymentCredRepo.findById(lId).orElse(null);

        Long lPayId = aInNewPaymentCreds.getPayId();
        if(null == aOldPaymentCreds && null != lPayId){
            aOldPaymentCreds = paymentCredRepo.findByPayId(lPayId).orElse(null);
        }

        if(null == aOldPaymentCreds) throw new PaymentCredsNotFound();

        Integer lPayPin = aInNewPaymentCreds.getPayPin();
        if(null != lPayPin) aOldPaymentCreds.setPayPin(lPayPin);

        return paymentCredRepo.save(aOldPaymentCreds);
    }

    public void deletePaymentCreds(Long aInId){
        PaymentCredentials lPaymentCreds = paymentCredRepo.findById(aInId).orElse(null);
        if(null == lPaymentCreds) throw new PaymentCredsNotFound();
        paymentCredRepo.delete(lPaymentCreds);
    }

    public Boolean validatePaymentCred(PaymentCredDTO aInPaymentCredDTO){
        Long aInPayId = aInPaymentCredDTO.getPayId();
        Integer aInPayPin = aInPaymentCredDTO.getPayPin();
        if(null == aInPayId || null == aInPayPin) throw new PaymentInvalidCred();

        PaymentCredentials lPaymentCred = paymentCredRepo.findByPayId(aInPayId).orElse(null);
        if(lPaymentCred == null) throw new PaymentInvalidCred();

        return aInPaymentCredDTO.getPayPin().equals(lPaymentCred.getPayPin());
    }
}
