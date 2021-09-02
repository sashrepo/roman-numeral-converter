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

/**
 * Acceptance tests that can be integrated into the CI/CD pipeline with test cases
 * required to certify the application ready for deployment to next stage
 *
 * @author sselvaraj
 */
public class IntegerToRomanNumeralConversionAT
{

    // helper method to parse json response body from Httpresponse
    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException
    {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }

    //Acceptance Test Case #1 - Validate response content type is application/json
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

    //Acceptance Test Case #2 - Happy day case to validate conversion of a valid int to roman numeral
    @Test
    public void testIntegerToRomanNumeralConversion_Success() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=3999");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        RomanNumeralResponseDTO romanNumeralResponseDTO = retrieveResourceFromResponse(response,
                                                                                       RomanNumeralResponseDTO.class);

        Assertions.assertEquals("3999", romanNumeralResponseDTO.getInput());
        Assertions.assertEquals("MMMCMXCIX", romanNumeralResponseDTO.getOutput());
    }

    //Acceptance Test Case #3 - Error case to validate out of range conversions. Valid range 1 to 3999
    @Test
    public void testIntegerToRomanNumeralConversion_ValidationError_OutOfRange() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=4000");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        ErrorResponseDTO errorResponseDTO = retrieveResourceFromResponse(response, ErrorResponseDTO.class);

        Assertions.assertEquals(400, errorResponseDTO.getStatusCode());
        Assertions.assertTrue(errorResponseDTO.getErrorMessage().contains(ErrorMessage.INVALID_DATA));
    }

    //Acceptance Test Case #4 - Error case to validate invalid data type inputs. Valid data type int, range 1 to 3999
    @Test
    public void testIntegerToRomanNumeralConversion_ValidationError_InvalidDataType() throws IOException
    {
        HttpUriRequest request = new HttpGet("http://localhost:8080/romannumeral?query=asdfasf");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        ErrorResponseDTO errorResponseDTO = retrieveResourceFromResponse(response, ErrorResponseDTO.class);

        Assertions.assertEquals(400, errorResponseDTO.getStatusCode());
        Assertions.assertTrue(errorResponseDTO.getErrorMessage().contains(ErrorMessage.INPUT_TYPE_MISMATCH));
    }
}
