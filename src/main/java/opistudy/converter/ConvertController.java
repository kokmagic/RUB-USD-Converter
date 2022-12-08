package opistudy.converter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.tree.VariableHeightLayoutCache;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ConvertController {
    @GetMapping("/convert")
    @ResponseBody
    public ResponseEntity<?> convert(@RequestParam String currencyFrom,
                       @RequestParam String currencyTo,
                       @RequestParam double value ) {

        if (!List.of("RUB","USD").contains(currencyFrom)) {
            return new ResponseEntity<>("Вы ввели некорректные названия валют.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (!List.of("RUB","USD").contains(currencyTo)) {
            return new ResponseEntity<>("Вы ввели некорректные названия валют.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (currencyTo.equals(currencyFrom)){
            return new ResponseEntity<>("Из одной валюты в ту же переводить не стоит.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(Objects.equals(currencyFrom, "USD") ? value * 62 : value * 0.016, HttpStatus.OK);

    }
}
