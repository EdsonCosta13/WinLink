package com.edsoncosta.WinLink.generics;

import com.edsoncosta.WinLink.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GenericEntity {

    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    @PreUpdate
    private void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }
}
