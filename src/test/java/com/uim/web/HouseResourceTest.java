package com.uim.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uim.HouseApplication;
import com.uim.data.HouseProvider;
import com.uim.models.House;
import com.uim.models.WriteHouseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Radhouene Rouached
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HouseApplication.class)
@SpringBootTest
@Sql("classpath:testdata.sql")
public class HouseResourceTest {
    private MockMvc mockMvc;
    private ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void saveHouse() throws Exception {
        WriteHouseDto writeHouse = HouseProvider.getWriteHouse();
        byte[] json = jsonMapper.writeValueAsBytes(writeHouse);
        MvcResult result = mockMvc.perform(post("/api/house/")
                .content(jsonMapper.writeValueAsBytes(writeHouse))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        House houseCreated = jsonMapper.readValue(result.getResponse().getContentAsString(), House.class);
        Assert.assertTrue("Excpect house to be created ", houseCreated != null);
        verifyHouses(writeHouse, houseCreated);
    }

    @Test
    public void getAllHouses() throws Exception {
        mockMvc.perform(get("/api/house").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }


    @Test
    public void getHouseById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/house/2"))
                .andDo(print())
                .andReturn();
        House house = jsonMapper.readValue(mvcResult.getResponse().getContentAsString(), House.class);
        Assert.assertNotNull(house);
    }

    @Test
    public void verifyInvalidHouseArgument() throws Exception {
        mockMvc.perform(get("/api/house/f"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void verifyInvalidHouseId() throws Exception {
        mockMvc.perform(get("/api/house/0")
                .content(jsonMapper.writeValueAsString(HouseProvider.getHouse(Long.valueOf(55))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }


    @Test
    public void verifyInvalidHouseIdToDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/house/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    public void verifyUpdateHouse() throws Exception {
        WriteHouseDto house = HouseProvider.getWriteHouse();
        house.setCity("City updated");
        MvcResult result = mockMvc.perform(put("/api/house/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsBytes(house))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        House houseUpdated = jsonMapper.readValue(result.getResponse().getContentAsString(), House.class);
        Assert.assertTrue("Excpect house to be updated ", houseUpdated != null);
        verifyHouses(house, houseUpdated);

    }

    @Test
    public void updateHouse_invaidIdTest() throws Exception {
        WriteHouseDto house = HouseProvider.getWriteHouse();
        mockMvc.perform(put("/api/house/55")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsBytes(house)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    public void deleteHouseById() throws Exception {
        mockMvc.perform(delete("/api/house/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }

    public void verifyHouses(WriteHouseDto house, House result) {
        Assert.assertEquals("Expect [house number:" + result.getHouseNumber() + "] to be the same", result.getHouseNumber(), house.getHouseNumber());
        Assert.assertEquals("Expect [house street:" + result.getStreet() + "] to be the same", result.getStreet(), house.getStreet());
        Assert.assertEquals("Expect [house city:" + result.getCity() + "] to be the same", result.getCity(), house.getCity());
    }
}
