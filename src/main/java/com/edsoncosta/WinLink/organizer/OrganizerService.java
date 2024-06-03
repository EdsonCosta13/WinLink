package com.edsoncosta.WinLink.organizer;

import com.edsoncosta.WinLink.generics.AbstractService;
import com.edsoncosta.WinLink.generics.AbstractServiceImpl;
import com.edsoncosta.WinLink.organizer.dto.OrganizerRequest;
import com.edsoncosta.WinLink.organizer.dto.OrganizerResponse;
import com.edsoncosta.WinLink.utils.exception.generic.GenericException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


/******************************
 * Created by: Edson Costa
 * Date: 03/05/2024
 * Time: 00:09 AM
 ******************************/

@Service
public class OrganizerService extends AbstractServiceImpl<Organizer, UUID, OrganizerResponse> implements AbstractService<Organizer, UUID, OrganizerResponse>{

    private final OrganizerRepository organizerRepository;

    @Autowired
    protected OrganizerService(JpaRepository<Organizer, UUID> repository, OrganizerRepository organizerRepository) {
        super(repository);
        this.organizerRepository = organizerRepository;
    }

    @Override
    protected OrganizerRepository getRepository() {
        return (OrganizerRepository)super.getRepository();
    }

//    @Override
//    protected OrganizerRepository getRepository() {
//        return this.organizerRepository;
//    }

    @Override
    public OrganizerResponse mapToResponse(Organizer organizer) {

        return new OrganizerResponse(
            organizer.getId(),organizer.getName(),organizer.getNif(),
                organizer.getPhone(), organizer.getEmail(), organizer.getOrganizerType()
        );
    }

    @Transactional
    @Override
    public OrganizerResponse save(OrganizerRequest organizer) {
        checkDuplicate(organizer);
        return super.save(organizer.mapToEntity());
    }

    public void checkDuplicate(OrganizerRequest organizer) {
        if (organizerRepository.findByNif(organizer.getNif()).isPresent()) {
            throw new GenericException("Organizer with this NIF already exists");
        }else if (organizerRepository.findByPhone(organizer.getPhone()).isPresent()) {
            throw new GenericException("Organizer with this phone number already exists");
       }else if (organizerRepository.findByEmail(organizer.getEmail()).isPresent()) {
           throw new GenericException("Organizer with this email already exists");
        }
    }
}
