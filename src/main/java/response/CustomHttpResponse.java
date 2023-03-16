package response;

import handler.GsonTool;
import java.util.Map;
import java.util.Map.Entry;

public class CustomHttpResponse {
  public final Map<String,String> headers;
  public final String status;
  public final String version;
  public final String body;

  public CustomHttpResponse(Map<String, String> headers, String status, String version,
      String body) {
    this.headers = headers;
    this.status = status;
    this.version = version;
    this.body = body;
  }

  // TODO fill this out
  public String toString(){
    return null;
  }
}
