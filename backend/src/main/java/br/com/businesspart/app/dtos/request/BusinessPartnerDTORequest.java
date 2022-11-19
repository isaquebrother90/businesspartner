package br.com.businesspart.app.dtos.request;

import br.com.businesspart.app.entities.BusinessPartnerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessPartnerDTORequest {

    private Instant createdAt = Instant.now();

    @NotEmpty
    @JsonProperty("CardName")
    private String cardname;
    @NotEmpty
    @JsonProperty("avatar")
    private String avatar;
    @NotEmpty
    @JsonProperty("Address")
    private String address;
    @NotEmpty
    @JsonProperty("ZipCode")
    private String zipcode;
    @JsonProperty("CardCode")
    private Long cardCode;
    private String marca;
    private String modelo;
    private Integer ano;

    public BusinessPartnerDTORequest(BusinessPartnerEntity businessPartnerEntity) {
        cardCode = businessPartnerEntity.getCardCode();
        createdAt = businessPartnerEntity.getCreatedAt();
        cardname = businessPartnerEntity.getCardname();
        avatar = businessPartnerEntity.getAvatar();
        address = businessPartnerEntity.getAddress();
        zipcode = businessPartnerEntity.getZipcode();
        marca = businessPartnerEntity.getMarca();
        modelo = businessPartnerEntity.getModelo();
        ano = businessPartnerEntity.getAno();
    }

}
