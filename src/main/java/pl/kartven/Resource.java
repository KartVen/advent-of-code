package pl.kartven;

public class Resource {
    private static final String URL = "src/main/resources/";

    public static String getPath(String name){
        return URL + name;
    }

    private Resource() {
    }
}
