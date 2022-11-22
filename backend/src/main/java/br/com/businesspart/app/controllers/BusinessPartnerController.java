package br.com.businesspart.app.controllers;

import br.com.businesspart.app.dtos.response.BusinessPartnerDTOResponse;
import br.com.businesspart.app.dtos.request.BusinessPartnerDTORequest;
import br.com.businesspart.app.services.BusinessPartnerService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/business-partner")
public class BusinessPartnerController {

    private BusinessPartnerService service;

    public BusinessPartnerController(BusinessPartnerService service) {
        this.service = service;
    }

    /*Excluir esse endpoint /consume antes de finalizar projeto. É usado nesse momento apenas para testes.
      Excluir essa request da collection do Postman também.*/
    @GetMapping(value = "/consume")
    public ResponseEntity<List<BusinessPartnerDTOResponse>> consumePartners() {
        return ResponseEntity.ok(service.consumePartnersApi());
    }

    @GetMapping(value = "/find")
    public ResponseEntity<List<BusinessPartnerDTOResponse>> findPartners() {
        return ResponseEntity.ok(service.findAllPartners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessPartnerDTOResponse> findPartnersById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BusinessPartnerDTORequest> store(@RequestBody @Valid BusinessPartnerDTORequest dtoRequest) {
        return new ResponseEntity<>(service.save(dtoRequest), HttpStatus.CREATED); //ResponseEntity.status(HttpStatus.CREATED).build().;// .created(service.salvar(dtoRequest)).build(); //new ResponseEntity<>(service.salvar(dtoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessPartnerDTORequest> store(@PathVariable Long id, @RequestBody @Valid BusinessPartnerDTORequest dtoRequest) {
        return ResponseEntity.ok(service.update(id, dtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusinessPartnerDTORequest> destroy(@PathVariable Long id) {
        service.destroy(id);
        return ResponseEntity.noContent().build();
    }
}
