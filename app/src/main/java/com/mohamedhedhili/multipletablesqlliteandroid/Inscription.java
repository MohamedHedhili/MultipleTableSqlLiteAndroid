package com.mohamedhedhili.multipletablesqlliteandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import PackageModel.Categorie;
import PackageModel.DatabaseHelper;
import PackageModel.User;
import PackageModel.UserScore;

public class Inscription extends AppCompatActivity {
EditText firstname,  lastname ,  login , password1, password2 ;
    Button btn_sign_up ;
    DatabaseHelper db;
    ArrayList<Integer> listCategorieId= new ArrayList<Integer>();
    ArrayList<Categorie> listCategorie=new ArrayList<Categorie>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        firstname =(EditText)findViewById(R.id.FirstName);
        lastname = (EditText) findViewById(R.id.Lastname);
        login =(EditText)findViewById(R.id.Login);
        password1 =(EditText) findViewById(R.id.pass1);
        password2 = (EditText) findViewById(R.id.pass2);
        btn_sign_up= (Button) findViewById(R.id.SignUp);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DatabaseHelper(getApplicationContext());

                String name = firstname.getText().toString() + " " + lastname.getText().toString();
                String log = login.getText().toString();
                String password = password1.getText().toString();

                User user = new User(name, log, password);
                long  createUser = db.createUser(user);
             //   Toast.makeText(getApplication(),createUser+" user  " ,Toast.LENGTH_SHORT).show();

                listCategorie= (ArrayList<Categorie>) db.getAllCategorie();

                for (int i=0;i<listCategorie.size();i++)
                {
                    listCategorieId.add(listCategorie.get(i).getId());


                }
                UserScore us  ;

                String s ="" ;
                for (int i=0;i<listCategorieId.size();i++)
                {
                    Integer  x  =  (int)createUser;
                  us  =  new UserScore(listCategorieId.get(i),x,0) ;
                long createUserScore  = db.createUserScore(us);
                  s+=createUserScore;
                }


               Toast.makeText(getApplication(),createUser+" user  " + s +"insert score ",Toast.LENGTH_SHORT).show();

                db.closeDB();

            }
        });
    }
}
