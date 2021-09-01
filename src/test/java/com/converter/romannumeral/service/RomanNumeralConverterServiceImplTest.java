package com.converter.romannumeral.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RomanNumeralConverterServiceImplTest
{

    @Test
    void convertIntegerToRomanNumeral_Success()
    {
        RomanNumeralConverterServiceImpl romanNumeralConverterService = new RomanNumeralConverterServiceImpl();

        Assertions.assertEquals("I", romanNumeralConverterService.convertIntegerToRomanNumeral(1));
        Assertions.assertEquals("C", romanNumeralConverterService.convertIntegerToRomanNumeral(100));
        Assertions.assertEquals("CMXCIX", romanNumeralConverterService.convertIntegerToRomanNumeral(999));
        Assertions.assertEquals("MCDXCVIII", romanNumeralConverterService.convertIntegerToRomanNumeral(1498));
        Assertions.assertEquals("MMMCDXLIV", romanNumeralConverterService.convertIntegerToRomanNumeral(3444));
        Assertions.assertEquals("MMMCMXCIX", romanNumeralConverterService.convertIntegerToRomanNumeral(3999));
    }
}