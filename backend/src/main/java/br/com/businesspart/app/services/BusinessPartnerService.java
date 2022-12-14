package br.com.businesspart.app.services;

import br.com.businesspart.app.dtos.BusinessPartnerApiDTO;
import br.com.businesspart.app.dtos.response.BusinessPartnerDTOResponse;
import br.com.businesspart.app.dtos.request.BusinessPartnerDTORequest;
import br.com.businesspart.app.entities.BusinessPartnerEntity;
import br.com.businesspart.app.exceptionhandlers.PartnerNotFoundException;
import br.com.businesspart.app.repositories.BusinessPartnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@Service
public class BusinessPartnerService {
    private BusinessPartnerRepository repository;

    public BusinessPartnerService(BusinessPartnerRepository repository) {
        this.repository = repository;
    }

    public BusinessPartnerEntity validatedById(Long id) {
        Optional<BusinessPartnerEntity> obj = repository.findById(id);
        obj.orElseThrow(PartnerNotFoundException::new);
        return obj.get();
    }

    public BusinessPartnerDTORequest save(BusinessPartnerDTORequest dtoRequest) {
        return toDTORequest(repository.save(toEntity(dtoRequest)));
    }

    public BusinessPartnerDTORequest update(@NotNull Long id, BusinessPartnerDTORequest dtoRequest) {
        BusinessPartnerEntity entity = this.validatedById(id);
        this.repository.findByCardCode(entity.getCardCode()).get().setCardname(dtoRequest.getCardname());
        return toDTORequest(repository.save(entity));
    }

    public void destroy(@NotNull Long id) {
        BusinessPartnerEntity entity = this.validatedById(id);
        repository.delete(entity);
    }

    public BusinessPartnerEntity toEntity(BusinessPartnerDTOResponse dto) {
        BusinessPartnerEntity entity = new BusinessPartnerEntity();
        entity.setCardCode(dto.getCardCode());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setCardname(dto.getCardname());
        entity.setAvatar(dto.getAvatar());
        entity.setAddress(dto.getAddress());
        entity.setZipcode(dto.getZipcode());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setAno(dto.getAno());

        return entity;
    }

    public BusinessPartnerEntity toEntity(BusinessPartnerDTORequest dtoRequest) {
        BusinessPartnerEntity entity = new BusinessPartnerEntity();
        entity.setCardCode(dtoRequest.getCardCode());
        entity.setCreatedAt(dtoRequest.getCreatedAt());
        entity.setCardname(dtoRequest.getCardname());
        entity.setAvatar(dtoRequest.getAvatar());
        entity.setAddress(dtoRequest.getAddress());
        entity.setZipcode(dtoRequest.getZipcode());
        entity.setMarca(dtoRequest.getMarca());
        entity.setModelo(dtoRequest.getModelo());
        entity.setAno(dtoRequest.getAno());

        return entity;
    }

    public BusinessPartnerDTOResponse toDTO(BusinessPartnerEntity entity) {
        BusinessPartnerDTOResponse dto = new BusinessPartnerDTOResponse();
        dto.setCardCode(entity.getCardCode());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCardname(entity.getCardname());
        dto.setAvatar(entity.getAvatar());
        dto.setAddress(entity.getAddress());
        dto.setZipcode(entity.getZipcode());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setAno(entity.getAno());

        return dto;
    }

    public BusinessPartnerDTORequest toDTORequest(BusinessPartnerEntity entity) {
        BusinessPartnerDTORequest dtoRequest = new BusinessPartnerDTORequest();
        dtoRequest.setCardCode(entity.getCardCode());
        dtoRequest.setCreatedAt(entity.getCreatedAt());
        dtoRequest.setCardname(entity.getCardname());
        dtoRequest.setAvatar(entity.getAvatar());
        dtoRequest.setAddress(entity.getAddress());
        dtoRequest.setZipcode(entity.getZipcode());
        dtoRequest.setMarca(entity.getMarca());
        dtoRequest.setModelo(entity.getModelo());
        dtoRequest.setAno(entity.getAno());

        return dtoRequest;
    }

    public List<BusinessPartnerDTOResponse> consumePartnersApi() {
        String baseUrl = "https://637283b2348e947299f77e08.mockapi.io/b1s/v2/BusinessPartners";
        RestTemplate restTemplate = new RestTemplate();
        BusinessPartnerApiDTO[] arrayBusinessPartnerApiDTOS = restTemplate.getForObject(baseUrl, BusinessPartnerApiDTO[].class);
        List<BusinessPartnerApiDTO> responseList = Arrays.asList(arrayBusinessPartnerApiDTOS);
        List<BusinessPartnerEntity> deParaList = new ArrayList<>();
        responseList.forEach(item -> {
            BusinessPartnerEntity businessPartnerEntity = new BusinessPartnerEntity();
            businessPartnerEntity.setCardCode(item.getCardCode());
            if (item.getCreatedAt().contains("Z")) {
                Instant instant = Instant.parse(item.getCreatedAt());
                businessPartnerEntity.setCreatedAt(instant);
            } else {
                String concat = item.getCreatedAt() + "Z";
                Instant instant = Instant.parse(concat);
                businessPartnerEntity.setCreatedAt(instant);
            }

            businessPartnerEntity.setCardname(item.getCardName());
            businessPartnerEntity.setAvatar(item.getAvatar());
            businessPartnerEntity.setAddress(item.getAddress());
            businessPartnerEntity.setZipcode(item.getZipCode());
            businessPartnerEntity.setMarca(item.getMarca());
            businessPartnerEntity.setModelo(item.getModelo());
            businessPartnerEntity.setAno(item.getAno());
            deParaList.add(repository.save(businessPartnerEntity));

        });
        List<BusinessPartnerDTOResponse> dtoList = deParaList.stream().map(x -> new BusinessPartnerDTOResponse(x)).toList();

        return dtoList;

    }

    public List<BusinessPartnerDTOResponse> findAllPartners() {
        List<BusinessPartnerEntity> entityList = repository.findAll();
        if (entityList.isEmpty()) {
            return consumePartnersApi();
        } else {
            List<BusinessPartnerDTOResponse> dtoList = entityList.stream().map(x -> new BusinessPartnerDTOResponse(x)).toList();
            return dtoList;
        }
        //List<BusinessPartnerEntity> entityList = repository.findAll();
    }

    public BusinessPartnerDTOResponse findById(@NotNull Long id) {
        return toDTO(this.validatedById(id));
    }

}
