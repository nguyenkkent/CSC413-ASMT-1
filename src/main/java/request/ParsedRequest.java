package request;

import java.util.HashMap;
import java.util.Map;

public class ParsedRequest {

  private String path;
  private Map<String,String> queryMap = new HashMap<>();
  private Map<String,String> headerMap = new HashMap<>();
  private String method;
  private String body;
  private String version;

  public String getQueryParam(String key){
    return queryMap.get(key);
  }

  public void setQueryParam(String key, String value){
    this.queryMap.put(key, value);
  }

  public void setHeaderParam(String key, String value){
    this.headerMap.put(key, value);
  }

  public String getHeaderParam(String key){
    return headerMap.get(key);
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getPath(){
    return path;
  }

  public String getMethod(){
    return method;
  }

  public String getBody(){
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setVersion(String version){
    this.version = version;
  }

  public String getVersion(){
    return version;
  }
}
