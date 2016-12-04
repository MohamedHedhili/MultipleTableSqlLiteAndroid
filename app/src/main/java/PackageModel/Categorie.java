package PackageModel;

/**
 * Created by mohamedHedhili on 24/11/2016.
 */
public class Categorie {

    private int  id ;
    private  String libelle ;
 ;

    public  Categorie (int  id  ,  String  libelle   )
    {
        this.id=id  ;
        this.libelle  =  libelle  ;

    }
    public Categorie ()
    {}
    public  Categorie (  String  libelle   )
    {
        this.libelle  =  libelle  ;

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
}
