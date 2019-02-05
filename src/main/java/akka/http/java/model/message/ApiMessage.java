package akka.http.java.model.message;

import java.util.Map;


/**
 * ApiMessage - abstract class for ApiRequest body models
 */
abstract class ApiMessage {

    protected Map<String, String> headers;

}
