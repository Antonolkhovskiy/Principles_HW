import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 10.12.2017.
 */
public class Parameters {

    Map<String, String> params = new HashMap<>();


    /*public Parameters(String releaseDate, String movieType, String quality, String dubbing, String subtitles, String expectationRating) {
        params.put("Release Date", releaseDate);
        params.put("Movie Type", movieType);
        params.put("Quality", quality);
        params.put("Dubbing", dubbing);
        params.put("Subtitles", subtitles);
        params.put("Expectation Rating", expectationRating);
    }*/

    public Map<String, String> getParams() {
        return params;
    }

    public void setOneParam(String param, String value){
        params.put(param, value);
    }

    public void setParams(Map<String, String> params){
        this.params = params;
    }

    public List<String> getParametersName(){
        List<String> result = new ArrayList<>();
        for (Map.Entry entry: params.entrySet()) {
            String key = (String) entry.getKey();
            result.add(key);
        }
        return result;
    }

    public List<String> getParametersValue(){
        List<String> result = new ArrayList<>();
        for (Map.Entry entry: params.entrySet()) {
            String value = (String) entry.getValue();
            result.add(value);
        }
        return result;
    }

/*    public Movie updateParam(String key, String value){
        params.put()

    }*/

    public String allToString(){
        String result = "";
        Map<String,String> paramsToString = getParams();
        for (Map.Entry entry: paramsToString.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            result += key + ": '" + value + "'\n";
        }

        return result;
    }
}
