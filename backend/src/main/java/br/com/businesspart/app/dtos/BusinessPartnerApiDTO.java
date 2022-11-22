package br.com.businesspart.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessPartnerApiDTO {

    @NotEmpty
    @JsonProperty("CardCode")
    private Long cardCode;
    @NotEmpty
    @JsonProperty("createdAt")
    private String createdAt;//No service, converter essa String em DateTime
    @NotEmpty
    @JsonProperty("CardName")
    private String cardName;
    @NotEmpty
    @JsonProperty("avatar")
    private String avatar;
    @NotEmpty
    @JsonProperty("Address")
    private String address;
    @NotEmpty
    @JsonProperty("ZipCode")
    private String zipCode;
    @JsonProperty("marca")
    private String marca;
    @JsonProperty("modelo")
    private String modelo;
    @JsonProperty("ano")
    private Integer ano;

}
