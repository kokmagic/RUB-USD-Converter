package opistudy.converter;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ConvertController {

    final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/convert")
    @ResponseBody
    public ResponseEntity<?> convert(@RequestParam String currencyFrom,
                                     @RequestParam String currencyTo,
                                     @RequestParam double value) {

        final String url = "https://api.apilayer.com/currency_data/live?source=USD&currencies=RUB";
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "trwyXVd7Rk24rVUGC5TVUaEUe1gzVUhV");
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        ResponseEntity<Currency> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Currency.class, 1);
        Currency currency = response.getBody();

        double exchangeRate = currency.getQuotes().getUSDRUB();


        if (!List.of("RUB", "USD").contains(currencyFrom)) {
            return new ResponseEntity<>("Вы ввели некорректные названия валют.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!List.of("RUB", "USD").contains(currencyTo)) {
            return new ResponseEntity<>("Вы ввели некорректные названия валют.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (currencyTo.equals(currencyFrom)) {
            return new ResponseEntity<>("Из одной валюты в ту же переводить не стоит.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(Objects.equals(currencyFrom, "USD") ? value * exchangeRate : value / exchangeRate, HttpStatus.OK);
    }
}
