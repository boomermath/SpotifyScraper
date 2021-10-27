public class Image {
    private int height;
    private int width;
    private String url;

    public Image(int height, int width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public String url() {
        return url;
    }
}
