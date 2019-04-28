package lz.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import lz.model.Card;
import lz.model.PostCardResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardsControllerSTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCards() {
	final String body = restTemplate.getForObject("/api/cards/", String.class);
	assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void testPostCardSuccessful() {

	final Card card = new Card("", "", new Date());
	final ResponseEntity<PostCardResponse> body = restTemplate.postForEntity("/api/cards/", card,
		PostCardResponse.class);

	assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void testCsvUpload() {

	final Card card = new Card("", "", new Date());
	final ResponseEntity<PostCardResponse> body = restTemplate.postForEntity("/api/cards/upload", card,
		PostCardResponse.class);

	assertThat(body).isEqualTo("Hello World");
    }
    
}