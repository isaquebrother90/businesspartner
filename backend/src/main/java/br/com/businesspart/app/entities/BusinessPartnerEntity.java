package br.com.businesspart.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "bnpartner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessPartnerEntity implements Serializable {

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "card_name")
    @JsonProperty("CardName")
    private String cardname;

    private String avatar;

    private String address;

    @Column(name = "zip_code")
    private String zipcode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_code")
    @JsonProperty("CardCode")
    private Long cardCode;

    private String marca;

    private String modelo;

    private Integer ano;

}