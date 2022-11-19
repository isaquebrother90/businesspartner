package br.com.businesspart.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessPartnerApiDTO {

    @NotEmpty
    private Long cardCode;
    @NotEmpty
    private String createdAt;//No service, converter essa String em DateTime
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
    private String marca;
    private String modelo;
    private Integer ano;

}
