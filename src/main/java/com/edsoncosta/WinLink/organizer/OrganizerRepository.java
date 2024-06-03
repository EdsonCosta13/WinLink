package com.edsoncosta.WinLink.organizer;

import com.edsoncosta.WinLink.organizer.dto.OrganizerResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {

    Optional<OrganizerResponse> findByName(String name);
    Optional<OrganizerResponse> findByNif(String nif);
    Optional<OrganizerResponse> findByPhone(String phone);
    Optional<OrganizerResponse> findByEmail(String email);
}
