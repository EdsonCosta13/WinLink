package com.edsoncosta.WinLink.raffle;

import com.edsoncosta.WinLink.generics.GenericEntity;
import com.edsoncosta.WinLink.organizer.Organizer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Raffle extends GenericEntity {

    private String title;
    private String description;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private double participationValue;
    private double award;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

}
