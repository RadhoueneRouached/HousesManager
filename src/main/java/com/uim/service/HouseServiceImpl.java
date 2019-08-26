package com.uim.service;

import com.uim.exceptions.HouseException;
import com.uim.models.House;
import com.uim.models.WriteHouseDto;
import com.uim.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Radhouene Rouached
 */
@Service("HouseService")
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    public HouseServiceImpl(@Qualifier("houseRepository") HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Override
    public List<House> getHousesByOwnerName(String ownerName) {
        return houseRepository.findByOwnerName(ownerName);
    }

    @Override
    public House getHouseById(Long id) throws HouseException {
        if (!houseRepository.existsById(id)) {
            throw new HouseException("House not found");
        }
        return houseRepository.findById(id).get();
    }

    @Override
    public House saveHouse(WriteHouseDto house) {
        House houseToUpdate = new House();
        applyValues(houseToUpdate, house);
        return houseRepository.save(houseToUpdate);
    }

    @Override
    public House updateHouse(Long id, WriteHouseDto house) throws HouseException {
        House houseToUpdate = getHouseById(id);
        applyValues(houseToUpdate, house);
        return houseRepository.save(houseToUpdate);
    }

    @Override
    public void deleteHouse(Long id) throws HouseException {
        House house = getHouseById(id);
        houseRepository.delete(house);
    }

    private void applyValues(House houseToUpdate, WriteHouseDto house) {
        houseToUpdate.setHouseNumber(house.getHouseNumber());
        houseToUpdate.setStreet(house.getStreet());
        houseToUpdate.setCity(house.getCity());
        houseToUpdate.setOwnerName(house.getOwnerName());
        houseToUpdate.setPostalCode(house.getPostalCode());
        houseToUpdate.setNumberBedrooms(house.getNumberBedrooms());
        houseToUpdate.setConstructionDate(house.getConstructionDate());
        houseToUpdate.setPrice(house.getPrice());
    }
}
