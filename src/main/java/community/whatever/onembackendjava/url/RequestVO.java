package community.whatever.onembackendjava.url;

public class RequestVO {
    private String url ;
    private String key ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RequestVO{" +
                "url='" + url + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
