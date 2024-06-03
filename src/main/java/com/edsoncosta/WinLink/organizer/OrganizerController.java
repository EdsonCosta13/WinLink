package com.edsoncosta.WinLink.organizer;


import com.edsoncosta.WinLink.generics.AbstractController;
import com.edsoncosta.WinLink.generics.AbstractService;
import com.edsoncosta.WinLink.organizer.dto.OrganizerRequest;
import com.edsoncosta.WinLink.organizer.dto.OrganizerResponse;
import com.edsoncosta.WinLink.utils.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/******************************
 * Created by: Edson Costa
 * Date: 03/05/2024
 * Time: 00:12 AM
 ******************************/

@RestController
@RequestMapping("/organizer")
public class OrganizerController extends AbstractController<Organizer, UUID, OrganizerRequest, OrganizerResponse> {

//    @Autowired
//    protected OrganizerController(AbstractService<Organizer, UUID, OrganizerResponse> service) {
//        super(service);
//    }
//
//    @Override
//    public OrganizerService getService() {
//        return (OrganizerService)super.getService();
//    }

    private final OrganizerService organizerService;

    @Autowired
    protected OrganizerController(OrganizerService organizerService) {
        super(organizerService);
        this.organizerService = organizerService;
    }

    @Override
    protected OrganizerService getService() {
        return this.organizerService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response<OrganizerResponse>> create(@RequestBody OrganizerRequest dto) {
//        this.getService().save(dto.mapToEntity());
//        return this.created("Organizer created successfully").toResponseEntity();

        this.getService().checkDuplicate(dto);

        OrganizerResponse responseDto = this.getService().save(dto.mapToEntity());
        return new Response<OrganizerResponse>()
                .withData(responseDto)
                .created("Organizer created successfully")
                .toResponseEntity();
    }

    @GetMapping
    @Override
    public List<OrganizerResponse> getAll() {
        return getService().getAll();
    }

    @Override
    public ResponseEntity<Response<OrganizerResponse>> update(UUID uuid, OrganizerRequest dto) {
        return super.update(uuid, dto);
    }

    @Override
    public ResponseEntity<Response<Void>> delete(UUID uuid) {
        return super.delete(uuid);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Response<OrganizerResponse>> getById(@PathVariable(value = "id") UUID id) {
//        return getService().getById(id).map(responseDtoList->this.success("Organizer").toResponseEntity());

//        return getService().getById(id).map(response->this.success("Registro encontrado")).get().toResponseEntity();

        return this.getService().getById(id)
                .map(response -> new Response<OrganizerResponse>()
                        .withData(response)
                        .success("Registro encontrado")
                        .toResponseEntity())
                .orElseGet(() -> new Response<OrganizerResponse>()
                        .notFound("Error, entity not found")
                        .toResponseEntity());
    }

}
