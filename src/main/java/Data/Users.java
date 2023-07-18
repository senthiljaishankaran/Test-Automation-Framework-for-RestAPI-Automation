package Data;
// POJO Class (Plain Old Java Object)
public class Users {
    String name;
    String job;
    String id;
    String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Users(){

    }
    public Users(String name,String job){
        this.name=name;
        this.job=job;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setJob(String job){
        this.job=job;
    }
    public String getName(){
        return name;
    }
    public String getJob(){
        return job;
    }
}
