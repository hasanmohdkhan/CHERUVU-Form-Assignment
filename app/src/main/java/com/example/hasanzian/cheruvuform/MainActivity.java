package com.example.hasanzian.cheruvuform;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

     private  EditText sNo,FarmerName,FarmerAge;
    private Button submit;
    private Spinner villageSpinner,mandelSpinner;
    public TextView WARN_SNO,WARN_NAME,WARN_AGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sNo=(EditText)findViewById(R.id.sno_edit);
        FarmerAge=(EditText)findViewById(R.id.farmer_age_edit);
        FarmerName  =(EditText)findViewById(R.id.farmer_name_edit);
        submit=(Button)findViewById(R.id.button_submit);
        villageSpinner=(Spinner) findViewById(R.id.village_spinner);

        mandelSpinner=(Spinner)findViewById(R.id.mandal_spinner);
        // text View casting
        WARN_SNO=(TextView)findViewById(R.id.warn_SNO);
        WARN_NAME=(TextView)findViewById(R.id.warn_Name);
        WARN_AGE=(TextView)findViewById(R.id.warn_Age);



       sNo.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(sNo.getText().toString().length() <  3){
                WARN_SNO.setVisibility(View.VISIBLE);
                WARN_SNO.setText("S.No. Must be 3 Digit long !");

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(sNo.getText().toString().length()==3){
                WARN_SNO.setVisibility(View.INVISIBLE);

            }

        }
    });
       FarmerAge.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(FarmerAge.getText().toString().matches("0") || FarmerAge.getText().toString().matches("00")) {

                   WARN_AGE.setVisibility(View.VISIBLE);
                   WARN_AGE.setText("ERROR ! Age can't be  0 ");


               }
           }

           @Override
           public void afterTextChanged(Editable s) {
               if(s.length() == 2  ){
                   WARN_AGE.setVisibility(View.INVISIBLE);
               }


           }
       });
       FarmerName.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(FarmerName.getText().toString().matches("(.*)[a-zA-z](.*)"))
               {

                   WARN_NAME.setVisibility(View.INVISIBLE);
                   // WARN_NAME.setText("Accept only alphabets");

               }


           }

           @Override
           public void afterTextChanged(Editable s) {
               if(FarmerName.getText().toString().matches("(.*)[0-9](.*)")
                       ){
                   WARN_NAME.setVisibility(View.VISIBLE);
                   WARN_NAME.setText("Accept only alphabets");

               }








           }
       });






       mandelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String sp1=String.valueOf(mandelSpinner.getSelectedItem());
               if(sp1.contentEquals("Mandal 1")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village A");
                   list.add("Village B");
                   list.add("Village C");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }

               if(sp1.contentEquals("Mandal 2")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village D");
                   list.add("Village E");
                   list.add("Village F");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }

               if(sp1.contentEquals("Mandal 3")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village G");
                   list.add("Village H");
                   list.add("Village I");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }



           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == submit){
                    addForm();
                    sNo.setText("");
                    FarmerAge.setText("");
                    FarmerName.setText("");
                    WARN_SNO.setVisibility(View.INVISIBLE);
                    WARN_NAME.setVisibility(View.INVISIBLE);
                    WARN_AGE.setVisibility(View.INVISIBLE);
                }

            }
        });





    }

    private void addForm() {
        //addEmployee()

        final String Village=villageSpinner.getSelectedItem().toString();
        final String Mandal=mandelSpinner.getSelectedItem().toString();

        final  String Sno=sNo.getText().toString().trim();
        final  String farmerName=FarmerName.getText().toString().trim();
        final  String farmerAge=FarmerAge.getText().toString().trim();


    class AddForm extends AsyncTask<Void,Void,String>{
     ProgressDialog loading;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading.dismiss();
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... v) {
            HashMap<String,String> params = new HashMap<>();
            params.put(Config.KEY_FAR_SNO,Sno);
            params.put(Config.KEY_FAR_NAME,farmerName);
            params.put(Config.KEY_FAR_AGE,farmerAge);
            params.put(Config.KEY_FAR_MANDAL,Mandal);
            params.put(Config.KEY_FAR_VILLAGE,Village);


            RequestHandler rh = new RequestHandler();
            String res = rh.sendPostRequest(Config.URL_ADD, params);
            return res;
        }
    }

        AddForm ae = new AddForm();
        ae.execute();
    }







}


















