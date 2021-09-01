package com.converter.romannumeral.service;

import org.springframework.stereotype.Service;

@Service
public class RomanNumeralConverterServiceImpl implements RomanNumeralConverterService
{
    private static final int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private static final String[] romanNumerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
            "I" };

    @Override
    public String convertIntegerToRomanNumeral(int input)
    {
        StringBuilder romanNumeralBuilder = new StringBuilder();

        for (int i = 0; i < numbers.length && input > 0; i++)
        {
            while (numbers[i] <= input)
            {
                input -= numbers[i];
                romanNumeralBuilder.append(romanNumerals[i]);
            }
        }
        return romanNumeralBuilder.toString();
    }
}
