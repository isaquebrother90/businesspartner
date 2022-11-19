package br.com.businesspart.app.dtos.response;

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
public class BusinessPartnerDTOResponse {
    @NotEmpty
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    //@JsonIgnore
    private Instant createdAt = Instant.now();

    @NotEmpty
    @JsonProperty("CardName")
    private String cardname;
    @NotEmpty
    private String avatar;
    @NotEmpty
    @JsonProperty("Address")
    private String address;
    @NotEmpty
    @JsonProperty("ZipCode")
    private String zipcode;

    private Long cardCode;
    private String marca;
    private String modelo;
    private Integer ano;

    public BusinessPartnerDTOResponse(BusinessPartnerEntity businessPartnerEntity) {
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
