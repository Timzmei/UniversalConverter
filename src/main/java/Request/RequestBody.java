package Request;

import lombok.Data;

@Data
public class RequestBody {


    private String from;
    private String to;

    public RequestBody(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
