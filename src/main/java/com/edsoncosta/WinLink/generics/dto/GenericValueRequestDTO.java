package com.edsoncosta.WinLink.generics.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 02/06/2024
 * Time: 15:41
 ******************************/

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class GenericValueRequestDTO<E> extends AbstractRequestDTO<E>{
    private UUID id;
    private String name;
    private String description;
}
