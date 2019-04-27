package busyflight.controller;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusyFlightControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
	base = new URL("http://localhost:" + port + "/busyFlights");
	final RestGatewaySupport gateway = new RestGatewaySupport();
	gateway.setRestTemplate(restTemplate);
	mockServer = MockRestServiceServer.createServer(gateway);
    }

    @Test
    public void testGetRootResourceOnce() {
	mockServer.expect(once(), requestTo("http://localhost:8080"))
		.andRespond(withSuccess("{message : 'under construction'}", MediaType.APPLICATION_JSON));

//	final String result = service.getRootResource();
	// System.out.println("testGetRootResourceOnce: " + result);

	mockServer.verify();
	// assertEquals("{message : 'under construction'}", result);
    }

    @Test
    public void getHello() throws Exception {
	final ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
	// assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }
}