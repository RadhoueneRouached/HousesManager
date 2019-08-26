package com.uim.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Radhouene Rouached
 */
@Entity
@Table(name = "house")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number")
    private int houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;


    @Column(name = "country_code", columnDefinition = "varchar(2) default 'DE'")
    private String countryCode= "DE";

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "number_bedrooms")
    private short numberBedrooms;

    @Column(name = "construction_date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate constructionDate;

    @Column(name = "price")
    private float price;
}