package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.TestApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class NsClientTest {

    @Autowired
    private NsClient nsClient;

    @Value("${authentication.key.name.ns}") private String expectedKeyName;
    @Value("${authentication.key.value.ns}") private String expectedKeyValue;
    @Value("${urls.base.ns}") private String nsBaseUri;
    @Value("${urls.path.ns}") private String uriPath;


    @Test
    public void contextLoads() {
    }

    @Test
    public void keyNameIsPopulatedOnLoadingWithConfigValue() {
        assertEquals(expectedKeyName, nsClient.getKeyName());
    }
    @Test
    public void keyNameIsPopulatedOnLoadingWithConfigValueInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getKeyName());
    }

    @Test
    public void keyValueIsPopulatedOnLoadingWithConfigValue() {
        assertEquals(expectedKeyValue, nsClient.getKeyValue());
    }
    @Test
    public void keyValueIsPopulatedOnLoadingWithConfigValueInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getKeyValue());
    }

    /** Tests for URI */
    @Test
    public void uriBaseUriEqualsExpectedBaseUri() {
        assertEquals(nsBaseUri, nsClient.getUriBase());
    }
    @Test
    public void uriBaseUriEqualsExpectedBaseUriInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getUriBase());
    }
    @Test
    public void uriPathEqualsExpectedPath() {
        assertEquals(uriPath, nsClient.getUriPath());
    }
    @Test
    public void uriPathEqualsExpectedPathInvalid() {
        assertNotEquals(UUID.randomUUID().toString(), nsClient.getUriBase());
    }

    /** TODO: Make this test mock an object.
     *  TODO: Make it more generic */
    @Test
    public void fullUriConsumesInputInCreateUriMethod() {
        String originEva = "8400282";
        String destinationEva = "8400056";
        String dateTime = "2019-10-07T16L25:00+0200";

        String expectedUri = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16L25:00+0200";
        assertEquals(expectedUri, nsClient.buildUri(originEva, destinationEva, dateTime).toString());
    }

    /** Tests for HttpEntity creation */
    @Test
    public void httpEntityHasHeader() {
        assertEquals(expectedKeyValue, nsClient.getHttpEntity()
                .getHeaders().getFirst(expectedKeyName));
    }
    @Test
    public void testThatHeaderIsNotNull () {
        assertFalse(nsClient.getHttpEntity().getHeaders().isEmpty());
    }


    @Test
    public void restTemplateUriIsNotNull() {



//        assertNotNull(nsClient.createRestTemplate());
    }
    @Test
    public void restTemplateHeaderIsNotNull() {

    }



    @Test
    public void fullUriIsGeneratedFromParameters() {

    }

    @Test
    public void fullRestTemplateIsGenerated() {

    }



    //2.2: When context loaded, key.name and key.value is equal to hardcoded value
    //2.3: When context loaded, key.name and key.value are equal to application.properties values
    //3:

}