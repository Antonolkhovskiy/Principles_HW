import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 11.12.2017.
 */
public class JsonIO {
    public void writeJson(String filmName, JSONObject jsonObject){
        if(jsonObject == null){
            try(FileWriter file = new FileWriter("movie_database.json")){

                file.write(jsonObject.toJSONString());
                file.flush();
            }
            catch (IOException e ){e.printStackTrace();}
        }else{
            JSONObject oldObject = readJson();
            oldObject.put(filmName, jsonObject);
            try(FileWriter file = new FileWriter("movie_database.json")){

                file.write(oldObject.toJSONString());
                file.flush();
            }
            catch (IOException e ){e.printStackTrace();}
        }

    }

    public JSONObject readJson(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            jsonObject  = (JSONObject)jsonParser.parse(new FileReader("movie_database.json"));
        }
        catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        catch (ParseException e ){e.printStackTrace();}
        catch (Exception e){e.printStackTrace();}

        return jsonObject;
    }


    public void saveChanges(Movie movie){
        JSONObject jsonObject = readJson();
        JSONObject newObject = new JSONObject();
        Map<String,String> params = movie.getParams().getParams();

        for (Map.Entry entry: params.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            newObject.put(key, value);
            System.out.print(key + " " + value + "\n");
        }

        try(FileWriter file = new FileWriter("movie_database.json")){
            jsonObject.remove(movie.getMovieName());
            jsonObject.put(movie.getMovieName(), newObject);
            file.write(jsonObject.toJSONString());
            file.flush();
        }
        catch (IOException e ){e.printStackTrace();}
    }




}
