package RestAssuredAutomation;

public class Pojo {

    private String message;
    private int userId;
    private String title;

    public Pojo(String message, int Id, String title) {
        this.message = message;
        this.userId = Id;
        this.title = title;
    }

    public void putMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
    public void putID(int Id){
        this.userId = Id;
    }

    public int getId(){
        return userId;
    }
    public void puttitle(String title){
        this.title = title;
    }

    public String gettitle() {
        return title;
    }

    }
