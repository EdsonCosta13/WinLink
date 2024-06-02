package com.edsoncosta.WinLink.generics.dto;


/******************************
 * Created by: Edson Costa
 * Date: 02/06/2024
 * Time: 15:44
 ******************************/

public abstract class AbstractRequestDTO<E>{
    public abstract E mapToEntity();
}
