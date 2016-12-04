package PackageModel;

/**
 * Created by mohamedHedhili on 26/11/2016.
 */
public class UserScore {
private Integer idUser ;
private Integer idCategorie ;
private   int score ;

    public  UserScore (Integer  idCategorie, Integer  idUser,int score)
    {
 this.idUser= idUser;
        this.idCategorie=idCategorie ;
        this.score=score;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Integer   getidCategorie() {
        return idCategorie;
    }

    public void setidCategorie(Integer   iDcategorie) {
        this.idCategorie = iDcategorie;
    }

    public Integer   getidUser() {
        return  idUser;
    }

    public void setidUser(Integer   iDuser) {
        this.idUser = iDuser;
    }
}
