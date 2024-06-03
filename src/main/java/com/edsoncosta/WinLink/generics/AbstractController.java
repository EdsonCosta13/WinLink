package com.edsoncosta.WinLink.generics;


import com.edsoncosta.WinLink.generics.dto.AbstractRequestDTO;
import com.edsoncosta.WinLink.organizer.OrganizerService;
import com.edsoncosta.WinLink.utils.exception.generic.EntityNotFoundException;
import com.edsoncosta.WinLink.utils.http.Response;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******************************
 * Created by: Edson Costa
 * Date: 02/05/2024
 * Time: 15:50 PM
 ******************************/

@Setter
public abstract class AbstractController<E, ID, REQUEST_DTO extends AbstractRequestDTO<E>, RESPONSE_DTO> extends Response<RESPONSE_DTO> {

    private final AbstractService<E, ID, RESPONSE_DTO> service;

    protected AbstractController(AbstractService<E, ID, RESPONSE_DTO> service) {
        this.service = service;
    }

    protected AbstractService<E, ID, RESPONSE_DTO> getService() {
        return this.service;
    }

    @GetMapping
    public List<RESPONSE_DTO> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<RESPONSE_DTO>> getById(@PathVariable ID id) {
        return this.service.getById(id)
                .map(responseDto -> new Response<RESPONSE_DTO>()
                        .withData(responseDto)
                        .success("Entity found")
                        .toResponseEntity())
                .orElseGet(() -> new Response<RESPONSE_DTO>()
                        .notFound("Error, entity not found")
                        .toResponseEntity());
    }

    @PostMapping
    public ResponseEntity<Response<RESPONSE_DTO>> create(@RequestBody REQUEST_DTO dto) {
        E entity = dto.mapToEntity();
        RESPONSE_DTO savedDto = this.service.save(entity);
        Response<RESPONSE_DTO> response = new Response<RESPONSE_DTO>()
                .withData(savedDto)
                .created("Entidade criada!");

        return response.toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<RESPONSE_DTO>> update(@PathVariable ID id, @RequestBody REQUEST_DTO dto) {
        E entity = dto.mapToEntity();

        try {
            RESPONSE_DTO updatedDto = this.service.update(entity, id);
            return new Response<RESPONSE_DTO>()
                    .withData(updatedDto)
                    .success("Entidade atualizada!")
                    .toResponseEntity();
        } catch (EntityNotFoundException e) {
            return new Response<RESPONSE_DTO>()
                    .notFound("Entidade não encontrada!")
                    .toResponseEntity();
        } catch (Exception e) {
            return new Response<RESPONSE_DTO>()
                    .error("Erro ao actualizar a entidade!")
                    .toResponseEntity();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable ID id) {
        try {
            this.service.delete(id);
            return new Response<Void>()
                    .success("Entidade eliminada!")
                    .toResponseEntity();
        } catch (EntityNotFoundException e) {
            return new Response<Void>()
                    .notFound("Entidade não encontrada!")
                    .toResponseEntity();
        } catch (Exception e) {
            return new Response<Void>()
                    .error("Erro ao eliminar a entidade!")
                    .toResponseEntity();
        }
    }
}

