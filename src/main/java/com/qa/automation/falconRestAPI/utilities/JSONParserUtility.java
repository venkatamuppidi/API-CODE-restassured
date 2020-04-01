/**
 * 
 */
package com.qa.automation.falconRestAPI.utilities;

/**
 * @author venkata.muppidi
 *
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.testng.Reporter;

import com.atmecs.falcon.automation.util.logging.LogManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JSONParserUtility {
static Logger logger = LogManager.getInstance().getLogger(JSONParserUtility.class);

    // Get JSONObject by Passing File Path
public JSONObject getJettisonJSONObjectByFile(String filePath) throws JSONException, IOException, ParseException {
return new JSONObject(new JSONTokener(new JSONParser().parse(new FileReader(filePath)).toString()));
}

public void writeAsPrettyJson(JsonObject contractObject, String filepath) {

try (Writer writer = new FileWriter(filepath)) {
Gson gson = new GsonBuilder().setPrettyPrinting().create();
gson.toJson(contractObject, writer);
} catch (IOException ioException) {
Reporter.log("Unable to write data to the specified file: " + ioException.getMessage());
logger.debug("Unable to write data to the specified file {}", ioException.getMessage());
} catch (Exception exception) {
Reporter.log("Unexpected exception occur: " + exception.getMessage());
logger.debug("Unexpected exception occur: {}", exception.getMessage());
throw exception;
}
}

    // Get JSONObject by Passing JSON String
    static JSONObject getJettisonJSONObjectByString (String jsonString) throws JSONException, IOException, ParseException {
        return new JSONObject(new JSONTokener(new JSONParser().parse(jsonString).toString()));
    }

    // Get JSONArray by Passing File Path
    public static JSONArray getJettisonJSONArrayByFile(String filePath) throws JSONException, IOException, ParseException {
        return new JSONArray(new JSONTokener(new JSONParser().parse(new FileReader(filePath)).toString()));
    }

    // Get JSONArray by Passing JSON String
    static JSONArray getJettisonJSONArrayByString (String jsonString) throws JSONException, IOException, ParseException {
        return new JSONArray(new JSONTokener(new JSONParser().parse(jsonString).toString()));
    }

    // Parse JSONObject to Print all JSONElements
    @SuppressWarnings("unchecked")
static void parseJSONObject(JSONObject jsonObject) throws JSONException, ParseException {
Iterator<String> jsonObjectIterator = jsonObject.keys();

while (jsonObjectIterator.hasNext()) {
String jsonObjectKey = jsonObjectIterator.next();
Object jsonObjectKeyValue = jsonObject.get(jsonObjectKey);

if (jsonObjectKeyValue.toString().indexOf("[") == 0) {
JSONArray jsonArray = new JSONArray(jsonObjectKeyValue.toString());
parseJSONArray(jsonArray);
}
}

}

    // Parse JSONArray to Print all JSONElements
static void parseJSONArray(JSONArray jsonArray) throws ParseException, JSONException {
for (int jsonElementCount = 0; jsonElementCount < jsonArray.length(); jsonElementCount++) {
if (jsonArray.get(jsonElementCount).toString().indexOf("{") == 0) {
JSONObject jsonObject = new JSONObject(jsonArray.get(jsonElementCount).toString());
parseJSONObject(jsonObject);
}
}
}
   
    // Check Object within the JSONObject, if JSONObject formed by JSONTokener
public static boolean checkObjectInJSON(JSONObject jsonObject, Object object) {
return jsonObject.toString().contains(object.toString());
}

 /*   // Return JSONObject Keys as Comma Separated String
    @SuppressWarnings("rawtypes")
public static String getJSONKeyString(JSONObject jsonObject) {
List<String> keyList = new ArrayList<String>();
Iterator itr = jsonObject.keys();

while (itr.hasNext())
keyList.add(itr.next().toString());

return String.join(",", keyList);
}*/
 
}

The information in this message may be proprietary and/or confidential, and protected from disclosure. If the reader of this message is not the intended recipient, you are hereby notified that any dissemination, distribution or copying of this communication is strictly prohibited. If you have received this communication in error, please notify ATMECS and delete it from your computer.
