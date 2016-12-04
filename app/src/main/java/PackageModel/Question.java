package PackageModel;

/**
 * Created by mohamedHedhili on 25/11/2016.
 */
public class Question {
    private int id ;
    private String  libelle  ;
    private int  idCategorie ;

    public  Question(String libelle)
    {this.libelle=libelle;}
    public  Question(String libelle, int idCategorie)
    {this.libelle=libelle;
        this.idCategorie =idCategorie;
    }
    public  Question(int id ,String libelle, int idCategorie)
    {this.id=id ;
        this.libelle=libelle;
        this.idCategorie =idCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
}

