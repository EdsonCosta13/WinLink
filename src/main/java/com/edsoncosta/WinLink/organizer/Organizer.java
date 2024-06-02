package com.edsoncosta.WinLink.organizer;

import com.edsoncosta.WinLink.generics.GenericEntity;
import com.edsoncosta.WinLink.raffle.Raffle;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Organizer extends GenericEntity {
    private String name;
    private String nif;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private OrganizerType organizerType;

    @OneToMany(mappedBy = "organizer")
    private List<Raffle> raffles;

}
