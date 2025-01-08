package graph;
import java.util.Date;

public class Message {

    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

public Message(byte[] data) {
    this.data = data;
    this.asText = new String(data);
    this.asDouble = this.tryParseDouble(asText);
    this.date = new Date();
}

public Message(String asText) {
    this.asText = asText;
    this.asDouble = tryParseDouble(this.asText);
    this.date = new Date();
    this.data = asText.getBytes();
}

public Message(double asDouble) {
    this.asDouble = asDouble;
    this.date = new Date();
    this.asText = Double.toString(asDouble);
    this.data = this.asText.getBytes();
}

private double tryParseDouble(String value) {
    try {
        return Double.parseDouble(value);
    } catch (NumberFormatException e) {
        return Double.NaN;
    }
}


}
