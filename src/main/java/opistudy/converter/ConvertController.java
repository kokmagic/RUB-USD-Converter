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
        return new ResponseEntity<>(value * 0.016, HttpStatus.OK);

    }
}
