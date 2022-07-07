package themole.example.myapplication;


import androidx.annotation.RequiresApi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import themole.example.myapplication.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class scanning_qr extends MainActivity2 {


    String d;
    Drawable drawable,wrong;
    String contents,option;
    String num;
    String fileName="cod.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_qr);
         drawable  = getResources().getDrawable(R.mipmap.done_round);
        wrong = getResources().getDrawable(R.mipmap.wrong_round);
        //imgView.setImageDrawable(drawable);
        Intent ii = getIntent();
         d = ii.getStringExtra("data1");
        option= ii.getStringExtra("sendingData");


        File file = new File(getFilesDir() +File.separator+fileName);

        if(file.exists()){

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
            }
            catch ( IOException ioe) {
                ioe.printStackTrace () ;
            }

            num=datax.toString();

            try {
                finalize();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }


        }

        scanCode();

    }




    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode,int resultCode ,Intent data){
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode ,resultCode ,data);
        if(result != null){
            if (result.getContents() != null){
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                //builder.setMessage(result.getContents());
                //builder.setIconAttribute();

                contents=result.getContents();
                List<String> contentsList = Arrays.asList(contents.split(","));
                String classOnQrCode=contentsList.get(0);
                String curLatitude=contentsList.get(1);
                String curLongitude=contentsList.get(2);

                String arr1 = d.replace("In", ",");
                String arr2 = arr1.replace("At", ",");
                List<String> sendData = Arrays.asList(arr2.split(","));
                String subject = sendData.get(0);
                String classAttending = sendData.get(1);
                String timeAttended = sendData.get(2);

                // we are going to send data as checking IN
                if(option.equals("IN")) {

                    // check if he is in the write class
                    if (classAttending.replaceAll("\\s", "").equals(classOnQrCode.replaceAll("\\s", ""))) {
                        //check by what time is he late

                        Date date = new Date();
                        Integer hours = date.getHours();
                        Integer minutes = date.getMinutes();

                        String vb=timeAttended.replace(":",",");
                        List<String> sd = Arrays.asList(vb.split(","));
                        String attendingHour= sd.get(0);
                        String attendingMinutes;
                        attendingMinutes= sd.get(1);


                        Integer finalLateMin=0;
                        Integer lateMinutes=0;

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date datee = new Date();
                        String time = sdf.format(datee);


                        try{

                            if(minutes <= Integer.parseInt(attendingMinutes.replaceAll("\\s", ""))){
                                //the teacher came before time and he is exact
                                if(Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) == 00){
                                    //attendingMinutes= String.valueOf(60);
                                    finalLateMin=60 - minutes;
                                    String dc="["+time+","+finalLateMin+"]";
                                    InsertDataIN(num,dc);
                                }

                                finalLateMin=0;
                                String dc="["+time+","+finalLateMin+"]";
                                InsertDataIN(num,dc);

                            }

                            else if(minutes > Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) &&
                              hours.equals(Integer.parseInt(attendingHour.replaceAll("\\s", "")))){

                                if(Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) == 00){
                                    //attendingMinutes= String.valueOf(60);
                                    finalLateMin=60 - minutes;
                                    String dc="["+time+","+finalLateMin+"]";
                                    InsertDataIN(num,dc);
                                }

                                 lateMinutes=minutes-Integer.parseInt(attendingMinutes.replaceAll("\\s", ""));
                                 finalLateMin=lateMinutes;
                                String dc="["+time+","+finalLateMin+"]";
                                InsertDataIN(num,dc);

                            }else if(minutes < Integer.parseInt(attendingMinutes.replaceAll("\\s", ""))
                              && hours > Integer.parseInt(attendingHour.replaceAll("\\s", ""))){


                                lateMinutes=(minutes+60) - Integer.parseInt(attendingMinutes.replaceAll("\\s", ""));
                                finalLateMin=lateMinutes;

                                String dc="["+time+","+finalLateMin+"]";
                                InsertDataIN(num,dc);
                            }

                            //send data to database , of the time late
                            // fomart in the database [date(day,month,year),finalLateMin]

                        }
                        catch (NumberFormatException ex){
                            ex.printStackTrace();
                            Log.d("fgv", String.valueOf(ex));
                        }

                        builder.setIcon(drawable);
                        builder.setTitle("Information : ");
                        builder.setMessage("You Have Successfully Checked In to " + classAttending + " For " + subject + " At " + timeAttended);
                    }
                    else{

                        builder.setIcon(wrong);
                        builder.setTitle("WRONG CLASS CHECKED IN : ");
                        builder.setMessage("You Should Be In " +classAttending + " For " + subject + " At " + timeAttended);

                    }


                }
                else if(option.equals("OUT")){

                    if (classAttending.replaceAll("\\s", "").equals(classOnQrCode.replaceAll("\\s", ""))) {
                        //check by what time is he late

                        Date date = new Date();
                        Integer hours = date.getHours();
                        Integer minutes = date.getMinutes();

                        String vb=timeAttended.replace(":",",");
                        List<String> sd = Arrays.asList(vb.split(","));
                        String attendingHour= sd.get(0);
                        String attendingMinutes;
                        attendingMinutes= sd.get(1);


                        Integer finalExtendedMin=0;
                        Integer extendedMinutes=0;
                        Integer comeOutEarly=0;

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date datee = new Date();
                        String time = sdf.format(datee);

                        try{
                            if(Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) == 00){
                                attendingMinutes= String.valueOf(60);

                            }


                            if(minutes <= Integer.parseInt(attendingMinutes.replaceAll("\\s", ""))){

                                finalExtendedMin=0;
                                comeOutEarly=Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) - minutes;

                                String dc="["+time+","+finalExtendedMin+"]";
                                InsertDataOUT(num,dc);


                            }else if(minutes > Integer.parseInt(attendingMinutes.replaceAll("\\s", "")) &&
                                    hours.equals(Integer.parseInt(attendingHour.replaceAll("\\s", "")))){
                                comeOutEarly=0;

                                extendedMinutes=minutes - Integer.parseInt(attendingMinutes.replaceAll("\\s", ""));

                                String dc="["+time+","+extendedMinutes+"]";
                                InsertDataOUT(num,dc);

                            }else if(minutes < Integer.parseInt(attendingMinutes.replaceAll("\\s", ""))
                                    && hours > Integer.parseInt(attendingHour.replaceAll("\\s", ""))){
                                comeOutEarly=0;
                                extendedMinutes=(minutes+60) - Integer.parseInt(attendingMinutes.replaceAll("\\s", ""));

                                String dc="["+time+","+extendedMinutes+"]";
                                InsertDataOUT(num,dc);

                            }

                            //send data to database , of the time late
                            // fomart in the database [date(day,month,year),finalLateMin]

                        }
                        catch (NumberFormatException ex){
                            ex.printStackTrace();
                            Log.d("fgv", String.valueOf(ex));
                        }

                        builder.setIcon(drawable);
                        builder.setTitle("Information : ");
                        builder.setMessage("You Have Successfully Checked Out In " + classAttending + " For " + subject + " At " + timeAttended);
                    }
                    else{

                        builder.setIcon(wrong);
                        builder.setTitle("WRONG CLASS CHECKED OUT : ");
                        builder.setMessage("You Should Be Checking Out in " +classAttending + " For " + subject + " At " + timeAttended);

                    }




                }

                //Intent
                //builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener(){
                 //   @Override
                 //   public void onClick(DialogInterface dialog ,int which){
                //        scanCode();
                //    }
                //}).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                //    @Override
                //    public void onClick(DialogInterface dialog, int which) {
                 //       finish();
                 //   }
               // }) ;

                AlertDialog dialog=builder.create();
                dialog.show();

                // Hide after some seconds
                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                };

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                        finish();
                    }
                });

                handler.postDelayed(runnable, 3000);

            }
            else{
                Toast.makeText(this, "NO Results", Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onActivityResult(requestCode ,resultCode,data);
        }


    }


    public void InsertDataIN(final String fetCodee, final String checkCODE){

        // url to post our data
        String url = config.SERVER_URL_IN;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(scanning_qr.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(scanning_qr.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(scanning_qr.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                Log.d("prob", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                String fetcode=fetCodee;
                String checkinData = checkCODE ;


                params.put("fetchingCode", fetcode);
                params.put("checkInData", checkinData);
                //params.put("courseDescription", courseDescription);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }


    public void InsertDataOUT(final String fetCodee, final String outData){

        // url to post our data
        String url = config.SERVER_URL_OUT;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(scanning_qr.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(scanning_qr.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(scanning_qr.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                Log.d("prob", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                String fetcode=fetCodee;
                String outdata=outData;


                params.put("fetchingCode", fetcode);
                params.put("outData", outdata);
                //params.put("courseDescription", courseDescription);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }



}
