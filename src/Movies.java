import java.util.ArrayList;
import java.util.Scanner;
class Movies {


    private int size = 0;


    private static Scanner stdIn = new Scanner(System.in);

    private ArrayList <Movie> movieList = new ArrayList<>();

    void fillArray() {
        movieList.add(new Movie("Avengers","Robert Downey Jr", "2012", "Action"));
        movieList.add(new Movie("Incredibles","Craig Nelson","2004","Animation"));
        movieList.add(new Movie("Godfather","Al Pacino","1972","Crime"));
        movieList.add(new Movie("Inception","Leonardo DiCaprio","2010","Action"));
        movieList.add(new Movie("Matrix","Keanu Reeves", "1999","Action"));
        movieList.add(new Movie("Interstellar","Matthew McConaughey", "2014","Drama"));
        movieList.add(new Movie("Se7en","Brad Pitt", "1995","Crime"));
        movieList.add(new Movie("Prestige","Christian Bale", "2006","Drama"));
        movieList.add(new Movie("Departed","Leonardo DiCaprio", "2006","Crime"));
        movieList.add(new Movie("Memento","Guy Pierce", "2000","Drama"));
        movieList.add(new Movie("Pianist","Adrien Brody", "2002","Drama"));
        movieList.add(new Movie("Gladiator","Russel Crowe", "2000","Action"));
        movieList.add(new Movie("Terminator","Arnold Schwarzenegger", "1984","Action"));
        movieList.add(new Movie("Superman","Christopher Reeve", "1978","Action"));
        movieList.add(new Movie("Alien","Sigourney Weaver", "1978","Horror"));
        movieList.add(new Movie("Braveheart","Mel Gibson", "1995","Action"));
        movieList.add(new Movie("Scarface","Al Pacino", "1983","Crime"));
        size = 17;

    }

    void addMovies() {

        System.out.println("Title:");
        String newTitle = stdIn.nextLine();
        System.out.println("Lead Actor/Actress:");
        String newActor = stdIn.nextLine();
        System.out.println("Year:");
        String newYear = stdIn.nextLine();
        System.out.println("Genre:");
        String newGenre = stdIn.nextLine();
        addMovies(newTitle, newActor, newYear, newGenre);
    }

    private void addMovies(Movie newMovie){
        movieList.add(newMovie);
        size++;
    }

    private void addMovies(String title, String actor, String year, String genre) {

        movieList.add(new Movie(title, actor, year, genre));
        size++;
    }


    int getSize(){
        return size;
    }

    ArrayList<Movie> getMovieList() {
        return movieList;
    }

    Movies searchMoviesByTitle(String titleSearch) {
        Movies foundMovies = new Movies();
        boolean movieFound = false;
        for (int i = 0; i < size; i++) {
            Movie currentMovie = getMovieList().get(i);
            if (currentMovie.getTitle().toLowerCase().equals(titleSearch)) {
                movieFound = true;
                foundMovies.addMovies(currentMovie.getTitle(), currentMovie.getActor(), currentMovie.getYear(),
                        currentMovie.getGenre());
            }
        }
        movieFound(movieFound);
        return foundMovies;
    }

    Movies searchMoviesByActor(String actorSearch) {
        Movies foundMovies = new Movies();
        boolean movieFound = false;
        for (int i = 0; i < size; i++) {
            Movie currentMovie = getMovieList().get(i);
            if (currentMovie.getActor().toLowerCase().equals(actorSearch)) {
                movieFound = true;
                foundMovies.addMovies(currentMovie.getTitle(), currentMovie.getActor(), currentMovie.getYear(),
                        currentMovie.getGenre());
            }
        }
        movieFound(movieFound);
        return foundMovies;

    }

    Movies searchMoviesByYear(String yearSearch) {
        Movies foundMovies = new Movies();
        boolean movieFound = false;
        for (int i = 0; i < size; i++) {
            Movie currentMovie = getMovieList().get(i);
            if (currentMovie.getYear().toLowerCase().equals(yearSearch)) {
                movieFound = true;
                foundMovies.addMovies(currentMovie.getTitle(), currentMovie.getActor(), currentMovie.getYear(),
                        currentMovie.getGenre());
            }
        }
        movieFound(movieFound);
        return foundMovies;
    }

    Movies searchMoviesByGenre(String genreSearch) {
        Movies foundMovies = new Movies();
        boolean movieFound = false;
        for (int i = 0; i < size; i++) {
            Movie currentMovie = getMovieList().get(i);
            if (currentMovie.getGenre().toLowerCase().equals(genreSearch)) {
                movieFound = true;
                foundMovies.addMovies(currentMovie.getTitle(), currentMovie.getActor(), currentMovie.getYear(),
                        currentMovie.getGenre());
            }
        }
        movieFound(movieFound);
        return foundMovies;
    }

    private void movieFound(boolean movieFound) {
        if (movieFound) {
            System.out.println("Movie(s) found! ");

        }
        if (!movieFound) {
            System.out.println("Movie not found");
        }
    }

    long sortMovies(int i){
        long startTime;
        if (i == 1) {
            startTime = System.nanoTime();
            quickSort(this.movieList, 0, size-1);
            return System.nanoTime() - startTime;
        }
        else {
            startTime = System.nanoTime();
            bubbleSort(this.movieList, size);
            return  System.nanoTime() - startTime;
        }
    }
    private int partition(ArrayList<Movie> arr, int low, int high)
    {

        Movie pivot = arr.get(high);
        int i = (low-1);
        for (int j=low; j<high; j++)
        {

            if (arr.get(j).compareTo(pivot) < 0)
            {

                i++;


                swap(i,j);
            }

        }


        Movie temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);

        return i+1;
    }


    private void quickSort(ArrayList<Movie> arr, int low, int high)
    {

        if (low < high)
        {

            int pi = partition(arr, low, high);


            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    private void bubbleSort(ArrayList<Movie> arr, int n)
    {
        int i, j;
        for (i = 0; i < n-1; i++)


            for (j = 0; j < n-i-1; j++)
                if (arr.get(j).compareTo(arr.get(j+1)) > 0)
                    swap(j, j+1);
    }
    private void swap(int i, int j){
        Movie temp = movieList.get(i);
        movieList.set(i, movieList.get(j));
        movieList.set(j, temp);
    }

    Movies reverseMovies()
    {
        Movies reverseMovies = new Movies();

        for (int i = size - 1; i >= 0; i--) {


            reverseMovies.addMovies(movieList.get(i));
        }

        return reverseMovies;
    }

    public String toString(){
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            returnString.append(i+1).append("\n");
            returnString.append("Movie title: ").append(movieList.get(i).getTitle()).append("\n");
            returnString.append("Lead Actor: ").append(movieList.get(i).getActor()).append("\n");
            returnString.append("Release Year: ").append(movieList.get(i).getYear()).append("\n");
            returnString.append("Genre: ").append(movieList.get(i).getGenre()).append("\n");
        }
        return returnString.toString();
    }
}