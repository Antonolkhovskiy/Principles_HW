/**
 * Created by Anton on 10.12.2017.
 */
public class Movie {
    private Parameters params;
    private String movieName;

    public Movie(String movieName, Parameters params) {
        this.params = params;
        this.movieName = movieName;
    }

    public Parameters getParams() {
        return params;
    }

    public void setParams(Parameters params) {
        this.params = params;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String toString(){
        String result = movieName + ":\n";
        result += params.allToString();
       return result;
    }
}
