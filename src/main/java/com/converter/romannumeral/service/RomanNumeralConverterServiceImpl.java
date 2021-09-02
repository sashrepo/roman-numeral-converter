package com.converter.romannumeral.service;

import org.springframework.stereotype.Service;

/**
 * Implements RomanNumeralConverterService to provide logic for converting integer to romannumeral
 *
 * @author sselvaraj
 */
@Service
public class RomanNumeralConverterServiceImpl implements RomanNumeralConverterService
{
    private static final int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private static final String[] romanNumerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV",
            "I" };

    /**
     * Reference - https://simple.wikipedia.org/wiki/Roman_numerals
     * Using wikipedia as a reference - numbers and its corresponding roman numerals are declared and stored as static variables
     * to help in converting the integer input to its corresponding roman numeral representation
     *
     * Space Complexity - O(1) - Since the input is an integer, the memory used is going to remain constant
     * Time Complexity - O(1) -Since the number of roman numerals is finite,
     *                          the iteration can only happen a defined number of times, so the time complexity is O(1)
     * @param input -int
     * @return - Roman numeral representation of the given input - String
     */
    @Override
    public String convertIntegerToRomanNumeral(int input)
    {
        StringBuilder romanNumeralBuilder = new StringBuilder();

        //iterate over until the numbers array length and the input number getting to 0
        for (int i = 0; i < numbers.length && input > 0; i++)
        {
            //loop until the inout fits in the given number range and select the appropriate roman numeral accordingly
            while (numbers[i] <= input)
            {
                input -= numbers[i];
                romanNumeralBuilder.append(romanNumerals[i]);
            }
        }
        return romanNumeralBuilder.toString();
    }
}
