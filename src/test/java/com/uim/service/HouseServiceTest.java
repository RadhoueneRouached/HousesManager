package com.uim.service;

import com.uim.HouseApplication;
import com.uim.data.HouseProvider;
import com.uim.exceptions.HouseException;
import com.uim.models.House;
import com.uim.models.WriteHouseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Radhouene Rouached
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HouseApplication.class)
@SpringBootTest
@Sql("classpath:testdata.sql")
public class HouseServiceTest {

    @Autowired
    private HouseServiceImpl houseService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllHouse() {
        List<House> houseList = new ArrayList<House>();
        houseList.add(HouseProvider.getHouse(Long.valueOf(16)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(17)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(18)));
        List<House> result = houseService.getAllHouses();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void getHouseByOwerName() {
        List<House> houseList = new ArrayList<House>();
        houseList.add(HouseProvider.getHouse(Long.valueOf(16)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(17)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(18)));
        String owername = new String("Radhouene Rouached");
        List<House> result = houseService.getHousesByOwnerName(owername);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void getHouseByOwerName_wrongName() {
        List<House> houseList = new ArrayList<House>();
        houseList.add(HouseProvider.getHouse(Long.valueOf(16)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(17)));
        houseList.add(HouseProvider.getHouse(Long.valueOf(18)));
        String owername = new String("Radi");
        List<House> result = houseService.getHousesByOwnerName(owername);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void getHouseById() throws HouseException {
        House house = HouseProvider.getHouse(Long.valueOf(1));
        House result = houseService.getHouseById(house.getId());
        assertNotNull(result);
        assertEquals(result.getId(), house.getId());
    }

    @Test
    public void saveHouse() {
        WriteHouseDto writeHouse = HouseProvider.getWriteHouse();
        House result = houseService.saveHouse(writeHouse);
        assertNotNull(result);
        verifyHouses(result, writeHouse);
    }

    @Test
    public void removeHouse() throws HouseException {
        House house = HouseProvider.getHouse(Long.valueOf(2));
        houseService.deleteHouse(Long.valueOf(1));
    }

    public void verifyHouses(House result, WriteHouseDto writeHouse) {
        assertEquals(result.getHouseNumber(), writeHouse.getHouseNumber());
        assertEquals(result.getCity(), writeHouse.getCity());
        assertEquals(result.getStreet(), writeHouse.getStreet());
        assertEquals(result.getPostalCode(), writeHouse.getPostalCode());
        assertEquals(result.getConstructionDate(), writeHouse.getConstructionDate());
    }

}
