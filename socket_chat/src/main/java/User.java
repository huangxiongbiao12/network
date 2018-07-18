public class User {

    private String name;
    private String ip;

    public  User(String name,String ip){
        this.name = name;
        this.ip = ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}
