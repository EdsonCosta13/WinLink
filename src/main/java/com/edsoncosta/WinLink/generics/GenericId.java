package com.edsoncosta.WinLink.generics;

import com.edsoncosta.WinLink.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    @PreUpdate
    private void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    public GenericId(UUID id, Status status) {
        this.id = id;
        this.status = status;
    }

    public GenericId(UUID id) {
        this.id = id;
    }
    private void onCreate(){
        this.id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
        if (this.status == null){
            this.status = Status.ACTIVE;
        }
    }
}
