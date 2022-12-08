package opistudy.converter;

import lombok.Getter;

@Getter
public class Currency {
    public Quotes quotes;
    public String source;
    public Boolean success;
    public int timestamp;
}
