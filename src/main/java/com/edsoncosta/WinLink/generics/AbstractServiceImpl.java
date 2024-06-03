package com.edsoncosta.WinLink.generics;

import com.edsoncosta.WinLink.enums.Status;
import com.edsoncosta.WinLink.organizer.dto.OrganizerRequest;
import com.edsoncosta.WinLink.organizer.dto.OrganizerResponse;
import com.edsoncosta.WinLink.utils.exception.generic.GenericException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 02/05/2024
 * Time: 23:56 PM
 ******************************/

public abstract class AbstractServiceImpl<E,ID,RESPONSE_DTO> implements AbstractService<E,ID,RESPONSE_DTO> {

    private final JpaRepository<E,ID> repository;
    protected AbstractServiceImpl(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    protected JpaRepository<E,ID> getRepository(){
        return repository;
    }
    public abstract RESPONSE_DTO mapToResponse(E e);

    public List<RESPONSE_DTO> getAll(){
        return this.repository.findAll().stream().map(this::mapToResponse).toList();
    }

    public Optional<RESPONSE_DTO> getById(ID id){
        return this.repository.findById(id).map(this::mapToResponse);
    }

    @Transactional
    public RESPONSE_DTO save(E entity){
        if (entity instanceof GenericId) {
            GenericId genericEntity = (GenericId) entity;
            if (genericEntity.getId() == null) {
                genericEntity.setId(UUID.randomUUID());
                genericEntity.setCreatedAt(LocalDateTime.now());
                genericEntity.setStatus(Status.CREATED);
            }
            genericEntity.setUpdatedAt(LocalDateTime.now());
        }

        try {
            E savedEntity = this.repository.save(entity);
            return mapToResponse(savedEntity);
        } catch (DuplicateKeyException e) {
            throw new GenericException("Campo duplicado!"+e);
        } catch (Exception e) {
            throw new GenericException("Erro ao salvar o registro!"+e);
        }
    }

    @Transactional
    public RESPONSE_DTO update(E entity, ID id){
        try {
            if (this.repository.existsById(id)) {
                E updatedEntity = this.repository.save(entity);
                return mapToResponse(updatedEntity);
            } else {
                throw new GenericException("Registro não encontrado!");
            }
        } catch (DuplicateKeyException e) {
            throw new GenericException("Campo duplicado!");
        } catch (Exception e) {
            throw new GenericException("Erro ao actualizar o registro!");
        }
    }

    public void delete(ID id)
    {
        try{

            if(this.repository.existsById(id)){
                this.repository.deleteById(id);
            }else{
                throw new GenericException("Não foi possível encontrar o registro com ID "+id);
            }

        }catch(Exception ex){
            throw new GenericException("Erro ao excluir o registro!"+ex);
        }
    }

    @Transactional
    public abstract OrganizerResponse save(OrganizerRequest organizer);
}
