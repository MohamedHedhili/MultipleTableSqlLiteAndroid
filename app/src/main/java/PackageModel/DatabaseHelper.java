package PackageModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamedHedhili on 24/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Game";

    // Table Names
    private static final String TABLE_USER = "users";
    public static final String TABLE_USER_SCORE= "userScore";
    private static final String TABLE_CATEGORIE = "categories";
    public static final String TABLE_QUESTION = "questions";
    public static final String TABLE_REPONSE = "reponses";



    // USER TABLE _ column names

    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_LOGIN= "login";
    public static final String COLUMN_PASSWORD = "password";



    // CATEGORIE Table - column names
    public static final String COLUMN_ID_CATEGORIE = "_id";
    public static final String COLUMN_LIBELLE = "libelle";

    //QUESTION TABLE -column  names
    public static final String COLUMN_ID_QUESTION = "_id";
    public static final String COLUMN_lIBELLE_QUESTION = "libelle";
    public static final String COLUMN_CATEGORIE= "idCategorie";

    //REPONSE TABLE -column  names
    public static final String COLUMN_ID_REPONSE = "_id";
    public static final String COLUMN_LIBELLE_REPONSE = "libelle";
    public static final String COLUMN_VALID = "validity";
    public static final String ID_QUESTION = "idQuestion";

    //UserScore TABLE -column  names
    public static final String COLUMN_ID_USER_SCORE ="_id";
    public static final String COLUMN_CATEGORIE1= "idCategorie";
    public static final String COLUMN_USER= "idUser";
    public static final String COLUMN_SCORE = "score";

    // Table Create Statements
    // User table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + COLUMN_ID_USER + " INTEGER PRIMARY KEY  autoincrement," +COLUMN_NOM
            + " TEXT," + COLUMN_LOGIN   + " TEXT," + COLUMN_PASSWORD
            + " TEXT" + ");";

    // Categorie table create statement
    private static final String CREATE_TABLE_CATEGORIE = "create table "
            + TABLE_CATEGORIE
            + "("
            + COLUMN_ID_CATEGORIE  + " integer primary key autoincrement, "
            + COLUMN_LIBELLE  + " text not null "

            + ");";
    // Question table create statement
    private static final String CREATE_TABLE_QUESTION = "create table "
            + TABLE_QUESTION
            + "("
            +COLUMN_ID_QUESTION  + " integer primary key autoincrement, "
            + COLUMN_lIBELLE_QUESTION  + " text not null ,"
            + COLUMN_CATEGORIE + " integer ,"
            +"foreign key ("+COLUMN_CATEGORIE+") references "+TABLE_CATEGORIE+"("+COLUMN_ID_CATEGORIE+"));";

// Reponse table create statement


    private static final String CREATE_TABLE_REPONSE= "create table "
            + TABLE_REPONSE
            + "("
            + COLUMN_ID_REPONSE + " integer primary key autoincrement, "
            + COLUMN_LIBELLE_REPONSE  + " text not null, "
            + COLUMN_VALID + " integer ,"
            + ID_QUESTION + " integer ,"
            +"foreign key ("+ID_QUESTION+") references "+TABLE_QUESTION+"("+COLUMN_ID_QUESTION+"));";

    // UserScore table create statement
    private static final String CREATE_TABLE_USER_SCORE  = "create table "
            + TABLE_USER_SCORE
            + "("
            + COLUMN_ID_USER_SCORE + " integer primary key autoincrement, "
            + COLUMN_USER+ " integer , "
            +COLUMN_CATEGORIE1 + " integer, "
            +  COLUMN_SCORE + " integer ,"

            +"foreign key ("+COLUMN_USER+") references "+TABLE_USER+"("+COLUMN_ID_USER+"),"
            +"foreign key ("+COLUMN_CATEGORIE1+") references "+TABLE_CATEGORIE+"("+COLUMN_ID_CATEGORIE+"));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_REPONSE);

        db.execSQL(CREATE_TABLE_USER_SCORE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPONSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_SCORE);




        // create new tables
        onCreate(db);
    }

    public long createUser (User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
     //   values.put(COLUMN_ID_USER, user.getId());
        values.put(COLUMN_NOM, user.getNom().toString());
        values.put(COLUMN_LOGIN, user.getLogin());
        values.put(COLUMN_PASSWORD, user.getPassword());

        // insert row
        long User_id = db.insert(TABLE_USER, null, values);





        return User_id;
    }
    public long createCategorie (Categorie categorie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(COLUMN_ID_USER, categorie.getId());
        values.put(COLUMN_LIBELLE, categorie.getLibelle());


        // insert row
        long Categorie_id = db.insert(TABLE_CATEGORIE, null, values);





        return Categorie_id;
    }
    // insert in table  question
    public long createQuestion (Question question) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_lIBELLE_QUESTION, question.getLibelle());
        values.put(COLUMN_CATEGORIE, question.getIdCategorie());


        // insert row
        long question_id = db.insert(TABLE_QUESTION, null, values);





        return question_id;
    }

    // insert in table reponse
    public long createReponse (Reponse reponse) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LIBELLE_REPONSE, reponse.getLibelle());
        values.put(COLUMN_VALID, reponse.getValid());

        values.put(ID_QUESTION, reponse.getIdQuestion());


        // insert row
        long Reponse_id = db.insert(TABLE_REPONSE, null, values);





        return Reponse_id;
    }
    // insert in table  user  Score

    public long createUserScore (UserScore user) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, user.getidUser());
        values.put( COLUMN_CATEGORIE1, user.getidCategorie());


        values.put(COLUMN_SCORE, user.getScore());


        // insert row
        long userS_id = db.insert(TABLE_USER_SCORE, null, values);





        return userS_id;
    }



    //  Authentificate  User

    public int Authentificate (String login , String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + COLUMN_PASSWORD + " = '" + password +"'AND "+ COLUMN_LOGIN + " = '" + login +"'" ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount()==1)
        {c.moveToFirst();
            int id =c.getInt(c.getColumnIndex(COLUMN_ID_USER));
            return  id ;
        }




        return -1;
    }

  // Check categorie  exist in the table categorie or not and  return  categorieID
    public long  CheckCategorie (String  categorie )

    { SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIE + " WHERE "
                + COLUMN_LIBELLE + " = '" + categorie+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

             long id = c.getInt((c.getColumnIndex(COLUMN_ID_CATEGORIE)));
            return  id  ;

        }
        return  -1 ;
        }

    // Check question  exist in the table Question or not and  return  Questin ID

    private int  checkQuestion (String  question )

    { SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION + " WHERE "
                + COLUMN_lIBELLE_QUESTION + " = " + question;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            int id = c.getInt((c.getColumnIndex(COLUMN_ID_QUESTION)));
            return  id  ;

        }
        return  -1 ;
    }
    // find  Categorie by  libelle and  return  idCategorie
    public int findCategorie (String libelle ) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIE + " WHERE "
                 +"'AND "+ COLUMN_LIBELLE + " = '" + libelle+"'" ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {

            int id = c.getInt((c.getColumnIndex(COLUMN_ID_CATEGORIE)));
            return  id  ;

        }
        return  -1 ;
    }

    // find  User by  login and  return  idUser
    public int findUser (User login ) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                +"'AND "+ COLUMN_LOGIN + " = '" + login+"'" ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {

            int id = c.getInt((c.getColumnIndex(COLUMN_ID_USER)));
            return  id  ;

        }
        return  -1 ;
    }
//  close  Database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    //Fetching all Categorie

    public List<Categorie> getAllCategorie() {
        List<Categorie> Categories = new ArrayList<Categorie>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Categorie cat = new Categorie(c.getInt(c.getColumnIndex(COLUMN_ID_CATEGORIE)),c.getString(c.getColumnIndex(COLUMN_LIBELLE)));


                // adding to tags list
                Categories.add(cat);
            } while (c.moveToNext());
        }
        return Categories;
    }

    // get  All  Question
    public List<Question> getAllQuestion( int id) {
        List<Question> questions = new ArrayList<Question>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION
                + " WHERE "
                + COLUMN_CATEGORIE + " = " + id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Question q = new Question(c.getInt(c.getColumnIndex(COLUMN_ID_QUESTION)),c.getString(c.getColumnIndex(COLUMN_lIBELLE_QUESTION)),c.getInt(c.getColumnIndex(COLUMN_CATEGORIE)));


                // adding to tags list
                questions.add(q);
            } while (c.moveToNext());
        }
        return questions;
    }

    // get  All  reponses  for  question  i
    // get  All  Reponses
    public List<Reponse> getAllReponse(int  id) {
        List<Reponse> reponses = new ArrayList<Reponse>();
        String selectQuery = "SELECT  * FROM " + TABLE_REPONSE
                + " WHERE "
                + ID_QUESTION+ " = " + id
                ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Reponse r = new Reponse(c.getString(c.getColumnIndex(COLUMN_LIBELLE_REPONSE)),c.getInt(c.getColumnIndex(COLUMN_VALID)),c.getInt(c.getColumnIndex(ID_QUESTION)));


                // adding to tags list
                reponses.add(r);
            } while (c.moveToNext());
        }
        return reponses;
    }


    //  Get  score  USER in  categorie
    public int getScore (int user , int cat) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_SCORE + " WHERE "
                + COLUMN_CATEGORIE1 + " = " + cat +" AND "+ COLUMN_USER + " = " + user  ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount()==1)
        {c.moveToFirst();
            int score =c.getInt(c.getColumnIndex(COLUMN_SCORE));
            return  score ;
        }




        return -1;
    }
    // update  score
    public int updateScore(int user , int cat,int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, newScore);

        // updating row
        return db.update(TABLE_USER_SCORE, values, COLUMN_USER + " = "+user+" AND " + COLUMN_CATEGORIE1 +" = "+cat,
               null);
    }







}