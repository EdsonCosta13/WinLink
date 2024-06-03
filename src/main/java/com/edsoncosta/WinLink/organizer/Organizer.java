package com.edsoncosta.WinLink.organizer;

import com.edsoncosta.WinLink.generics.GenericId;
import com.edsoncosta.WinLink.raffle.Raffle;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizer extends GenericId {
    private String name;
    private String nif;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private OrganizerType organizerType;

    @OneToMany(mappedBy = "organizer")
    private List<Raffle> raffles;

    public Organizer(UUID id) {
        super(id);
    }

}
