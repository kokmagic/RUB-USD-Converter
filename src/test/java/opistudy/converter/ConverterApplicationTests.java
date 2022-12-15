package opistudy.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	final RestTemplate restTemplate = new RestTemplate();
	private double getExchangeRate() {
		final String url = "https://api.apilayer.com/currency_data/live?source=USD&currencies=RUB";
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", "trwyXVd7Rk24rVUGC5TVUaEUe1gzVUhV");
		HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
		ResponseEntity<Currency> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Currency.class, 1);
		Currency currency = response.getBody();

		return currency.getQuotes().getUSDRUB();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testRUBToUSD() {
		double exchangeRate = getExchangeRate();
		ResponseEntity<?> response =
				testRestTemplate.getForEntity("/api/convert/?currencyFrom=RUB&currencyTo=USD&value=1", Double.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(1 / exchangeRate));
	}

	@Test
	void testUSDToRUB() {
		double exchangeRate = getExchangeRate();
		ResponseEntity<?> response =
				testRestTemplate.getForEntity("/api/convert/?currencyFrom=USD&currencyTo=RUB&value=1", Double.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is(exchangeRate));
	}

}
