package themole.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import themole.example.myapplication.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity3 extends MainActivity2 {
    String fullFetchingCode;

    Button checkingIn;
    Button checkingOut;
    Integer mondayArrayLength = 0;
    Integer numberOfLessons = 0;
    String corectDate;
    String fetchingDate;
    Button newBtn2, newBtn, newBtn3, newBtn4, newBtn5, newBtn6, newBtn7, newBtn8, newBtn9, newBtn10, newBtn11, newBtn12;
    TextView newtextView;
    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent iii = getIntent();
        fullFetchingCode = iii.getStringExtra("fetchingCode");
        checkingIn = (Button) findViewById(R.id.checkingIn);
        checkingOut = (Button) findViewById(R.id.checkingOut);
        newBtn2 = new Button(this);
        newBtn = new Button(this);
        newBtn3 = new Button(this);
        newBtn4 = new Button(this);
        newBtn5 = new Button(this);
        newBtn6 = new Button(this);
        newBtn7 = new Button(this);
        newBtn8 = new Button(this);
        newBtn9 = new Button(this);
        newBtn10 = new Button(this);
        newBtn11 = new Button(this);
        newBtn12 = new Button(this);

        newtextView = new TextView(this);
        layout = (LinearLayout) findViewById(R.id.layt);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 25, 5, 5);


        checkingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInFunc();

            }
        });


        checkingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutFunc();

            }
        });


    }

    public void checkInFunc() {
        String fetchingUrl = config.DATA_URL + fullFetchingCode;
        String checkingInSendingData="IN";

        //Creating a string request
        StringRequest stringRequest = new StringRequest(fetchingUrl,
                response -> {
                    JSONObject j = null;
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = new JSONObject(response);

                        //Storing the Array of JSON String to our JSON Array
                        result = j.getJSONArray(config.JSON_ARRAY);

                        JSONObject json = result.getJSONObject(0);

                        Date date = new Date();
                        Integer dt = date.getDay();
                        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

                        switch (dt) {
                            case 1:
                                corectDate = days[0];
                                fetchingDate = config.MONDAY_SUB;
                                break;
                            case 2:
                                corectDate = days[1];
                                fetchingDate = config.TUESDAY_SUB;
                                break;
                            case 3:
                                corectDate = days[2];
                                fetchingDate = config.WEN_SUB;
                                break;
                            case 4:
                                corectDate = days[3];
                                fetchingDate = config.THURSDAY_SUB;
                                break;
                            case 5:
                                corectDate = days[4];
                                fetchingDate = config.FRIDAY_SUB;
                                break;


                        }
                        ;
                        //Log.d("i", String.valueOf(date.getDay()));


                        String dayy = json.getString(fetchingDate);//fetchingDate
                        Log.d("sub", dayy);
                        Integer a = 3;
                        Integer b = 0;

                        List<String> mondaylist = Arrays.asList(dayy.split(","));
                        String[] mondayArray = new String[mondaylist.size()];


                        for (int i = 0; i < mondaylist.size(); i++) {
                            mondayArray[i] = mondaylist.get(i).replaceAll("[\\[\\](){}]", "");

                            mondayArrayLength = mondayArray.length;
                        }

                        newtextView.setText("Which Lesson Are You Checking In ? ");
                        newtextView.setTextSize(30);
                        newtextView.setTextColor(Color.parseColor("#ffffff"));
                        layout.addView(newtextView);

                        switch (mondayArrayLength) {
                            case 3:
                                numberOfLessons = 1;
                                String[] mondayTime = new String[numberOfLessons];
                                String[] mondayClass = new String[numberOfLessons];
                                String[] mondaySubject = new String[numberOfLessons];


                                mondayTime[0] = mondayArray[0];
                                mondayClass[0] = mondayArray[1];
                                mondaySubject[0] = mondayArray[2];

                                String finalString1 = mondaySubject[0] + " In " + mondayClass[0] + " At " + mondayTime[0];
                                newBtn.setText(finalString1);
                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString1);
                                        i.putExtra("sendingData",checkingInSendingData);
                                       // i.putExtra("sendingCode",fullFetchingCode);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 6:
                                numberOfLessons = 2;
                                String[] mondayTime2 = new String[numberOfLessons];
                                String[] mondayClass2 = new String[numberOfLessons];
                                String[] mondaySubject2 = new String[numberOfLessons];


                                mondayTime2[0] = mondayArray[0];
                                mondayTime2[1] = mondayArray[1];

                                mondayClass2[0] = mondayArray[2];
                                mondayClass2[1] = mondayArray[3];

                                mondaySubject2[0] = mondayArray[4];
                                mondaySubject2[1] = mondayArray[5];

                                String finalString2 = mondaySubject2[0] + " In " + mondayClass2[0] + " At " + mondayTime2[0];
                                String finalString3 = mondaySubject2[1] + " In " + mondayClass2[1] + " At " + mondayTime2[1];

                                newBtn.setText(finalString2);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString2);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString3);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString3);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 9:
                                numberOfLessons = 3;
                                String[] mondayTime3 = new String[numberOfLessons];
                                String[] mondayClass3 = new String[numberOfLessons];
                                String[] mondaySubject3 = new String[numberOfLessons];


                                mondayTime3[0] = mondayArray[0];
                                mondayTime3[1] = mondayArray[1];
                                mondayTime3[2] = mondayArray[2];

                                mondayClass3[0] = mondayArray[3];
                                mondayClass3[1] = mondayArray[4];
                                mondayClass3[2] = mondayArray[5];

                                mondaySubject3[0] = mondayArray[6];
                                mondaySubject3[1] = mondayArray[7];
                                mondaySubject3[2] = mondayArray[8];

                                String finalString4 = mondaySubject3[0] + " In " + mondayClass3[0] + " At " + mondayTime3[0];
                                String finalString5 = mondaySubject3[1] + " In " + mondayClass3[1] + " At " + mondayTime3[1];
                                String finalString6 = mondaySubject3[2] + " In " + mondayClass3[2] + " At " + mondayTime3[2];

                                newBtn.setText(finalString4);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString4);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                newBtn2.setText(finalString5);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString5);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString6);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString6);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;
                            case 12:
                                numberOfLessons = 4;
                                String[] mondayTime4 = new String[numberOfLessons];
                                String[] mondayClass4 = new String[numberOfLessons];
                                String[] mondaySubject4 = new String[numberOfLessons];


                                mondayTime4[0] = mondayArray[0];
                                mondayTime4[1] = mondayArray[1];
                                mondayTime4[2] = mondayArray[2];
                                mondayTime4[3] = mondayArray[3];

                                mondayClass4[0] = mondayArray[4];
                                mondayClass4[1] = mondayArray[5];
                                mondayClass4[2] = mondayArray[6];
                                mondayClass4[3] = mondayArray[7];

                                mondaySubject4[0] = mondayArray[8];
                                mondaySubject4[1] = mondayArray[9];
                                mondaySubject4[2] = mondayArray[10];
                                mondaySubject4[3] = mondayArray[11];

                                String finalString7 = mondaySubject4[0] + " In " + mondayClass4[0] + " At " + mondayTime4[0];
                                String finalString8 = mondaySubject4[1] + " In " + mondayClass4[1] + " At " + mondayTime4[1];
                                String finalString9 = mondaySubject4[2] + " In " + mondayClass4[2] + " At " + mondayTime4[2];
                                String finalString10 = mondaySubject4[3] + " In " + mondayClass4[3] + " At " + mondayTime4[3];

                                newBtn.setText(finalString7);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString7);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString8);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString8);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString9);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString9);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString10);
                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString10);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 15:
                                numberOfLessons = 5;
                                String[] mondayTime5 = new String[numberOfLessons];
                                String[] mondayClass5 = new String[numberOfLessons];
                                String[] mondaySubject5 = new String[numberOfLessons];


                                mondayTime5[0] = mondayArray[0];
                                mondayTime5[1] = mondayArray[1];
                                mondayTime5[2] = mondayArray[2];
                                mondayTime5[3] = mondayArray[3];
                                mondayTime5[4] = mondayArray[4];

                                mondayClass5[0] = mondayArray[5];
                                mondayClass5[1] = mondayArray[6];
                                mondayClass5[2] = mondayArray[7];
                                mondayClass5[3] = mondayArray[8];
                                mondayClass5[4] = mondayArray[9];

                                mondaySubject5[0] = mondayArray[10];
                                mondaySubject5[1] = mondayArray[11];
                                mondaySubject5[2] = mondayArray[12];
                                mondaySubject5[3] = mondayArray[13];
                                mondaySubject5[4] = mondayArray[14];

                                String finalString11 = mondaySubject5[0] + " In " + mondayClass5[0] + " At " + mondayTime5[0];
                                String finalString12 = mondaySubject5[1] + " In " + mondayClass5[1] + " At " + mondayTime5[1];
                                String finalString13 = mondaySubject5[2] + " In " + mondayClass5[2] + " At " + mondayTime5[2];
                                String finalString14 = mondaySubject5[3] + " In " + mondayClass5[3] + " At " + mondayTime5[3];
                                String finalString15 = mondaySubject5[4] + " In " + mondayClass5[4] + " At " + mondayTime5[4];

                                newBtn.setText(finalString11);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString11);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString12);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString12);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString13);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString13);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString14);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString14);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString15);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString15);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 18:
                                numberOfLessons = 6;
                                String[] mondayTime6 = new String[numberOfLessons];
                                String[] mondayClass6 = new String[numberOfLessons];
                                String[] mondaySubject6 = new String[numberOfLessons];


                                mondayTime6[0] = mondayArray[0];
                                mondayTime6[1] = mondayArray[1];
                                mondayTime6[2] = mondayArray[2];
                                mondayTime6[3] = mondayArray[3];
                                mondayTime6[4] = mondayArray[4];
                                mondayTime6[5] = mondayArray[5];

                                mondayClass6[0] = mondayArray[6];
                                mondayClass6[1] = mondayArray[7];
                                mondayClass6[2] = mondayArray[8];
                                mondayClass6[3] = mondayArray[9];
                                mondayClass6[4] = mondayArray[10];
                                mondayClass6[5] = mondayArray[11];

                                mondaySubject6[0] = mondayArray[12];
                                mondaySubject6[1] = mondayArray[13];
                                mondaySubject6[2] = mondayArray[14];
                                mondaySubject6[3] = mondayArray[15];
                                mondaySubject6[4] = mondayArray[16];
                                mondaySubject6[5] = mondayArray[17];

                                String finalString16 = mondaySubject6[0] + " In " + mondayClass6[0] + " At " + mondayTime6[0];
                                String finalString17 = mondaySubject6[1] + " In " + mondayClass6[1] + " At " + mondayTime6[1];
                                String finalString18 = mondaySubject6[2] + " In " + mondayClass6[2] + " At " + mondayTime6[2];
                                String finalString19 = mondaySubject6[3] + " In " + mondayClass6[3] + " At " + mondayTime6[3];
                                String finalString20 = mondaySubject6[4] + " In " + mondayClass6[4] + " At " + mondayTime6[4];
                                String finalString21 = mondaySubject6[5] + " In " + mondayClass6[5] + " At " + mondayTime6[5];

                                newBtn.setText(finalString16);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString16);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString17);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString17);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString18);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString18);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString19);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString19);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString20);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString20);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString21);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString21);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 21:
                                numberOfLessons = 7;
                                String[] mondayTime7 = new String[numberOfLessons];
                                String[] mondayClass7 = new String[numberOfLessons];
                                String[] mondaySubject7 = new String[numberOfLessons];


                                mondayTime7[0] = mondayArray[0];
                                mondayTime7[1] = mondayArray[1];
                                mondayTime7[2] = mondayArray[2];
                                mondayTime7[3] = mondayArray[3];
                                mondayTime7[4] = mondayArray[4];
                                mondayTime7[5] = mondayArray[5];
                                mondayTime7[6] = mondayArray[6];

                                mondayClass7[0] = mondayArray[7];
                                mondayClass7[1] = mondayArray[8];
                                mondayClass7[2] = mondayArray[9];
                                mondayClass7[3] = mondayArray[10];
                                mondayClass7[4] = mondayArray[11];
                                mondayClass7[5] = mondayArray[12];
                                mondayClass7[6] = mondayArray[13];

                                mondaySubject7[0] = mondayArray[14];
                                mondaySubject7[1] = mondayArray[15];
                                mondaySubject7[2] = mondayArray[16];
                                mondaySubject7[3] = mondayArray[17];
                                mondaySubject7[4] = mondayArray[18];
                                mondaySubject7[5] = mondayArray[19];
                                mondaySubject7[6] = mondayArray[20];

                                String finalString22 = mondaySubject7[0] + " In " + mondayClass7[0] + " At " + mondayTime7[0];
                                String finalString23 = mondaySubject7[1] + " In " + mondayClass7[1] + " At " + mondayTime7[1];
                                String finalString24 = mondaySubject7[2] + " In " + mondayClass7[2] + " At " + mondayTime7[2];
                                String finalString25 = mondaySubject7[3] + " In " + mondayClass7[3] + " At " + mondayTime7[3];
                                String finalString26 = mondaySubject7[4] + " In " + mondayClass7[4] + " At " + mondayTime7[4];
                                String finalString27 = mondaySubject7[5] + " In " + mondayClass7[5] + " At " + mondayTime7[5];
                                String finalString28 = mondaySubject7[6] + " In " + mondayClass7[6] + " At " + mondayTime7[6];

                                newBtn.setText(finalString22);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString22);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString23);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString23);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString24);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString24);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString25);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString25);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString26);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString26);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString27);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString27);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString28);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString28);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 24:
                                numberOfLessons = 8;
                                String[] mondayTime8 = new String[numberOfLessons];
                                String[] mondayClass8 = new String[numberOfLessons];
                                String[] mondaySubject8 = new String[numberOfLessons];


                                mondayTime8[0] = mondayArray[0];
                                mondayTime8[1] = mondayArray[1];
                                mondayTime8[2] = mondayArray[2];
                                mondayTime8[3] = mondayArray[3];
                                mondayTime8[4] = mondayArray[4];
                                mondayTime8[5] = mondayArray[5];
                                mondayTime8[6] = mondayArray[6];
                                mondayTime8[7] = mondayArray[7];

                                mondayClass8[0] = mondayArray[8];
                                mondayClass8[1] = mondayArray[9];
                                mondayClass8[2] = mondayArray[10];
                                mondayClass8[3] = mondayArray[11];
                                mondayClass8[4] = mondayArray[12];
                                mondayClass8[5] = mondayArray[13];
                                mondayClass8[6] = mondayArray[14];
                                mondayClass8[7] = mondayArray[15];

                                mondaySubject8[0] = mondayArray[16];
                                mondaySubject8[1] = mondayArray[17];
                                mondaySubject8[2] = mondayArray[18];
                                mondaySubject8[3] = mondayArray[19];
                                mondaySubject8[4] = mondayArray[20];
                                mondaySubject8[5] = mondayArray[21];
                                mondaySubject8[6] = mondayArray[22];
                                mondaySubject8[7] = mondayArray[23];

                                String finalString29 = mondaySubject8[0] + " In " + mondayClass8[0] + " At " + mondayTime8[0];
                                String finalString30 = mondaySubject8[1] + " In " + mondayClass8[1] + " At " + mondayTime8[1];
                                String finalString31 = mondaySubject8[2] + " In " + mondayClass8[2] + " At " + mondayTime8[2];
                                String finalString32 = mondaySubject8[3] + " In " + mondayClass8[3] + " At " + mondayTime8[3];
                                String finalString33 = mondaySubject8[4] + " In " + mondayClass8[4] + " At " + mondayTime8[4];
                                String finalString34 = mondaySubject8[5] + " In " + mondayClass8[5] + " At " + mondayTime8[5];
                                String finalString35 = mondaySubject8[6] + " In " + mondayClass8[6] + " At " + mondayTime8[6];
                                String finalString36 = mondaySubject8[7] + " In " + mondayClass8[7] + " At " + mondayTime8[7];

                                newBtn.setText(finalString29);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString29);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString30);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString30);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString31);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString31);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString32);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString32);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString33);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString33);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString34);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString34);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString35);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString35);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString36);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString36);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 27:
                                numberOfLessons = 9;
                                String[] mondayTime9 = new String[numberOfLessons];
                                String[] mondayClass9 = new String[numberOfLessons];
                                String[] mondaySubject9 = new String[numberOfLessons];


                                mondayTime9[0] = mondayArray[0];
                                mondayTime9[1] = mondayArray[1];
                                mondayTime9[2] = mondayArray[2];
                                mondayTime9[3] = mondayArray[3];
                                mondayTime9[4] = mondayArray[4];
                                mondayTime9[5] = mondayArray[5];
                                mondayTime9[6] = mondayArray[6];
                                mondayTime9[7] = mondayArray[7];
                                mondayTime9[8] = mondayArray[8];

                                mondayClass9[0] = mondayArray[9];
                                mondayClass9[1] = mondayArray[10];
                                mondayClass9[2] = mondayArray[11];
                                mondayClass9[3] = mondayArray[12];
                                mondayClass9[4] = mondayArray[13];
                                mondayClass9[5] = mondayArray[14];
                                mondayClass9[6] = mondayArray[15];
                                mondayClass9[7] = mondayArray[16];
                                mondayClass9[8] = mondayArray[17];

                                mondaySubject9[0] = mondayArray[18];
                                mondaySubject9[1] = mondayArray[19];
                                mondaySubject9[2] = mondayArray[20];
                                mondaySubject9[3] = mondayArray[21];
                                mondaySubject9[4] = mondayArray[22];
                                mondaySubject9[5] = mondayArray[23];
                                mondaySubject9[6] = mondayArray[24];
                                mondaySubject9[7] = mondayArray[25];
                                mondaySubject9[8] = mondayArray[26];

                                String finalString37 = mondaySubject9[0] + " In " + mondayClass9[0] + " At " + mondayTime9[0];
                                String finalString38 = mondaySubject9[1] + " In " + mondayClass9[1] + " At " + mondayTime9[1];
                                String finalString39 = mondaySubject9[2] + " In " + mondayClass9[2] + " At " + mondayTime9[2];
                                String finalString40 = mondaySubject9[3] + " In " + mondayClass9[3] + " At " + mondayTime9[3];
                                String finalString41 = mondaySubject9[4] + " In " + mondayClass9[4] + " At " + mondayTime9[4];
                                String finalString42 = mondaySubject9[5] + " In " + mondayClass9[5] + " At " + mondayTime9[5];
                                String finalString43 = mondaySubject9[6] + " In " + mondayClass9[6] + " At " + mondayTime9[6];
                                String finalString44 = mondaySubject9[7] + " In " + mondayClass9[7] + " At " + mondayTime9[7];
                                String finalString45 = mondaySubject9[8] + " In " + mondayClass9[8] + " At " + mondayTime9[8];

                                newBtn.setText(finalString37);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString37);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString38);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString38);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString39);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString39);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString40);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString40);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString41);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString41);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString42);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString42);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString43);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString43);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString44);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString44);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString45);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString45);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 30:
                                numberOfLessons = 10;
                                String[] mondayTime10 = new String[numberOfLessons];
                                String[] mondayClass10 = new String[numberOfLessons];
                                String[] mondaySubject10 = new String[numberOfLessons];


                                mondayTime10[0] = mondayArray[0];
                                mondayTime10[1] = mondayArray[1];
                                mondayTime10[2] = mondayArray[2];
                                mondayTime10[3] = mondayArray[3];
                                mondayTime10[4] = mondayArray[4];
                                mondayTime10[5] = mondayArray[5];
                                mondayTime10[6] = mondayArray[6];
                                mondayTime10[7] = mondayArray[7];
                                mondayTime10[8] = mondayArray[8];
                                mondayTime10[9] = mondayArray[9];

                                mondayClass10[0] = mondayArray[10];
                                mondayClass10[1] = mondayArray[11];
                                mondayClass10[2] = mondayArray[12];
                                mondayClass10[3] = mondayArray[13];
                                mondayClass10[4] = mondayArray[14];
                                mondayClass10[5] = mondayArray[15];
                                mondayClass10[6] = mondayArray[16];
                                mondayClass10[7] = mondayArray[17];
                                mondayClass10[8] = mondayArray[18];
                                mondayClass10[9] = mondayArray[19];

                                mondaySubject10[0] = mondayArray[20];
                                mondaySubject10[1] = mondayArray[21];
                                mondaySubject10[2] = mondayArray[22];
                                mondaySubject10[3] = mondayArray[23];
                                mondaySubject10[4] = mondayArray[24];
                                mondaySubject10[5] = mondayArray[25];
                                mondaySubject10[6] = mondayArray[26];
                                mondaySubject10[7] = mondayArray[27];
                                mondaySubject10[8] = mondayArray[28];
                                mondaySubject10[9] = mondayArray[29];


                                String finalString46 = mondaySubject10[0] + " In " + mondayClass10[0] + " At " + mondayTime10[0];
                                String finalString47 = mondaySubject10[1] + " In " + mondayClass10[1] + " At " + mondayTime10[1];
                                String finalString48 = mondaySubject10[2] + " In " + mondayClass10[2] + " At " + mondayTime10[2];
                                String finalString49 = mondaySubject10[3] + " In " + mondayClass10[3] + " At " + mondayTime10[3];
                                String finalString50 = mondaySubject10[4] + " In " + mondayClass10[4] + " At " + mondayTime10[4];
                                String finalString51 = mondaySubject10[5] + " In " + mondayClass10[5] + " At " + mondayTime10[5];
                                String finalString52 = mondaySubject10[6] + " In " + mondayClass10[6] + " At " + mondayTime10[6];
                                String finalString53 = mondaySubject10[7] + " In " + mondayClass10[7] + " At " + mondayTime10[7];
                                String finalString54 = mondaySubject10[8] + " In " + mondayClass10[8] + " At " + mondayTime10[8];
                                String finalString55 = mondaySubject10[9] + " In " + mondayClass10[9] + " At " + mondayTime10[9];

                                newBtn.setText(finalString46);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString46);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString47);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString47);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString48);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString48);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString49);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString49);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString50);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString50);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString51);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString51);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString52);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString52);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString53);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString53);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString54);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString54);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString55);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString55);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 33:
                                numberOfLessons = 11;
                                String[] mondayTime11 = new String[numberOfLessons];
                                String[] mondayClass11 = new String[numberOfLessons];
                                String[] mondaySubject11 = new String[numberOfLessons];


                                mondayTime11[0] = mondayArray[0];
                                mondayTime11[1] = mondayArray[1];
                                mondayTime11[2] = mondayArray[2];
                                mondayTime11[3] = mondayArray[3];
                                mondayTime11[4] = mondayArray[4];
                                mondayTime11[5] = mondayArray[5];
                                mondayTime11[6] = mondayArray[6];
                                mondayTime11[7] = mondayArray[7];
                                mondayTime11[8] = mondayArray[8];
                                mondayTime11[9] = mondayArray[9];
                                mondayTime11[10] = mondayArray[10];

                                mondayClass11[0] = mondayArray[11];
                                mondayClass11[1] = mondayArray[12];
                                mondayClass11[2] = mondayArray[13];
                                mondayClass11[3] = mondayArray[14];
                                mondayClass11[4] = mondayArray[15];
                                mondayClass11[5] = mondayArray[16];
                                mondayClass11[6] = mondayArray[17];
                                mondayClass11[7] = mondayArray[18];
                                mondayClass11[8] = mondayArray[19];
                                mondayClass11[9] = mondayArray[20];
                                mondayClass11[10] = mondayArray[21];

                                mondaySubject11[0] = mondayArray[22];
                                mondaySubject11[1] = mondayArray[23];
                                mondaySubject11[2] = mondayArray[24];
                                mondaySubject11[3] = mondayArray[25];
                                mondaySubject11[4] = mondayArray[26];
                                mondaySubject11[5] = mondayArray[27];
                                mondaySubject11[6] = mondayArray[28];
                                mondaySubject11[7] = mondayArray[29];
                                mondaySubject11[8] = mondayArray[30];
                                mondaySubject11[9] = mondayArray[31];
                                mondaySubject11[10] = mondayArray[32];


                                String finalString56 = mondaySubject11[0] + " In " + mondayClass11[0] + " At " + mondayTime11[0];
                                String finalString57 = mondaySubject11[1] + " In " + mondayClass11[1] + " At " + mondayTime11[1];
                                String finalString58 = mondaySubject11[2] + " In " + mondayClass11[2] + " At " + mondayTime11[2];
                                String finalString59 = mondaySubject11[3] + " In " + mondayClass11[3] + " At " + mondayTime11[3];
                                String finalString60 = mondaySubject11[4] + " In " + mondayClass11[4] + " At " + mondayTime11[4];
                                String finalString61 = mondaySubject11[5] + " In " + mondayClass11[5] + " At " + mondayTime11[5];
                                String finalString62 = mondaySubject11[6] + " In " + mondayClass11[6] + " At " + mondayTime11[6];
                                String finalString63 = mondaySubject11[7] + " In " + mondayClass11[7] + " At " + mondayTime11[7];
                                String finalString64 = mondaySubject11[8] + " In " + mondayClass11[8] + " At " + mondayTime11[8];
                                String finalString65 = mondaySubject11[9] + " In " + mondayClass11[9] + " At " + mondayTime11[9];
                                String finalString66 = mondaySubject11[10] + " In " + mondayClass11[10] + " At " + mondayTime11[10];

                                newBtn.setText(finalString56);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString56);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString57);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString57);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString58);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString58);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString59);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString59);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString60);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString60);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString61);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString61);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString62);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString62);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString63);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString63);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString64);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString64);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString65);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString66);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn11.setText(finalString66);

                                newBtn11.setTextColor(Color.parseColor("#000000"));
                                newBtn11.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn11.setTextSize(26);
                                layout.addView(newBtn11, layoutParams);
                                newBtn11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString66);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;
                            case 36:
                                numberOfLessons = 12;
                                String[] mondayTime12 = new String[numberOfLessons];
                                String[] mondayClass12 = new String[numberOfLessons];
                                String[] mondaySubject12 = new String[numberOfLessons];


                                mondayTime12[0] = mondayArray[0];
                                mondayTime12[1] = mondayArray[1];
                                mondayTime12[2] = mondayArray[2];
                                mondayTime12[3] = mondayArray[3];
                                mondayTime12[4] = mondayArray[4];
                                mondayTime12[5] = mondayArray[5];
                                mondayTime12[6] = mondayArray[6];
                                mondayTime12[7] = mondayArray[7];
                                mondayTime12[8] = mondayArray[8];
                                mondayTime12[9] = mondayArray[9];
                                mondayTime12[10] = mondayArray[10];
                                mondayTime12[11] = mondayArray[11];

                                mondayClass12[0] = mondayArray[12];
                                mondayClass12[1] = mondayArray[13];
                                mondayClass12[2] = mondayArray[14];
                                mondayClass12[3] = mondayArray[15];
                                mondayClass12[4] = mondayArray[16];
                                mondayClass12[5] = mondayArray[17];
                                mondayClass12[6] = mondayArray[18];
                                mondayClass12[7] = mondayArray[19];
                                mondayClass12[8] = mondayArray[20];
                                mondayClass12[9] = mondayArray[21];
                                mondayClass12[10] = mondayArray[22];
                                mondayClass12[11] = mondayArray[23];

                                mondaySubject12[0] = mondayArray[24];
                                mondaySubject12[1] = mondayArray[25];
                                mondaySubject12[2] = mondayArray[26];
                                mondaySubject12[3] = mondayArray[27];
                                mondaySubject12[4] = mondayArray[28];
                                mondaySubject12[5] = mondayArray[29];
                                mondaySubject12[6] = mondayArray[30];
                                mondaySubject12[7] = mondayArray[31];
                                mondaySubject12[8] = mondayArray[32];
                                mondaySubject12[9] = mondayArray[33];
                                mondaySubject12[10] = mondayArray[34];
                                mondaySubject12[11] = mondayArray[35];


                                String finalString67 = mondaySubject12[0] + " In " + mondayClass12[0] + " At " + mondayTime12[0];
                                String finalString68 = mondaySubject12[1] + " In " + mondayClass12[1] + " At " + mondayTime12[1];
                                String finalString69 = mondaySubject12[2] + " In " + mondayClass12[2] + " At " + mondayTime12[2];
                                String finalString70 = mondaySubject12[3] + " In " + mondayClass12[3] + " At " + mondayTime12[3];
                                String finalString71 = mondaySubject12[4] + " In " + mondayClass12[4] + " At " + mondayTime12[4];
                                String finalString72 = mondaySubject12[5] + " In " + mondayClass12[5] + " At " + mondayTime12[5];
                                String finalString73 = mondaySubject12[6] + " In " + mondayClass12[6] + " At " + mondayTime12[6];
                                String finalString74 = mondaySubject12[7] + " In " + mondayClass12[7] + " At " + mondayTime12[7];
                                String finalString75 = mondaySubject12[8] + " In " + mondayClass12[8] + " At " + mondayTime12[8];
                                String finalString76 = mondaySubject12[9] + " In " + mondayClass12[9] + " At " + mondayTime12[9];
                                String finalString77 = mondaySubject12[10] + " In " + mondayClass12[10] + " At " + mondayTime12[10];
                                String finalString78 = mondaySubject12[11] + " In " + mondayClass12[11] + " At " + mondayTime12[11];

                                newBtn.setText(finalString67);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString67);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString68);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString68);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString69);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString69);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString70);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString70);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString71);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString71);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString72);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString72);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString73);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString73);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString74);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString74);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString75);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString75);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString76);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString76);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn11.setText(finalString77);

                                newBtn11.setTextColor(Color.parseColor("#000000"));
                                newBtn11.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn11.setTextSize(26);
                                layout.addView(newBtn11, layoutParams);
                                newBtn11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString77);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn12.setText(finalString78);

                                newBtn12.setTextColor(Color.parseColor("#000000"));
                                newBtn12.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn12.setTextSize(26);
                                layout.addView(newBtn12, layoutParams);
                                newBtn12.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString78);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;

                        }

                        View removeout = findViewById(R.id.checkingOut);
                        ViewGroup parentOut = (ViewGroup) removeout.getParent();
                        parentOut.removeView(removeout);

                        View removein = findViewById(R.id.checkingIn);
                        ViewGroup parentIn = (ViewGroup) removein.getParent();
                        parentIn.removeView(removein);

                        View removeinn = findViewById(R.id.textView9);
                        ViewGroup parentInn = (ViewGroup) removeinn.getParent();
                        parentInn.removeView(removeinn);


                    } catch (JSONException e) {
                        e.printStackTrace();
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

    public void checkOutFunc() {
        String fetchingUrl = config.DATA_URL + fullFetchingCode;
        String checkingInSendingData="OUT";

        //Creating a string request
        StringRequest stringRequest = new StringRequest(fetchingUrl,
                response -> {
                    JSONObject j = null;
                    // new Response.Listener<String>()
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = new JSONObject(response);

                        //Storing the Array of JSON String to our JSON Array
                        result = j.getJSONArray(config.JSON_ARRAY);

                        JSONObject json = result.getJSONObject(0);

                        Date date = new Date();
                        Integer dt = date.getDay();
                        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

                        switch (dt) {
                            case 1:
                                corectDate = days[0];
                                fetchingDate = config.MONDAY_SUB;
                                break;
                            case 2:
                                corectDate = days[1];
                                fetchingDate = config.TUESDAY_SUB;
                                break;
                            case 3:
                                corectDate = days[2];
                                fetchingDate = config.WEN_SUB;
                                break;
                            case 4:
                                corectDate = days[3];
                                fetchingDate = config.THURSDAY_SUB;
                                break;
                            case 5:
                                corectDate = days[4];
                                fetchingDate = config.FRIDAY_SUB;
                                break;


                        }
                        ;
                        //Log.d("i", String.valueOf(date.getDay()));


                        String dayy = json.getString(fetchingDate);//fetchingDate
                        Log.d("sub", dayy);
                        Integer a = 3;
                        Integer b = 0;

                        List<String> mondaylist = Arrays.asList(dayy.split(","));
                        String[] mondayArray = new String[mondaylist.size()];


                        for (int i = 0; i < mondaylist.size(); i++) {
                            mondayArray[i] = mondaylist.get(i).replaceAll("[\\[\\](){}]", "");

                            mondayArrayLength = mondayArray.length;
                        }

                        newtextView.setText("Which Lesson Are You Checking Out ? ");
                        newtextView.setTextSize(30);
                        newtextView.setTextColor(Color.parseColor("#ffffff"));
                        layout.addView(newtextView);

                        switch (mondayArrayLength) {
                            case 3:
                                numberOfLessons = 1;
                                String[] mondayTime = new String[numberOfLessons];
                                String[] mondayClass = new String[numberOfLessons];
                                String[] mondaySubject = new String[numberOfLessons];


                                mondayTime[0] = mondayArray[0];
                                mondayClass[0] = mondayArray[1];
                                mondaySubject[0] = mondayArray[2];

                                String finalString1 = mondaySubject[0] + " In " + mondayClass[0] + " At " + mondayTime[0];
                                newBtn.setText(finalString1);
                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString1);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 6:
                                numberOfLessons = 2;
                                String[] mondayTime2 = new String[numberOfLessons];
                                String[] mondayClass2 = new String[numberOfLessons];
                                String[] mondaySubject2 = new String[numberOfLessons];


                                mondayTime2[0] = mondayArray[0];
                                mondayTime2[1] = mondayArray[1];

                                mondayClass2[0] = mondayArray[2];
                                mondayClass2[1] = mondayArray[3];

                                mondaySubject2[0] = mondayArray[4];
                                mondaySubject2[1] = mondayArray[5];

                                String finalString2 = mondaySubject2[0] + " In " + mondayClass2[0] + " At " + mondayTime2[0];
                                String finalString3 = mondaySubject2[1] + " In " + mondayClass2[1] + " At " + mondayTime2[1];

                                newBtn.setText(finalString2);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString2);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString3);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString3);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 9:
                                numberOfLessons = 3;
                                String[] mondayTime3 = new String[numberOfLessons];
                                String[] mondayClass3 = new String[numberOfLessons];
                                String[] mondaySubject3 = new String[numberOfLessons];


                                mondayTime3[0] = mondayArray[0];
                                mondayTime3[1] = mondayArray[1];
                                mondayTime3[2] = mondayArray[2];

                                mondayClass3[0] = mondayArray[3];
                                mondayClass3[1] = mondayArray[4];
                                mondayClass3[2] = mondayArray[5];

                                mondaySubject3[0] = mondayArray[6];
                                mondaySubject3[1] = mondayArray[7];
                                mondaySubject3[2] = mondayArray[8];

                                String finalString4 = mondaySubject3[0] + " In " + mondayClass3[0] + " At " + mondayTime3[0];
                                String finalString5 = mondaySubject3[1] + " In " + mondayClass3[1] + " At " + mondayTime3[1];
                                String finalString6 = mondaySubject3[2] + " In " + mondayClass3[2] + " At " + mondayTime3[2];

                                newBtn.setText(finalString4);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString4);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                newBtn2.setText(finalString5);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString5);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString6);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString6);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;
                            case 12:
                                numberOfLessons = 4;
                                String[] mondayTime4 = new String[numberOfLessons];
                                String[] mondayClass4 = new String[numberOfLessons];
                                String[] mondaySubject4 = new String[numberOfLessons];


                                mondayTime4[0] = mondayArray[0];
                                mondayTime4[1] = mondayArray[1];
                                mondayTime4[2] = mondayArray[2];
                                mondayTime4[3] = mondayArray[3];

                                mondayClass4[0] = mondayArray[4];
                                mondayClass4[1] = mondayArray[5];
                                mondayClass4[2] = mondayArray[6];
                                mondayClass4[3] = mondayArray[7];

                                mondaySubject4[0] = mondayArray[8];
                                mondaySubject4[1] = mondayArray[9];
                                mondaySubject4[2] = mondayArray[10];
                                mondaySubject4[3] = mondayArray[11];

                                String finalString7 = mondaySubject4[0] + " In " + mondayClass4[0] + " At " + mondayTime4[0];
                                String finalString8 = mondaySubject4[1] + " In " + mondayClass4[1] + " At " + mondayTime4[1];
                                String finalString9 = mondaySubject4[2] + " In " + mondayClass4[2] + " At " + mondayTime4[2];
                                String finalString10 = mondaySubject4[3] + " In " + mondayClass4[3] + " At " + mondayTime4[3];

                                newBtn.setText(finalString7);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString7);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString8);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString8);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString9);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString9);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString10);
                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString10);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 15:
                                numberOfLessons = 5;
                                String[] mondayTime5 = new String[numberOfLessons];
                                String[] mondayClass5 = new String[numberOfLessons];
                                String[] mondaySubject5 = new String[numberOfLessons];


                                mondayTime5[0] = mondayArray[0];
                                mondayTime5[1] = mondayArray[1];
                                mondayTime5[2] = mondayArray[2];
                                mondayTime5[3] = mondayArray[3];
                                mondayTime5[4] = mondayArray[4];

                                mondayClass5[0] = mondayArray[5];
                                mondayClass5[1] = mondayArray[6];
                                mondayClass5[2] = mondayArray[7];
                                mondayClass5[3] = mondayArray[8];
                                mondayClass5[4] = mondayArray[9];

                                mondaySubject5[0] = mondayArray[10];
                                mondaySubject5[1] = mondayArray[11];
                                mondaySubject5[2] = mondayArray[12];
                                mondaySubject5[3] = mondayArray[13];
                                mondaySubject5[4] = mondayArray[14];

                                String finalString11 = mondaySubject5[0] + " In " + mondayClass5[0] + " At " + mondayTime5[0];
                                String finalString12 = mondaySubject5[1] + " In " + mondayClass5[1] + " At " + mondayTime5[1];
                                String finalString13 = mondaySubject5[2] + " In " + mondayClass5[2] + " At " + mondayTime5[2];
                                String finalString14 = mondaySubject5[3] + " In " + mondayClass5[3] + " At " + mondayTime5[3];
                                String finalString15 = mondaySubject5[4] + " In " + mondayClass5[4] + " At " + mondayTime5[4];

                                newBtn.setText(finalString11);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString11);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString12);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString12);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString13);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString13);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString14);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString14);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString15);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString15);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 18:
                                numberOfLessons = 6;
                                String[] mondayTime6 = new String[numberOfLessons];
                                String[] mondayClass6 = new String[numberOfLessons];
                                String[] mondaySubject6 = new String[numberOfLessons];


                                mondayTime6[0] = mondayArray[0];
                                mondayTime6[1] = mondayArray[1];
                                mondayTime6[2] = mondayArray[2];
                                mondayTime6[3] = mondayArray[3];
                                mondayTime6[4] = mondayArray[4];
                                mondayTime6[5] = mondayArray[5];

                                mondayClass6[0] = mondayArray[6];
                                mondayClass6[1] = mondayArray[7];
                                mondayClass6[2] = mondayArray[8];
                                mondayClass6[3] = mondayArray[9];
                                mondayClass6[4] = mondayArray[10];
                                mondayClass6[5] = mondayArray[11];

                                mondaySubject6[0] = mondayArray[12];
                                mondaySubject6[1] = mondayArray[13];
                                mondaySubject6[2] = mondayArray[14];
                                mondaySubject6[3] = mondayArray[15];
                                mondaySubject6[4] = mondayArray[16];
                                mondaySubject6[5] = mondayArray[17];

                                String finalString16 = mondaySubject6[0] + " In " + mondayClass6[0] + " At " + mondayTime6[0];
                                String finalString17 = mondaySubject6[1] + " In " + mondayClass6[1] + " At " + mondayTime6[1];
                                String finalString18 = mondaySubject6[2] + " In " + mondayClass6[2] + " At " + mondayTime6[2];
                                String finalString19 = mondaySubject6[3] + " In " + mondayClass6[3] + " At " + mondayTime6[3];
                                String finalString20 = mondaySubject6[4] + " In " + mondayClass6[4] + " At " + mondayTime6[4];
                                String finalString21 = mondaySubject6[5] + " In " + mondayClass6[5] + " At " + mondayTime6[5];

                                newBtn.setText(finalString16);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString16);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString17);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString17);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString18);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString18);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString19);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString19);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString20);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString20);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString21);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString21);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 21:
                                numberOfLessons = 7;
                                String[] mondayTime7 = new String[numberOfLessons];
                                String[] mondayClass7 = new String[numberOfLessons];
                                String[] mondaySubject7 = new String[numberOfLessons];


                                mondayTime7[0] = mondayArray[0];
                                mondayTime7[1] = mondayArray[1];
                                mondayTime7[2] = mondayArray[2];
                                mondayTime7[3] = mondayArray[3];
                                mondayTime7[4] = mondayArray[4];
                                mondayTime7[5] = mondayArray[5];
                                mondayTime7[6] = mondayArray[6];

                                mondayClass7[0] = mondayArray[7];
                                mondayClass7[1] = mondayArray[8];
                                mondayClass7[2] = mondayArray[9];
                                mondayClass7[3] = mondayArray[10];
                                mondayClass7[4] = mondayArray[11];
                                mondayClass7[5] = mondayArray[12];
                                mondayClass7[6] = mondayArray[13];

                                mondaySubject7[0] = mondayArray[14];
                                mondaySubject7[1] = mondayArray[15];
                                mondaySubject7[2] = mondayArray[16];
                                mondaySubject7[3] = mondayArray[17];
                                mondaySubject7[4] = mondayArray[18];
                                mondaySubject7[5] = mondayArray[19];
                                mondaySubject7[6] = mondayArray[20];

                                String finalString22 = mondaySubject7[0] + " In " + mondayClass7[0] + " At " + mondayTime7[0];
                                String finalString23 = mondaySubject7[1] + " In " + mondayClass7[1] + " At " + mondayTime7[1];
                                String finalString24 = mondaySubject7[2] + " In " + mondayClass7[2] + " At " + mondayTime7[2];
                                String finalString25 = mondaySubject7[3] + " In " + mondayClass7[3] + " At " + mondayTime7[3];
                                String finalString26 = mondaySubject7[4] + " In " + mondayClass7[4] + " At " + mondayTime7[4];
                                String finalString27 = mondaySubject7[5] + " In " + mondayClass7[5] + " At " + mondayTime7[5];
                                String finalString28 = mondaySubject7[6] + " In " + mondayClass7[6] + " At " + mondayTime7[6];

                                newBtn.setText(finalString22);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString22);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString23);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString23);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString24);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString24);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString25);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString25);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString26);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString26);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString27);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString27);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString28);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString28);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 24:
                                numberOfLessons = 8;
                                String[] mondayTime8 = new String[numberOfLessons];
                                String[] mondayClass8 = new String[numberOfLessons];
                                String[] mondaySubject8 = new String[numberOfLessons];


                                mondayTime8[0] = mondayArray[0];
                                mondayTime8[1] = mondayArray[1];
                                mondayTime8[2] = mondayArray[2];
                                mondayTime8[3] = mondayArray[3];
                                mondayTime8[4] = mondayArray[4];
                                mondayTime8[5] = mondayArray[5];
                                mondayTime8[6] = mondayArray[6];
                                mondayTime8[7] = mondayArray[7];

                                mondayClass8[0] = mondayArray[8];
                                mondayClass8[1] = mondayArray[9];
                                mondayClass8[2] = mondayArray[10];
                                mondayClass8[3] = mondayArray[11];
                                mondayClass8[4] = mondayArray[12];
                                mondayClass8[5] = mondayArray[13];
                                mondayClass8[6] = mondayArray[14];
                                mondayClass8[7] = mondayArray[15];

                                mondaySubject8[0] = mondayArray[16];
                                mondaySubject8[1] = mondayArray[17];
                                mondaySubject8[2] = mondayArray[18];
                                mondaySubject8[3] = mondayArray[19];
                                mondaySubject8[4] = mondayArray[20];
                                mondaySubject8[5] = mondayArray[21];
                                mondaySubject8[6] = mondayArray[22];
                                mondaySubject8[7] = mondayArray[23];

                                String finalString29 = mondaySubject8[0] + " In " + mondayClass8[0] + " At " + mondayTime8[0];
                                String finalString30 = mondaySubject8[1] + " In " + mondayClass8[1] + " At " + mondayTime8[1];
                                String finalString31 = mondaySubject8[2] + " In " + mondayClass8[2] + " At " + mondayTime8[2];
                                String finalString32 = mondaySubject8[3] + " In " + mondayClass8[3] + " At " + mondayTime8[3];
                                String finalString33 = mondaySubject8[4] + " In " + mondayClass8[4] + " At " + mondayTime8[4];
                                String finalString34 = mondaySubject8[5] + " In " + mondayClass8[5] + " At " + mondayTime8[5];
                                String finalString35 = mondaySubject8[6] + " In " + mondayClass8[6] + " At " + mondayTime8[6];
                                String finalString36 = mondaySubject8[7] + " In " + mondayClass8[7] + " At " + mondayTime8[7];

                                newBtn.setText(finalString29);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString29);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString30);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString30);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString31);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString31);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString32);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString32);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString33);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString33);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString34);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString34);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString35);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString35);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString36);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString36);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 27:
                                numberOfLessons = 9;
                                String[] mondayTime9 = new String[numberOfLessons];
                                String[] mondayClass9 = new String[numberOfLessons];
                                String[] mondaySubject9 = new String[numberOfLessons];


                                mondayTime9[0] = mondayArray[0];
                                mondayTime9[1] = mondayArray[1];
                                mondayTime9[2] = mondayArray[2];
                                mondayTime9[3] = mondayArray[3];
                                mondayTime9[4] = mondayArray[4];
                                mondayTime9[5] = mondayArray[5];
                                mondayTime9[6] = mondayArray[6];
                                mondayTime9[7] = mondayArray[7];
                                mondayTime9[8] = mondayArray[8];

                                mondayClass9[0] = mondayArray[9];
                                mondayClass9[1] = mondayArray[10];
                                mondayClass9[2] = mondayArray[11];
                                mondayClass9[3] = mondayArray[12];
                                mondayClass9[4] = mondayArray[13];
                                mondayClass9[5] = mondayArray[14];
                                mondayClass9[6] = mondayArray[15];
                                mondayClass9[7] = mondayArray[16];
                                mondayClass9[8] = mondayArray[17];

                                mondaySubject9[0] = mondayArray[18];
                                mondaySubject9[1] = mondayArray[19];
                                mondaySubject9[2] = mondayArray[20];
                                mondaySubject9[3] = mondayArray[21];
                                mondaySubject9[4] = mondayArray[22];
                                mondaySubject9[5] = mondayArray[23];
                                mondaySubject9[6] = mondayArray[24];
                                mondaySubject9[7] = mondayArray[25];
                                mondaySubject9[8] = mondayArray[26];

                                String finalString37 = mondaySubject9[0] + " In " + mondayClass9[0] + " At " + mondayTime9[0];
                                String finalString38 = mondaySubject9[1] + " In " + mondayClass9[1] + " At " + mondayTime9[1];
                                String finalString39 = mondaySubject9[2] + " In " + mondayClass9[2] + " At " + mondayTime9[2];
                                String finalString40 = mondaySubject9[3] + " In " + mondayClass9[3] + " At " + mondayTime9[3];
                                String finalString41 = mondaySubject9[4] + " In " + mondayClass9[4] + " At " + mondayTime9[4];
                                String finalString42 = mondaySubject9[5] + " In " + mondayClass9[5] + " At " + mondayTime9[5];
                                String finalString43 = mondaySubject9[6] + " In " + mondayClass9[6] + " At " + mondayTime9[6];
                                String finalString44 = mondaySubject9[7] + " In " + mondayClass9[7] + " At " + mondayTime9[7];
                                String finalString45 = mondaySubject9[8] + " In " + mondayClass9[8] + " At " + mondayTime9[8];

                                newBtn.setText(finalString37);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString37);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString38);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString38);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString39);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString39);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString40);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString40);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString41);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString41);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString42);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString42);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString43);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString43);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString44);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString44);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString45);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString45);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 30:
                                numberOfLessons = 10;
                                String[] mondayTime10 = new String[numberOfLessons];
                                String[] mondayClass10 = new String[numberOfLessons];
                                String[] mondaySubject10 = new String[numberOfLessons];


                                mondayTime10[0] = mondayArray[0];
                                mondayTime10[1] = mondayArray[1];
                                mondayTime10[2] = mondayArray[2];
                                mondayTime10[3] = mondayArray[3];
                                mondayTime10[4] = mondayArray[4];
                                mondayTime10[5] = mondayArray[5];
                                mondayTime10[6] = mondayArray[6];
                                mondayTime10[7] = mondayArray[7];
                                mondayTime10[8] = mondayArray[8];
                                mondayTime10[9] = mondayArray[9];

                                mondayClass10[0] = mondayArray[10];
                                mondayClass10[1] = mondayArray[11];
                                mondayClass10[2] = mondayArray[12];
                                mondayClass10[3] = mondayArray[13];
                                mondayClass10[4] = mondayArray[14];
                                mondayClass10[5] = mondayArray[15];
                                mondayClass10[6] = mondayArray[16];
                                mondayClass10[7] = mondayArray[17];
                                mondayClass10[8] = mondayArray[18];
                                mondayClass10[9] = mondayArray[19];

                                mondaySubject10[0] = mondayArray[20];
                                mondaySubject10[1] = mondayArray[21];
                                mondaySubject10[2] = mondayArray[22];
                                mondaySubject10[3] = mondayArray[23];
                                mondaySubject10[4] = mondayArray[24];
                                mondaySubject10[5] = mondayArray[25];
                                mondaySubject10[6] = mondayArray[26];
                                mondaySubject10[7] = mondayArray[27];
                                mondaySubject10[8] = mondayArray[28];
                                mondaySubject10[9] = mondayArray[29];


                                String finalString46 = mondaySubject10[0] + " In " + mondayClass10[0] + " At " + mondayTime10[0];
                                String finalString47 = mondaySubject10[1] + " In " + mondayClass10[1] + " At " + mondayTime10[1];
                                String finalString48 = mondaySubject10[2] + " In " + mondayClass10[2] + " At " + mondayTime10[2];
                                String finalString49 = mondaySubject10[3] + " In " + mondayClass10[3] + " At " + mondayTime10[3];
                                String finalString50 = mondaySubject10[4] + " In " + mondayClass10[4] + " At " + mondayTime10[4];
                                String finalString51 = mondaySubject10[5] + " In " + mondayClass10[5] + " At " + mondayTime10[5];
                                String finalString52 = mondaySubject10[6] + " In " + mondayClass10[6] + " At " + mondayTime10[6];
                                String finalString53 = mondaySubject10[7] + " In " + mondayClass10[7] + " At " + mondayTime10[7];
                                String finalString54 = mondaySubject10[8] + " In " + mondayClass10[8] + " At " + mondayTime10[8];
                                String finalString55 = mondaySubject10[9] + " In " + mondayClass10[9] + " At " + mondayTime10[9];

                                newBtn.setText(finalString46);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString46);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString47);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString47);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString48);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString48);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString49);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString49);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString50);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString50);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString51);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString51);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString52);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString52);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString53);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString53);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString54);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString54);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString55);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString55);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });


                                break;
                            case 33:
                                numberOfLessons = 11;
                                String[] mondayTime11 = new String[numberOfLessons];
                                String[] mondayClass11 = new String[numberOfLessons];
                                String[] mondaySubject11 = new String[numberOfLessons];


                                mondayTime11[0] = mondayArray[0];
                                mondayTime11[1] = mondayArray[1];
                                mondayTime11[2] = mondayArray[2];
                                mondayTime11[3] = mondayArray[3];
                                mondayTime11[4] = mondayArray[4];
                                mondayTime11[5] = mondayArray[5];
                                mondayTime11[6] = mondayArray[6];
                                mondayTime11[7] = mondayArray[7];
                                mondayTime11[8] = mondayArray[8];
                                mondayTime11[9] = mondayArray[9];
                                mondayTime11[10] = mondayArray[10];

                                mondayClass11[0] = mondayArray[11];
                                mondayClass11[1] = mondayArray[12];
                                mondayClass11[2] = mondayArray[13];
                                mondayClass11[3] = mondayArray[14];
                                mondayClass11[4] = mondayArray[15];
                                mondayClass11[5] = mondayArray[16];
                                mondayClass11[6] = mondayArray[17];
                                mondayClass11[7] = mondayArray[18];
                                mondayClass11[8] = mondayArray[19];
                                mondayClass11[9] = mondayArray[20];
                                mondayClass11[10] = mondayArray[21];

                                mondaySubject11[0] = mondayArray[22];
                                mondaySubject11[1] = mondayArray[23];
                                mondaySubject11[2] = mondayArray[24];
                                mondaySubject11[3] = mondayArray[25];
                                mondaySubject11[4] = mondayArray[26];
                                mondaySubject11[5] = mondayArray[27];
                                mondaySubject11[6] = mondayArray[28];
                                mondaySubject11[7] = mondayArray[29];
                                mondaySubject11[8] = mondayArray[30];
                                mondaySubject11[9] = mondayArray[31];
                                mondaySubject11[10] = mondayArray[32];


                                String finalString56 = mondaySubject11[0] + " In " + mondayClass11[0] + " At " + mondayTime11[0];
                                String finalString57 = mondaySubject11[1] + " In " + mondayClass11[1] + " At " + mondayTime11[1];
                                String finalString58 = mondaySubject11[2] + " In " + mondayClass11[2] + " At " + mondayTime11[2];
                                String finalString59 = mondaySubject11[3] + " In " + mondayClass11[3] + " At " + mondayTime11[3];
                                String finalString60 = mondaySubject11[4] + " In " + mondayClass11[4] + " At " + mondayTime11[4];
                                String finalString61 = mondaySubject11[5] + " In " + mondayClass11[5] + " At " + mondayTime11[5];
                                String finalString62 = mondaySubject11[6] + " In " + mondayClass11[6] + " At " + mondayTime11[6];
                                String finalString63 = mondaySubject11[7] + " In " + mondayClass11[7] + " At " + mondayTime11[7];
                                String finalString64 = mondaySubject11[8] + " In " + mondayClass11[8] + " At " + mondayTime11[8];
                                String finalString65 = mondaySubject11[9] + " In " + mondayClass11[9] + " At " + mondayTime11[9];
                                String finalString66 = mondaySubject11[10] + " In " + mondayClass11[10] + " At " + mondayTime11[10];

                                newBtn.setText(finalString56);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString56);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString57);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString57);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString58);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString58);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString59);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString59);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString60);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString60);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString61);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString61);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString62);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString62);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString63);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString63);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString64);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString64);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString65);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString66);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn11.setText(finalString66);

                                newBtn11.setTextColor(Color.parseColor("#000000"));
                                newBtn11.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn11.setTextSize(26);
                                layout.addView(newBtn11, layoutParams);
                                newBtn11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString66);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;
                            case 36:
                                numberOfLessons = 12;
                                String[] mondayTime12 = new String[numberOfLessons];
                                String[] mondayClass12 = new String[numberOfLessons];
                                String[] mondaySubject12 = new String[numberOfLessons];


                                mondayTime12[0] = mondayArray[0];
                                mondayTime12[1] = mondayArray[1];
                                mondayTime12[2] = mondayArray[2];
                                mondayTime12[3] = mondayArray[3];
                                mondayTime12[4] = mondayArray[4];
                                mondayTime12[5] = mondayArray[5];
                                mondayTime12[6] = mondayArray[6];
                                mondayTime12[7] = mondayArray[7];
                                mondayTime12[8] = mondayArray[8];
                                mondayTime12[9] = mondayArray[9];
                                mondayTime12[10] = mondayArray[10];
                                mondayTime12[11] = mondayArray[11];

                                mondayClass12[0] = mondayArray[12];
                                mondayClass12[1] = mondayArray[13];
                                mondayClass12[2] = mondayArray[14];
                                mondayClass12[3] = mondayArray[15];
                                mondayClass12[4] = mondayArray[16];
                                mondayClass12[5] = mondayArray[17];
                                mondayClass12[6] = mondayArray[18];
                                mondayClass12[7] = mondayArray[19];
                                mondayClass12[8] = mondayArray[20];
                                mondayClass12[9] = mondayArray[21];
                                mondayClass12[10] = mondayArray[22];
                                mondayClass12[11] = mondayArray[23];

                                mondaySubject12[0] = mondayArray[24];
                                mondaySubject12[1] = mondayArray[25];
                                mondaySubject12[2] = mondayArray[26];
                                mondaySubject12[3] = mondayArray[27];
                                mondaySubject12[4] = mondayArray[28];
                                mondaySubject12[5] = mondayArray[29];
                                mondaySubject12[6] = mondayArray[30];
                                mondaySubject12[7] = mondayArray[31];
                                mondaySubject12[8] = mondayArray[32];
                                mondaySubject12[9] = mondayArray[33];
                                mondaySubject12[10] = mondayArray[34];
                                mondaySubject12[11] = mondayArray[35];


                                String finalString67 = mondaySubject12[0] + " In " + mondayClass12[0] + " At " + mondayTime12[0];
                                String finalString68 = mondaySubject12[1] + " In " + mondayClass12[1] + " At " + mondayTime12[1];
                                String finalString69 = mondaySubject12[2] + " In " + mondayClass12[2] + " At " + mondayTime12[2];
                                String finalString70 = mondaySubject12[3] + " In " + mondayClass12[3] + " At " + mondayTime12[3];
                                String finalString71 = mondaySubject12[4] + " In " + mondayClass12[4] + " At " + mondayTime12[4];
                                String finalString72 = mondaySubject12[5] + " In " + mondayClass12[5] + " At " + mondayTime12[5];
                                String finalString73 = mondaySubject12[6] + " In " + mondayClass12[6] + " At " + mondayTime12[6];
                                String finalString74 = mondaySubject12[7] + " In " + mondayClass12[7] + " At " + mondayTime12[7];
                                String finalString75 = mondaySubject12[8] + " In " + mondayClass12[8] + " At " + mondayTime12[8];
                                String finalString76 = mondaySubject12[9] + " In " + mondayClass12[9] + " At " + mondayTime12[9];
                                String finalString77 = mondaySubject12[10] + " In " + mondayClass12[10] + " At " + mondayTime12[10];
                                String finalString78 = mondaySubject12[11] + " In " + mondayClass12[11] + " At " + mondayTime12[11];

                                newBtn.setText(finalString67);

                                newBtn.setTextColor(Color.parseColor("#000000"));
                                newBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn.setTextSize(26);
                                layout.addView(newBtn);
                                newBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString67);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn2.setText(finalString68);

                                newBtn2.setTextColor(Color.parseColor("#000000"));
                                newBtn2.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn2.setTextSize(26);
                                layout.addView(newBtn2, layoutParams);
                                newBtn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString68);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn3.setText(finalString69);

                                newBtn3.setTextColor(Color.parseColor("#000000"));
                                newBtn3.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn3.setTextSize(26);
                                layout.addView(newBtn3, layoutParams);
                                newBtn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString69);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn4.setText(finalString70);

                                newBtn4.setTextColor(Color.parseColor("#000000"));
                                newBtn4.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn4.setTextSize(26);
                                layout.addView(newBtn4, layoutParams);
                                newBtn4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString70);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn5.setText(finalString71);

                                newBtn5.setTextColor(Color.parseColor("#000000"));
                                newBtn5.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn5.setTextSize(26);
                                layout.addView(newBtn5, layoutParams);
                                newBtn5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString71);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn6.setText(finalString72);

                                newBtn6.setTextColor(Color.parseColor("#000000"));
                                newBtn6.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn6.setTextSize(26);
                                layout.addView(newBtn6, layoutParams);
                                newBtn6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString72);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn7.setText(finalString73);

                                newBtn7.setTextColor(Color.parseColor("#000000"));
                                newBtn7.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn7.setTextSize(26);
                                layout.addView(newBtn7, layoutParams);
                                newBtn7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString73);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn8.setText(finalString74);

                                newBtn8.setTextColor(Color.parseColor("#000000"));
                                newBtn8.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn8.setTextSize(26);
                                layout.addView(newBtn8, layoutParams);
                                newBtn8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString74);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn9.setText(finalString75);

                                newBtn9.setTextColor(Color.parseColor("#000000"));
                                newBtn9.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn9.setTextSize(26);
                                layout.addView(newBtn9, layoutParams);
                                newBtn9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString75);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn10.setText(finalString76);

                                newBtn10.setTextColor(Color.parseColor("#000000"));
                                newBtn10.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn10.setTextSize(26);
                                layout.addView(newBtn10, layoutParams);
                                newBtn10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString76);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn11.setText(finalString77);

                                newBtn11.setTextColor(Color.parseColor("#000000"));
                                newBtn11.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn11.setTextSize(26);
                                layout.addView(newBtn11, layoutParams);
                                newBtn11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString77);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                newBtn12.setText(finalString78);

                                newBtn12.setTextColor(Color.parseColor("#000000"));
                                newBtn12.setBackgroundColor(Color.parseColor("#ffffff"));
                                newBtn12.setTextSize(26);
                                layout.addView(newBtn12, layoutParams);
                                newBtn12.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(getApplicationContext(), scanning_qr.class);
                                        i.putExtra("data1", finalString78);
                                        i.putExtra("sendingData",checkingInSendingData);
                                        startActivity(i);

                                    }
                                });

                                break;

                        }

                        View removeout = findViewById(R.id.checkingOut);
                        ViewGroup parentOut = (ViewGroup) removeout.getParent();
                        parentOut.removeView(removeout);

                        View removein = findViewById(R.id.checkingIn);
                        ViewGroup parentIn = (ViewGroup) removein.getParent();
                        parentIn.removeView(removein);

                        View removeinn = findViewById(R.id.textView9);
                        ViewGroup parentInn = (ViewGroup) removeinn.getParent();
                        parentInn.removeView(removeinn);


                    } catch (JSONException e) {
                        e.printStackTrace();
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