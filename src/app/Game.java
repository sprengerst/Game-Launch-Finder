package app;

public class Game {

    private String path;
    private String name;
    private String imagePath;


    public Game(String path, String name) {
        this.path = path;
        this.name = name;
        this.imagePath = "";
    }


    public Game(String path, String name, String imagePath) {
        this.path = path;
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
