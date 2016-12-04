package com.mohamedhedhili.multipletablesqlliteandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.nfc.tech.NfcBarcode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import PackageModel.Categorie;
import PackageModel.DatabaseHelper;
import PackageModel.Question;
import PackageModel.Reponse;

public class EspaceAdmin extends AppCompatActivity implements OnItemSelectedListener  {
ToggleButton btn_category ,  btn_question  ;
    DatabaseHelper db;
    ArrayList<Categorie> listCategorie=new ArrayList<Categorie>();
    ArrayList<String> listSpin =  new ArrayList<String>() ;
    String cat ;
    Context context = this ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace_admin);
        btn_category =(ToggleButton)findViewById(R.id.btn_categorie);
        btn_question  =(ToggleButton)findViewById(R.id.btn_question);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EspaceAdmin.this);
                builder.setMessage("Category");
                builder.setMessage("Category"); // Message to be displayed


                     LayoutInflater li = getLayoutInflater();
                View view = li.inflate(R.layout.myalertdialogcategorie, null);
                final Button add = (Button) view.findViewById(R.id.btn_add);
                final EditText editadd = (EditText) view.findViewById(R.id.edit_add);
                final Button delete = (Button) view.findViewById(R.id.btn_delete);
                final EditText editdelete = (EditText) view.findViewById(R.id.edit_delete);

                builder.setView(view);
                add.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        db = new DatabaseHelper(getApplicationContext());
                        long  createCategory = db.createCategorie(new Categorie(editadd.getText().toString()));
                        Toast.makeText(getApplicationContext(),createCategory+"category",Toast.LENGTH_SHORT).show();
                        db.closeDB();

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                builder.show();


            }
        });

        // manage  question and  answer


        btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder = new AlertDialog.Builder(EspaceAdmin.this);
                builder.setMessage(" Manage Question");
                builder.setMessage("Manage Question"); // Message to be displayed


                LayoutInflater li = getLayoutInflater();
                View view = li.inflate(R.layout.myalertdialogquestion, null);
                final Button add_question = (Button) view.findViewById(R.id.btn_add_new_question);
                final EditText edit_new_question = (EditText) view.findViewById(R.id.edit_new_question);
                final EditText answer1 = (EditText) view.findViewById(R.id.answer1);
                final EditText answer2  = (EditText) view.findViewById(R.id.answer2);
                final EditText answer3  = (EditText) view.findViewById(R.id.answer3);
                final CheckBox checkBox1  = (CheckBox) view.findViewById(R.id.checkbox1);
                final CheckBox checkBox2  = (CheckBox) view.findViewById(R.id.checkbox2);
                final CheckBox checkBox3  = (CheckBox) view.findViewById(R.id.checkbox3);
                final Button add_answer = (Button) view.findViewById(R.id.btn_add_answer);
                   Spinner spin = (Spinner) view.findViewById(R.id.simpleSpinner);

                spin.setOnItemSelectedListener((OnItemSelectedListener) view.getContext());

                db = new DatabaseHelper(getApplicationContext());
                listCategorie = (ArrayList<Categorie>) db.getAllCategorie();
                listSpin.add("Choose Category") ;
                for (int i = 0 ;i<listCategorie.size();i++)
                {listSpin.add(listCategorie.get(i).getLibelle());}


                //Creating the ArrayAdapter instance having the Category name list
                ArrayAdapter  aa = new ArrayAdapter (view.getContext(),android.R.layout.simple_spinner_item, listSpin);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);

                db.closeDB();

                add_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db = new DatabaseHelper(getApplicationContext());


                        if ( (cat.equals("Choose Category")) || (edit_new_question.getText().toString().equals("")))
                        {Toast.makeText(getApplicationContext(),"Check  your  Category name or  your question  ",Toast.LENGTH_SHORT).show();
                         }
                        else

                        {
                            long  fetch_category  = db.CheckCategorie(cat);
                            Toast.makeText(getApplicationContext(),fetch_category+"",Toast.LENGTH_SHORT).show();

                        if  (fetch_category>0)
                        {
                          Question q = new Question (edit_new_question.getText().toString(),(int)fetch_category);
                            edit_new_question.setText("");
                            Toast.makeText(getApplicationContext()," Succes Insert Question ",Toast.LENGTH_SHORT).show();

                            final  long insert_question =db.createQuestion(q);
                           // if  insert_question > 0   then  insert  answer  for  the  question
                            if  (insert_question>0)

                            {   checkBox1.setEnabled(true);
                                checkBox2.setEnabled(true);
                                checkBox3.setEnabled(true);
                                answer1.setEnabled(true);
                                answer2.setEnabled(true);
                                answer3.setEnabled(true);
                                add_answer.setEnabled(true);
                               add_answer.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       if  ( (answer1.getText().toString().equals("")) || (answer2.getText().toString().equals(""))|| (answer2.getText().toString().equals("")))
                                       {
                                           Toast.makeText(getApplicationContext()," Check  your answer ",Toast.LENGTH_SHORT).show();

                                       }
                                       else
                                       {   if (checkBox1.isChecked())
                                           {  Reponse  rep1 =  new Reponse (answer1.getText().toString(),1,(int)insert_question);
                                           long  insert_answer1 =db.createReponse(rep1);
                                               answer1.setText("");
                                               Toast.makeText(getApplicationContext(),"Succes insert answer1",Toast.LENGTH_SHORT).show();


                                           }
                                            else
                                             {  Reponse  rep1 =  new Reponse (answer1.getText().toString(),0,(int)insert_question);
                                                 answer1.setText("");
                                             long  insert_answer1 =db.createReponse(rep1);
                                                 Toast.makeText(getApplicationContext(),"Succes insert answer1",Toast.LENGTH_SHORT).show();
                                            }
                                           if (checkBox2.isChecked())
                                           {  Reponse  rep1 =  new Reponse (answer2.getText().toString(),1,(int)insert_question);
                                               long  insert_answer2 =db.createReponse(rep1);
                                               answer2.setText("");
                                               Toast.makeText(getApplicationContext(),"Succes insert answer2",Toast.LENGTH_SHORT).show();

                                           }
                                           else
                                           {  Reponse  rep1 =  new Reponse (answer2.getText().toString(),0,(int)insert_question);
                                               long  insert_answer2 =db.createReponse(rep1);
                                               answer2.setText("");
                                               Toast.makeText(getApplicationContext(),"Succes insert answer2",Toast.LENGTH_SHORT).show();

                                           }
                                           if (checkBox3.isChecked())
                                           {  Reponse  rep1 =  new Reponse (answer3.getText().toString(),1,(int)insert_question);
                                               long  insert_answer3 =db.createReponse(rep1);
                                               answer3.setText("");
                                               Toast.makeText(getApplicationContext(),"Succes insert answer3",Toast.LENGTH_SHORT).show();

                                           }
                                           else
                                           {  Reponse  rep1 =  new Reponse (answer3.getText().toString(),0,(int)insert_question);
                                               long  insert_answer3 =db.createReponse(rep1);
                                               answer3.setText("");
                                               Toast.makeText(getApplicationContext(),"Succes insert answer3",Toast.LENGTH_SHORT).show();

                                           }


                                       }
                                   }
                               });

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext()," Category not  exist  in database  ",Toast.LENGTH_SHORT).show();
                             }
                        }

                    }
                });
                builder.setView(view);

                builder.show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cat=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
