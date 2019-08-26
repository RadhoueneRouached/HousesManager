package com.uim.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Radhouene Rouached
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteHouseDto {

    private int houseNumber;
    private String street;
    private String city;
    private String postalCode;
    private String ownerName;
    private short numberBedrooms;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate constructionDate;
    private float price;
}