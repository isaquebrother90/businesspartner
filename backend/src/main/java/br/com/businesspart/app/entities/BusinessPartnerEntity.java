package br.com.businesspart.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bnpartner")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessPartnerEntity {

    @Id
    @Column(name = "card_code")
    private Long cardCode;

    @Column(name = "created_at")
    private String createdAt;

    @Setter
    @Column(name = "card_name")
    private String cardName;

    private String avatar;

    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    private String marca;

    private String modelo;

    private Integer ano;

}