package themole.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //JSON Array
    public JSONArray result;

    EditText editTextvalueOrgCode;
    EditText editTextvaluePersonalcode;
    EditText editTextvaluePassword;
    Button buttonlogin;

    String fileName="cod.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextvalueOrgCode = (EditText) findViewById(R.id.Orgcode);
        editTextvaluePersonalcode = (EditText) findViewById(R.id.Personalcode);
        editTextvaluePassword = (EditText) findViewById(R.id.Password);
        buttonlogin = (Button) findViewById(R.id.loginBtn);



        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

            }
        });



    }

    public void getData () {

        //String id = editTextvalue.getText().toString().trim();
        String orgCode = editTextvalueOrgCode.getText().toString().trim();
        String personalCode = editTextvaluePersonalcode.getText().toString().trim();
        String pPassword = editTextvaluePassword.getText().toString().trim();


        if (orgCode.equals("") || personalCode.equals("") || pPassword.equals("")) {

            Toast.makeText(this, "Fill In All The Details", Toast.LENGTH_LONG).show();
            return;
        }

        String joinedData = orgCode + personalCode;
        String lettersRemoved = joinedData.replaceAll("[a-zA-Z]", "");

        String url = config.DATA_URL + lettersRemoved;
        Log.d("ed", lettersRemoved);

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

                            if (password.equals(pPassword)) {
                                Log.d("r", "correct");
                                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                                i.putExtra("userFullName", name);
                                i.putExtra("email", email);
                                i.putExtra("subject", subject);
                                i.putExtra("idt", idntiy);
                                i.putExtra("codee", lettersRemoved);

                                File file = new File(getFilesDir() +File.separator+fileName);

                                if(file.exists()){
                                    file.delete();
                                }

                                String File_Name = fileName; //gives file name
                                String Data = lettersRemoved; //define data

                                FileOutputStream fileobj = null;
                                try {
                                    fileobj = openFileOutput(File_Name, Context.MODE_PRIVATE);
                                    byte[] ByteArray = Data.getBytes(); //Converts into bytes stream
                                    fileobj.write(ByteArray); //writing to file
                                    fileobj.close(); //File closed
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                startActivity(i);

                            }

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
