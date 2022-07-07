package themole.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import themole.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class landingpage extends AppCompatActivity {

    Button buttonsignIn;
    Button ialreadyHaveAnAcc;
    String fileName="cod.txt";
    //JSON Array
    public JSONArray result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        buttonsignIn = (Button) findViewById(R.id.singIn);
        ialreadyHaveAnAcc =(Button) findViewById(R.id.iHaveAnAccount);

        buttonsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignPage();

            }
        });

        ialreadyHaveAnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();

            }
        });

    }

    public void openSignPage(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }


    public void check(){

        File file = new File(getFilesDir() +File.separator+fileName);
        Log.d("g", file.toString());


        if(file.exists()){
            readSavedData();
        }else{
            Toast.makeText(this, "You Have No Account, Please Sign Up First", Toast.LENGTH_LONG).show();
        }

    }

    public void readSavedData ( ) {


        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fIn = openFileInput (fileName) ;
            InputStreamReader isr = new InputStreamReader(fIn) ;
            BufferedReader buffreader = new BufferedReader (isr) ;

            String readString = buffreader.readLine () ;
            while ( readString != null ) {
                datax.append(readString);
                readString = buffreader.readLine () ;
            }

            isr.close () ;
        } catch ( IOException ioe) {
            ioe.printStackTrace () ;
        }
        Log.d("gj", datax.toString());


        makeReq(datax.toString());

        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //return datax.toString() ;
    }

    public void makeReq (String x){
        String urlff = x;

        String url = config.DATA_URL + x;
        //Creating a string request
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(config.JSON_ARRAY);

                            JSONObject json = result.getJSONObject(0);

                            //Adding the name of the student to array list
                            String password = json.getString(config.PASSWORD);
                            String name = json.getString(config.KEY_NAME);
                            String email = json.getString(config.EMAIL);
                            String subject = json.getString(config.SUBJECTT);
                            String idntiy = json.getString(config.IDENTITY_CODE);
                            //  if(password.equals(pPassword)) {
                            Log.d("r", "correct");
                            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                            i.putExtra("userFullName", name);
                            i.putExtra("email", email);
                            i.putExtra("subject", subject);
                            i.putExtra("idt", idntiy);
                            i.putExtra("codees", urlff);

                            startActivity(i);

                            // }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);



    }


}