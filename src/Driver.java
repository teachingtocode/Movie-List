
// PASSWORD: 123456  ENCRYPTED  3 ATTEMPTS
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Driver {
    private static boolean isValid;
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Movies movies = new Movies();
        String username;
        String password = encryptPassword("123456");
        boolean tryAgain = true;
        movies.fillArray();

do {
        System.out.println("Main Menu.\n Enter A for admin, C for customer or Q to quit.");
        username = stdIn.next().toLowerCase();
        if (!username.equals("q")) {


                switch (username) {

                    case "a":
                        userCaseA(stdIn, password, movies);
                        break;
                    case "c":
                        userCaseC(stdIn, movies);
                        break;
                    default:
                        System.out.println("Entry invalid.");
                }


        } else {
            System.out.println("Goodbye!");
            tryAgain = false;

        } } while (tryAgain);
    }

    private static void sortFound(Scanner stdIn, Movies foundMovies) {
        if (foundMovies.getSize() != 0) {
            int sortChoice;

            System.out.println("1. Quick Sort\n" +
                    "2. Bubble Sort");
            sortChoice = 0;
            isValid = false;
            sortChoice = getSortChoice(stdIn, sortChoice);
            long time = foundMovies.sortMovies(sortChoice);
            isValid = false;
            System.out.println("Enter 1 for A-Z or 2 for Z-A.");
            while (!isValid) {
                while (!stdIn.hasNextInt()) {
                    System.out.println("Enter 1 for A-Z or 2 for Z-A.");
                    stdIn.next();
                }
                sortChoice = stdIn.nextInt();
                if (sortChoice != 1 && sortChoice != 2) {
                    System.out.println("Select either 1 or 2.");
                    continue;
                }
                isValid = true;
            }
            if (sortChoice == 1) {
                System.out.println(foundMovies.toString());
                System.out.println("List sorted in " + time + " nano seconds.");
            } else {
                System.out.println(foundMovies.reverseMovies().toString());
                System.out.println("List sorted in " + time + " nano seconds.");

            }
        }
    }

    private static int getSortChoice(Scanner stdIn, int sortChoice) {
        while(!isValid) {
            while (!stdIn.hasNextInt()) {
                System.out.println("Number not entered. Enter 1 or 2.");
                stdIn.next();
            }
            sortChoice = stdIn.nextInt();
            if (sortChoice != 1 && sortChoice != 2) {
                System.out.println("Select either 1 or 2.");
                continue;
            }
            isValid = true;
        }
        return sortChoice;
    }

    private static String encryptPassword(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashText = new StringBuilder(no.toString(16));

            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            return hashText.toString();
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private static void userCaseA(Scanner stdIn, String password, Movies movies){
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Password");
            String typedPassword = stdIn.next();

            if (encryptPassword(typedPassword).equals(password)) {

                System.out.println("1. Display All");
                System.out.println("2. Search");
                System.out.println("3. Add Movies");
                System.out.println("4. Remove Movies");
                System.out.println("5. Sort");
                System.out.println("6. Main Menu");

                while (!stdIn.hasNextInt()) {
                    System.out.println("Invalid Entry. Select either from one of the above options.");
                    stdIn.next();
                }
                int searchChoice = stdIn.nextInt();

                switch (searchChoice) {

                    case 1:
                        System.out.println(movies.toString());
                        break;

                    case 2:
                        case2(stdIn, movies);
                        break;


                    case 3:
                        movies.addMovies();
                        System.out.println("Updated List");
                        System.out.println(movies.toString());
                        break;

                    case 4:

                        System.out.println("Select number associated with movie to be removed");
                        int deleteMovie = stdIn.nextInt();
                        movies.getMovieList().remove(deleteMovie - 1);
                        System.out.println("Updated List:");
                        System.out.println(movies.toString());
                        break;

                    case 5:
                        System.out.println("1. Quick Sort\n" +
                                "2. Bubble Sort");
                        int sortChoice = 0;
                        isValid = false;
                        sortChoice = getSortChoice(stdIn, sortChoice);
                        long time = movies.sortMovies(sortChoice);
                        System.out.println(movies.toString());
                        System.out.println("List sorted in " + time + " nano seconds.");

                        break;
                    case 6:
                        i = 3;
                        break;
                    default:
                        System.out.println("Invalid selection.");


                }
            } else {
                System.out.println("Invalid Password! " + (2 - i) + " attempt(s) remaining.");
            }
        }
    }
    private static void userCaseC(Scanner stdIn, Movies movies){
        System.out.println("1. Display All");
        System.out.println("2. Search");
        System.out.println("3. Sort");
        System.out.println("4. Main Menu");
        isValid=false;
        int guestChoice = 0;
        while(!isValid) {
            while (!stdIn.hasNextInt()) {
                System.out.println("Number not entered. Enter 1, 2, 3, or 4");
                stdIn.next();
            }
            guestChoice = stdIn.nextInt();
            if (guestChoice != 1 && guestChoice != 2 && guestChoice !=3 && guestChoice != 4) {
                System.out.println("Select either 1, 2, 3, or 4");
                continue;
            }
            isValid = true;
        }
        switch (guestChoice) {
            case 1:
                System.out.println(movies.toString());
                break;

            case 2:
                case2(stdIn, movies);
                break;

            case 3:
                System.out.println("1. Quick Sort\n" +
                        "2. Bubble Sort");
                int sortChoice = 0;
                isValid = false;
                sortChoice = getSortChoice(stdIn, sortChoice);
                long time = movies.sortMovies(sortChoice);
                System.out.println(movies.toString());
                System.out.println("List sorted in " + time + " nano seconds.");

                break;
            case 4:
                break;
            default:
                System.out.println("Invalid Entry");
        }
    }
    private static void case2(Scanner stdIn, Movies movies){
        System.out.println("Search by::\n 1. Title\n 2.Lead Actor/Actress\n 3.Release Year\n 4. Genre");
        isValid = false;
        int searchType = 0;
        while(!isValid) {
            while (!stdIn.hasNextInt()) {
                System.out.println("Number not entered. Enter 1, 2, or 3.");
                stdIn.next();
            }
            searchType = stdIn.nextInt();
            if (searchType != 1 && searchType != 2 && searchType !=3 && searchType != 4) {
                System.out.println("Select either 1, 2, or 3.");
                continue;
            }
            isValid = true;
        }
        stdIn.nextLine();
        Movies foundMovies;
        if (searchType==1) {
            System.out.println("Enter a movie title to search for.");
            String titleSearch = stdIn.nextLine().toLowerCase();
            foundMovies = movies.searchMoviesByTitle(titleSearch);
            sortFound(stdIn, foundMovies);

        }
        else if (searchType==2){
            System.out.println("Enter an actor or actress to search for");
            String actorSearch = stdIn.nextLine().toLowerCase();
            foundMovies = movies.searchMoviesByActor(actorSearch);
            sortFound(stdIn, foundMovies);
        }
        else if (searchType==3){
            System.out.println("Enter a release year to search.");
            String yearSearch = stdIn.next().toLowerCase();
            foundMovies = movies.searchMoviesByYear(yearSearch);
            sortFound(stdIn, foundMovies);
        }
        else{
            System.out.println("Enter a genre to search for.");
            String genreSearch = stdIn.next();
            foundMovies = movies.searchMoviesByGenre(genreSearch);
            sortFound(stdIn, foundMovies);
        }
    }
}