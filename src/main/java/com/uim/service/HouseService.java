package com.uim.service;

import com.uim.exceptions.HouseException;
import com.uim.models.House;
import com.uim.models.WriteHouseDto;

import java.util.List;

/**
 * @author Radhouene Rouached
 */
public interface HouseService {
    List<House> getAllHouses();

    List<House> getHousesByOwnerName(String ownerName);

    House getHouseById(Long id) throws HouseException;

    House saveHouse(WriteHouseDto house);

    House updateHouse(Long id, WriteHouseDto house) throws HouseException;

    void deleteHouse(Long id) throws HouseException;
}
