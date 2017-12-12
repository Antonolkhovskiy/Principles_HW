import org.json.simple.JSONObject;


import java.util.*;

/**
 * Created by Anton on 10.12.2017.
 */
public class database {
    private Movie mov;
    private  List<String> params;
    private static Scanner scanner = new Scanner(System.in);


    public void addMov(Movie mov) {
        this.mov = mov;
    }

    public Movie getMov() {
        return mov;
    }

    public void setMov(Movie mov) {
        this.mov = mov;
    }

    public static void main(String[] args){
        operation();

    }
    public static void operation(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome!\n" +
                "What would you like to do?\n" +
                "1 - find a Movie by its name\n" +
                "2 - find movies by Parameters\n" +
                "3 - add a Movie\n" +
                "4 - remove a Movie\n" +
                "5 - update a Movie\n" +
                "6 - leave a comment\n" +
                "7 - exit\n");

        String operation = scanner.next();

        Search search = new Search();




        switch (operation){
            case"1":{
                System.out.print("Please type the film name\n");
                scanner.nextLine();
                String filmName = scanner.nextLine();
                Movie mov = search.searchByName(filmName.toLowerCase());
                System.out.println(mov.getMovieName() + "\n" + mov.getParams().allToString());
                break;
            }
            case"2":{
                searchByParam();
                break;
            }
            case"3":{
                addMovie();
                break;
            }
            case"4":{
                break;
            }
            case"5":{
                updateMovie();
                break;
            }
            case"6":{
                break;
            }
            default:{
                System.out.print("Please enter correct operation");
            }


        }
    }

    public static void updateMovie(){
        System.out.print("How Would Like to Find a Movie\n" +
                "1 - Search by Movie's name\n" +
                "2 - Search by Parameters\n" +
                "Please Enter Only Numbers(1/2)\n");
        String searchType = scanner.next();
        switch (searchType){
            case"1":{
                updateByName();
                break;
            }
            case"2":{
                updateByParam();
                break;
            }
            default:{
                System.out.println("Please Type Number of Operation");
                updateMovie();
            }
        }
    }


    public static void updateByParam(){
        Search search = new Search();

        System.out.println("Enter Parameter to Update");
        String param = scanner.next();
        List<Movie> movies = search.searchByParam(param);

        int index = 1;
        for (Iterator<Movie> i = movies.iterator(); i.hasNext();){
            String item = i.next().getMovieName();
            System.out.print(index + " - " + item + "\n");
            index++;
        }

        System.out.println("Choose Movie to Update");
        String movieIndex = scanner.next();

        Movie filmToUpdate = movies.get(Integer.parseInt(movieIndex) - 1);

        System.out.println(filmToUpdate.getParams());

        System.out.println("Enter Value for the New Parameter");
        String value = scanner.next();




    }

    public static void updateByName(){
        JsonIO jsonIO = new JsonIO();
        System.out.println("Enter Movie's Name");
        scanner.nextLine();
        String filmName = scanner.nextLine();

        Search search = new Search();

        Movie movie = search.searchByName(filmName);

        List<String> parameters = movie.getParams().getParametersName();

        System.out.println("Choose Parameter to Change");
        int index = 1;
        for (Iterator<String> i = parameters.iterator(); i.hasNext();){
            String item = i.next();
            System.out.print(index + " - " + item + "\n");
            index++;
        }
        String paramToChange = scanner.next();
        System.out.println("Type New Value for the " + parameters.get(Integer.valueOf(paramToChange) - 1));
        scanner.nextLine();
        String value = scanner.nextLine();

        movie.getParams().setOneParam(parameters.get(Integer.valueOf(paramToChange) - 1), value);
        System.out.println(movie.toString());
        jsonIO.saveChanges(movie);

    }

    public static void searchByParam(){
        Search search = new Search();
        System.out.println("Enter Movie's parameter");
        String param = scanner.next();
        System.out.println("Enter the parameter");
        String value = scanner.next().toLowerCase();

        search.searchByParam(param, value);
    }


    public static void addMovie(){
        JsonIO jsonIO = new JsonIO();

        JSONObject jsonObject = new JSONObject();
        List<String> parameters = new ArrayList<String>();
        Map<String, String> newParameters = new HashMap<>();

        parameters.addAll(Arrays.asList("Release Date","Movie Type","Quality","Dubbing", "Subtitles","Expectation Rating","After Watched Rating"));

        String param;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Film name\n");

        String filmName = scanner.nextLine();


        for (Iterator<String> i = parameters.iterator(); i.hasNext();){
            String item = i.next();
            System.out.print(item + "\n");
            param = scanner.next();
            newParameters.put(item, param.toLowerCase());


        }


        for (Map.Entry entry: newParameters.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            jsonObject.put(key, value);
            System.out.print(key + " " + value + "\n");
        }


        jsonIO.writeJson(filmName, jsonObject);
    }
    public List<String> getParams() {
        return params;
    }

    public static void setParams(List<String> params) {
        params = params;
    }
}
