package com.edsoncosta.WinLink.payment;

import com.edsoncosta.WinLink.generics.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Entity
@Data
public class Payment extends GenericEntity {

    private double value;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
