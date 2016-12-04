package com.mohamedhedhili.multipletablesqlliteandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import PackageModel.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
   TextView link  ;
    Button btn_sign ;
    EditText login , password ;
    DatabaseHelper db;
    SQLiteDatabase bd ;

    CheckBox Admin  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        login  = (EditText)findViewById(R.id.emailSignIn);
        password  = (EditText)findViewById(R.id.password);
        btn_sign =(Button) findViewById(R.id.SignIn);
        Admin = (CheckBox) findViewById(R.id.Admin);
        link  =  (TextView) findViewById(R.id.link);
        db = new DatabaseHelper(getApplicationContext());

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if  (Admin.isChecked())
                {
                    if ((login.getText().toString().equals("Admin@games.com"))&&( password.getText().toString().equals("games123")))
                    {
                        Intent i  =  new Intent(getApplicationContext(),EspaceAdmin.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(getApplication(), "Check your password or your login", Toast.LENGTH_SHORT).show();

                }
                else {

                    int verifCnx = db.Authentificate(login.getText().toString(), password.getText().toString());


                    db.closeDB();
                    if (verifCnx>0) {
                        Intent i = new Intent(getApplicationContext(), ActivityLevel.class);
                        i.putExtra("idUser", verifCnx);
                        startActivity(i);

                    }
                    else
                        Toast.makeText(getApplication(), "Check your password or your login", Toast.LENGTH_SHORT).show();



                }

            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =  new Intent(getApplication(),Inscription.class);
                startActivity(i);

            }
        });

    }
}
