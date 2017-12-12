import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 10.12.2017.
 */
public class Search {

    public Movie searchByName(String filmName) {
        Movie searchedMovie = null;
        JsonIO jsonIO = new JsonIO();
        JSONObject jsonObject = jsonIO.readJson();

        List<String> filmNames = new ArrayList<>();

        for (Object keyObject : jsonObject.keySet())
        {
            String key = (String)keyObject;
            filmNames.add(key);
        }

        Iterator<String> name = filmNames.iterator();
        boolean notFound = true;
        String movieName = null;
        while(name.hasNext()){
            if(name.next().toString().equals(filmName)){
                //System.out.println(jsonObject.get(filmName));
                Parameters params = new Parameters();
                params.setParams((Map)jsonObject.get(filmName));
                searchedMovie = new Movie(filmName, params);
                notFound = false;
                break;
            }
        }

        if(notFound){
            System.out.println("There is no such Movie");
            return null;
        }

        return searchedMovie;
    }

    public List<Movie> searchByParam(String param, String value) {
        JsonIO jsonIO = new JsonIO();
        JSONObject jsonObject = jsonIO.readJson();
        List<JSONObject> films = new ArrayList<>();
        List<String> filmNames = new ArrayList<>();

        for (Object keyObject : jsonObject.keySet())
        {
            String key = (String)keyObject;
            filmNames.add(key);
        }

        List<Movie> result = new ArrayList<>();
        Iterator<String> filmName = filmNames.iterator();
        boolean notFound = true;

        while (filmName.hasNext()){

            String name = filmName.next();

            JSONObject film = (JSONObject)jsonObject.get(name);
            String parameter = film.getOrDefault(param, "noSuchParameter").toString();
            if( parameter.equals("noSuchParameter")){
            }else{
                if (parameter.equals(value)) {
                    System.out.println(film);
                    Movie movie = new Movie(name, (Parameters)film.get(name));
                    result.add(movie);
                    notFound = false;
                }
            }


        }
        if(notFound){
            System.out.println("There is no such Movie");
            return null;
        }
        return result;
    }

    public List<Movie> searchByParam(String param) {
        JsonIO jsonIO = new JsonIO();
        JSONObject jsonObject = jsonIO.readJson();
        List<JSONObject> films = new ArrayList<>();
        List<String> filmNames = new ArrayList<>();

        for (Object keyObject : jsonObject.keySet())
        {
            String key = (String)keyObject;
            filmNames.add(key);
        }

        List<Movie> result = new ArrayList<>();
        Iterator<String> filmName = filmNames.iterator();
        boolean notFound = true;

        while (filmName.hasNext()){

            String name = filmName.next();

            JSONObject film = (JSONObject)jsonObject.get(name);
            String parameter = film.getOrDefault(param, "noSuchParameter").toString();
            if( parameter.equals("noSuchParameter")){
            }else{
                    Parameters params = new Parameters();
                    params.setParams((Map)jsonObject.get(name));
                    Movie movie = new Movie(name, params);
                    System.out.println(jsonObject.get(name));
                    result.add(movie);
                    notFound = false;

            }


        }
        if(notFound){
            System.out.println("There is no such Movie");
            return null;
        }
        return result;
    }
}
