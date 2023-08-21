package com.idquantique.quantis.service;

import com.idquantique.quantis.Quantis;
import com.idquantique.quantis.QuantisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired

    public String generateOTP() throws QuantisException {
        Quantis quantis = new Quantis(Quantis.QuantisDeviceType.QUANTIS_DEVICE_PCI, 0);
        Integer positive_rand = Math.abs(quantis.ReadInt());
        return String.format("%06d", positive_rand);
    }

    public void sendOTP() throws QuantisException{
        Quantis quantis = new Quantis(Quantis.QuantisDeviceType.QUANTIS_DEVICE_PCI, 0);
        Integer positive_rand = Math.abs(quantis.ReadInt());
    }


}
