package com.edsoncosta.WinLink.payment;

import com.edsoncosta.WinLink.generics.GenericId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Entity
@Data
public class Payment extends GenericId {

    private double value;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
