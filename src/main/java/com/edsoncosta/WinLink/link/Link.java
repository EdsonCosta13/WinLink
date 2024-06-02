package com.edsoncosta.WinLink.link;

import com.edsoncosta.WinLink.generics.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Link extends GenericEntity {

    private String url;

    @Enumerated(EnumType.STRING)
    private LinkType linkType;

    private LocalDateTime expireAt;
    private boolean uniqueUse;
    private boolean used;
    private boolean validForMultiple;
}
