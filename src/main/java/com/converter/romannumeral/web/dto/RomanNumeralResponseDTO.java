package com.converter.romannumeral.web.dto;

public class RomanNumeralResponseDTO
{
    private String input;
    private String output;

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
