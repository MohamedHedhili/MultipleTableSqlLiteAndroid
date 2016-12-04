package PackageModel;

/**
 * Created by mohamedHedhili on 26/11/2016.
 */
public class Reponse {
    private int  id  ;
    private String  libelle  ;
    private int  valid ;
    private int  idQuestion ;
    public  Reponse  (String libelle ,int valid , int  idQuestion)
    {   this.valid=valid;
        this.libelle=libelle;
        this.idQuestion=idQuestion;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
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

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }
}
