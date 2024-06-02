package com.edsoncosta.WinLink.generics;

import java.util.List;
import java.util.Optional;

public interface AbstractService<E,ID,RESPONSE_DTO>{
    RESPONSE_DTO mapToResponse(E e);
    List<RESPONSE_DTO> getAll();
    Optional<RESPONSE_DTO> getById(ID id);
    RESPONSE_DTO save(E e);
    RESPONSE_DTO update(E e,ID id);
    void delete(ID id);

}
