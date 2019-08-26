package com.uim.data;

import com.uim.models.House;
import com.uim.models.WriteHouseDto;

import java.time.LocalDate;

/**
 * @author Radhouene Rouached
 */
public class HouseProvider {

    public static House getHouse(Long id) {
        House house = House.builder()
                .id(id)
                .houseNumber(36)
                .street("frankfurt strasse 36")
                .city("münchen")
                .postalCode("80331")
                .ownerName("Radhouene Rouached")
                .constructionDate(LocalDate.parse("2015-01-03"))
                .numberBedrooms((short) 4)
                .price((float) 12000.0000)
                .build();
        return house;
    }

    public static WriteHouseDto getWriteHouse() {
        WriteHouseDto house = WriteHouseDto.builder()
                .houseNumber(36)
                .street("frankfurt strasse 36")
                .city("Munich")
                .postalCode("80331")
                .ownerName("Radhouene Rouached")
                .constructionDate(LocalDate.now())
                .numberBedrooms((short) 4)
                .price((float) 12000.0000)
                .build();
        return house;
    }
}
