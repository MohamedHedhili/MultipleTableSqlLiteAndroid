package com.mohamedhedhili.multipletablesqlliteandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import PackageModel.DatabaseHelper;
import PackageModel.Question;
import PackageModel.Reponse;

public class QuestionsActivity extends AppCompatActivity {
    private RadioGroup group1;
    private TextView   textQuestion;
    private RadioButton r1,r2,r3;
    private Button btnNext,btnSubmit;
    DatabaseHelper db  ;
    List<Question> questions;
    List<Reponse> reponses =new ArrayList<>();
    private  int i=0 ;
    Bundle  b ;
    int  user   ;
    long category   ;
    int  newScore  = 0 ;
    int sizeListQuestions ;
    Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
       textQuestion = (TextView) findViewById(R.id.textQuestion);
        r1=(RadioButton )findViewById(R.id.radioButton1);
        r2=(RadioButton )findViewById(R.id.radioButton2);
        r3=(RadioButton )findViewById(R.id.radioButton3);
        group1=(RadioGroup)findViewById(R.id.radiogroup);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        initialize ();
        db =  new DatabaseHelper(getApplicationContext());
        b =  getIntent().getExtras();
        String x= b.getString("category");
        user = b.getInt("idUser");
        category = db.CheckCategorie(x);
        questions = db.getAllQuestion((int) category);
        sizeListQuestions=questions.size();
        textQuestion.setText(questions.get(i).getLibelle());

        reponses = db.getAllReponse(questions .get(i).getId());
        r1.setText(reponses.get(0).getLibelle());
        r2.setText(reponses.get(1).getLibelle());
        r3.setText(reponses.get(2).getLibelle());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i < sizeListQuestions) {
                    textQuestion.setText(questions.get(i).getLibelle());

                    reponses = db.getAllReponse(questions.get(i).getId());
                    r1.setText(reponses.get(0).getLibelle());
                    r2.setText(reponses.get(1).getLibelle());
                    r3.setText(reponses.get(2).getLibelle());
                } else {
                    btnSubmit.setVisibility(View.VISIBLE);
                    btnNext.setEnabled(false);
                }
                String reponseValid="";
                for (int i=0 ; i<reponses.size();i++)
                { if  (reponses.get(i).getValid()==1)
                { reponseValid= reponses.get(i).getLibelle();}
                }
                int selectedId1 = group1.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radio1 = (RadioButton) findViewById(selectedId1);

                 if (radio1.getText().equals(reponseValid))
                 {  newScore +=5 ;
                 }
                else
                 {
                     newScore = newScore ;
                 }


            }
        });

   btnSubmit.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           int old =  db.getScore(user, (int) category);
           if (  old <= newScore )
           { int i  =db.updateScore(user, (int) category, newScore) ;}

           // Alert  Dialog
           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                   context);

           // set title
           alertDialogBuilder.setTitle(b.getString("category"));

           // set dialog message
           alertDialogBuilder
                   .setMessage("Votre score est " + newScore + " \n Meilleur score est  "+db.getScore(user,(int)category))

                   .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // if this button is clicked, just close
                           // the dialog box and do nothing
                           dialog.cancel();
                       }
                   });

           // create alert dialog
           AlertDialog alertDialog = alertDialogBuilder.create();

           // show it
           alertDialog.show();
       }
   });


    }
    void initialize ()
    {
        i=0;
        reponses.clear();
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);



    }


}
