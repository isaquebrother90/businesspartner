package br.com.businesspart.app.dtos;

import br.com.businesspart.app.entities.BusinessPartnerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class BusinessParnerDTO {

    @NotEmpty
    private Long cardCode;
    @NotEmpty
    private String createdAt;
    @Setter
    @NotEmpty
    private String cardName;
    @NotEmpty
    private String avatar;
    @NotEmpty
    private String address;
    @NotEmpty
    private String zipCode;
    private String marca;
    private String modelo;
    private Integer ano;

    public BusinessPartnerEntity toEntity() {
        return new BusinessPartnerEntity(this.cardCode, this.createdAt, this.cardName, this.avatar, this.address, this.zipCode, this.marca, this.modelo, this.ano);
    }

}
