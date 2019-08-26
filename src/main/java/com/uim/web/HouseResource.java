package com.uim.web;

import com.uim.exceptions.HouseException;
import com.uim.models.House;
import com.uim.models.Response;
import com.uim.models.WriteHouseDto;
import com.uim.service.HouseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Radhouene Rouached
 */
@RestController
@RequestMapping("/api/house")
@Api(value = "Housing Management System")
public class HouseResource {

    private static final Logger logger = LoggerFactory.getLogger(HouseResource.class);
    HouseServiceImpl houseService;

    public HouseResource(HouseServiceImpl houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of available houses.", tags = {"House"}, response = House.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The House resource you were trying to reach is not found")
    })
    public ResponseEntity<List<House>> getAllHouses() {
        logger.info("Get all houses");
        return new ResponseEntity<>(houseService.getAllHouses(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get a specific House by Id.", tags = {"House"})
    public ResponseEntity<House> getHouseById(@PathVariable("id") long id) throws HouseException {
        logger.info("House {} to return ", id);
        House house = this.houseService.getHouseById(id);
        if (house == null) {
            throw new HouseException("House with id:" + id + " does not exist");
        }
        return new ResponseEntity<House>(houseService.getHouseById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create House.", tags = {"WriteHouseDto"}, response = WriteHouseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "House successfully created."),
            @ApiResponse(code = 400, message = "House object request is malformed."),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden."),
    })
    public ResponseEntity<House> saveHouse(@Valid @RequestBody WriteHouseDto house) throws HouseException {
        logger.info("House house {} to save ", house);
        return new ResponseEntity<House>(houseService.saveHouse(house), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update House.", tags = {"WriteHouseDto"}, response = WriteHouseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "House successfully updated."),
            @ApiResponse(code = 400, message = "House object request is malformed."),
    })
    public ResponseEntity<House> updateHouse(@PathVariable("id") Long id, @Valid @RequestBody WriteHouseDto housePayload) throws HouseException {
        logger.info("House to update {} ", housePayload);
        return new ResponseEntity<House>(houseService.updateHouse(id,housePayload), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a specific House by Id.", tags = {"House"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "House successfully deleted."),
            @ApiResponse(code = 404, message = "The House resource you were trying to delete is not found.")
    })
    public ResponseEntity<Response> deleteHouseById(@PathVariable("id") long id) throws HouseException {
        logger.info("House {} to remove ", id);
        houseService.deleteHouse(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.NO_CONTENT.value(), "House has been deleted"), HttpStatus.NO_CONTENT);
    }
}