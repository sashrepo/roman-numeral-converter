package com.converter.romannumeral.web.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.converter.romannumeral.service.RomanNumeralConverterService;
import com.converter.romannumeral.web.dto.RomanNumeralResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/romannumeral")
@Validated
public class RomanNumeralConverterController
{
    private static final Logger LOG = LoggerFactory.getLogger(RomanNumeralConverterController.class);

    @Autowired
    RomanNumeralConverterService romanNumeralConverterService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
