package PackageModel;

/**
 * Created by mohamedHedhili on 24/11/2016.
 */
public class User {
    private int  id ;
    private  String nom  ;
    private String login ;
    private String  password  ;

    public  User (int  id  ,  String  nom  , String  login  , String  password )
    {
        this.id=id  ;
        this.nom  =  nom  ;
        this.login =  login  ;
        this.password =  password;
    }
    public  User ( String  nom  , String  login  , String  password )
    {

        this.nom  =  nom  ;
        this.login =  login  ;
        this.password =  password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
