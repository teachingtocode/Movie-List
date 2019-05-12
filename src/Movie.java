public class Movie implements Comparable<Movie> {

    private String title;
    private String actor;
    private String year;
    private String genre;

    Movie(String title, String actor, String year, String genre) {
        this.title = title;
        this.actor = actor;
        this.year = year;
        this.genre = genre;

    }

    String getTitle() {
        return title;
    }

    String getActor() {
        return actor;
    }

    String getYear() {return year;}

    String getGenre() { return genre; }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie other = (Movie)o;
        return this.getTitle().compareToIgnoreCase(other.getTitle()) != 0;
    }

    @Override
    public int compareTo(Movie o) {
        return this.getTitle().compareToIgnoreCase(o.getTitle());
    }
}
