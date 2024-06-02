package com.edsoncosta.WinLink.generics.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 02/06/2024
 * Time: 15:46
 ******************************/

@Data
public abstract class AbstractResponseDTO<E>{

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateddAt;
    public abstract E toEntity(AbstractResponseDTO<E> dto);
    public abstract AbstractResponseDTO<E> toResponse(E entity);


}
