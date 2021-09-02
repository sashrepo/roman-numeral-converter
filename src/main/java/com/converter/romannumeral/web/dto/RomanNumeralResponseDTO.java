package com.converter.romannumeral.web.dto;

/**
 * Response DTO for RomanNumeralConverterController.convertIntegerToRomanNumeral
 *
 * @author sselvaraj
 */
public class RomanNumeralResponseDTO
{
    private String input;
    private String output;

    @Override
    public String toString()
    {
        return "{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    public RomanNumeralResponseDTO()
    {
    }

    public RomanNumeralResponseDTO(String input, String output)
    {
        this.input = input;
        this.output = output;
    }

    public String getInput()
    {
        return input;
    }

    public void setInput(String input)
    {
        this.input = input;
    }

    public String getOutput()
    {
        return output;
    }

    public void setOutput(String output)
    {
        this.output = output;
    }
}
