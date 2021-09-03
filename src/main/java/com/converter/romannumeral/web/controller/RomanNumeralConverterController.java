package com.converter.romannumeral.web.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.converter.romannumeral.service.RomanNumeralConverterService;
import com.converter.romannumeral.web.dto.RomanNumeralResponseDTO;
import com.converter.romannumeral.web.error.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Rest Controller to create REST api for converting Integer to RomanNumeral
 *
 * @author sselvaraj
 */
@RestController
@RequestMapping(value = "/romannumeral")
@Api(value = "REST api to convert Integer to Roman Numeral")
@Validated
public class RomanNumeralConverterController
{
    private static final Logger LOG = LoggerFactory.getLogger(RomanNumeralConverterController.class);

    @Autowired
    RomanNumeralConverterService romanNumeralConverterService;

    /**
     * GET method to convert int to Roman Numeral with a min value of 1 and max value of 3999,
     * exceptions handled by RestExceptionHandler advice
     *
     * @param input
     * @return RomanNumeralResponseDTO, ErrorResponseDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "GET endpoint that accepts an int request and responds with the converted romannumeral in JSON format", response = RomanNumeralResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = ErrorMessage.INPUT_TYPE_MISMATCH),
            @ApiResponse(code = 400, message = ErrorMessage.INVALID_DATA),
            @ApiResponse(code = 500, message = ErrorMessage.INTERNAL_SERVER_ERROR)
    })
    public RomanNumeralResponseDTO convertIntegerToRomanNumeral(@RequestParam("query")
                                                                @Min(value = 1)
                                                                @Max(value = 3999) int input)
    {
        LOG.info("Incoming request: " + input);
        String romanNumeral = romanNumeralConverterService.convertIntegerToRomanNumeral(input);
        RomanNumeralResponseDTO romanNumeralResponse = new RomanNumeralResponseDTO(String.valueOf(input), romanNumeral);
        LOG.info("Outgoing response: " + romanNumeralResponse.toString());
        return romanNumeralResponse;
    }
}
