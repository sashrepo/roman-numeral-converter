package com.converter.romannumeral.web.dto;

public class ErrorResponseDTO
{
    private int statusCode;
    private String errorMessage;

    @Override
    public String toString()
    {
        return "{" +
                "statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public ErrorResponseDTO()
    {
    }

    public ErrorResponseDTO(int statusCode, String errorMessage)
    {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
