package com.converter.romannumeral.web.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.converter.romannumeral.service.RomanNumeralConverterService;
import com.converter.romannumeral.web.error.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Unit tests - To test validation and exception handling at controller layer
 *
 * @author sselvaraj
 */
@WebMvcTest(RomanNumeralConverterController.class)
class RomanNumeralConverterControllerTest
{
    @MockBean
    RomanNumeralConverterService romanNumeralConverterService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void convertIntegerToRomanNumeral_Success() throws Exception
    {
        Mockito.when(romanNumeralConverterService.convertIntegerToRomanNumeral(101)).thenReturn("CI");

        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral?query=101"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("CI")));
    }

    @Test
    void convertIntegerToRomanNumeral_lessThanMinError() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral?query=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(
                        "Invalid input, enter an integer value in the range from 1 to 3999")));
    }

    @Test
    void convertIntegerToRomanNumeral_greaterThanMaxError() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral?query=4000"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(ErrorMessage.INVALID_DATA)));
    }

    @Test
    void convertIntegerToRomanNumeral_inputTypeMismatchError() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral?query=plsFail"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(ErrorMessage.INPUT_TYPE_MISMATCH)));
    }

    @Test
    void convertIntegerToRomanNumeral_internalServerError() throws Exception
    {
        Mockito.when(romanNumeralConverterService.convertIntegerToRomanNumeral(1000)).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral?query=1000"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString(ErrorMessage.INTERNAL_SERVER_ERROR)));
    }
}