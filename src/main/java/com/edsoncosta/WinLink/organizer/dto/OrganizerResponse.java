package com.edsoncosta.WinLink.organizer.dto;

import com.edsoncosta.WinLink.organizer.OrganizerType;

import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 02/05/2024
 * Time: 23:55 PM
 ******************************/

public record OrganizerResponse(

        UUID id,
        String name,
        String nif,
        String phone,
        String email,
        OrganizerType organizerType
) {
}
