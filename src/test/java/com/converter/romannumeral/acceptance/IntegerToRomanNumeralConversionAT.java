package com.converter.romannumeral.acceptance;

import java.io.IOException;

import com.converter.romannumeral.web.dto.ErrorResponseDTO;
import com.converter.romannumeral.web.dto.RomanNumeralResponseDTO;
import com.converter.romannumeral.web.error.ErrorMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerToRomanNumeralConversionAT
{

    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException
    {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

    @Test
    public void testDefaultContentTypeIsJson() throws IOException
    {
        // Given
        String expectedContentType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=3999");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        Assertions.assertEquals(expectedContentType, ContentType.getOrDefault(response.getEntity()).getMimeType());
    }

    @Test
    public void testIntegerToRomanNumeralConversion_Success() throws IOException
    {
        // Given
        String expectedContentType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=3999");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        RomanNumeralResponseDTO romanNumeralResponseDTO = retrieveResourceFromResponse(response,
                                                                                       RomanNumeralResponseDTO.class);

        // Then
        Assertions.assertEquals("3999", romanNumeralResponseDTO.getInput());
        Assertions.assertEquals("MMMCMXCIX", romanNumeralResponseDTO.getOutput());
    }

    @Test
    public void testIntegerToRomanNumeralConversion_ValidationError_OutOfRange() throws IOException
    {
        // Given
        String expectedContentType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=4000");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        ErrorResponseDTO errorResponseDTO = retrieveResourceFromResponse(response, ErrorResponseDTO.class);

        // Then
        Assertions.assertEquals(400, errorResponseDTO.getStatusCode());
        Assertions.assertTrue(errorResponseDTO.getErrorMessage().contains(ErrorMessage.INVALID_DATA));
    }

    @Test
    public void testIntegerToRomanNumeralConversion_ValidationError_InvalidDataType() throws IOException
    {
        // Given
        String expectedContentType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=asdfasf");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        ErrorResponseDTO errorResponseDTO = retrieveResourceFromResponse(response, ErrorResponseDTO.class);

        // Then
        Assertions.assertEquals(400, errorResponseDTO.getStatusCode());
        Assertions.assertTrue(errorResponseDTO.getErrorMessage().contains(ErrorMessage.INPUT_TYPE_MISMATCH));
    }
}
