package themole.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import themole.example.myapplication.R;


public class MainActivity2 extends MainActivity{

    TextView userFullName;
    TextView userEmail;
    TextView userSubjectTaught;
    TextView identityCode;

    Button scanQrCode;
    String name;
    String email;
    String sub;
    String idt;
    String codee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userFullName  = (TextView)findViewById(R.id.userFullName);
        userEmail =(TextView)findViewById(R.id.userEmail);
        userSubjectTaught  =(TextView)findViewById(R.id.userSubjectTaught);
        identityCode  =(TextView)findViewById(R.id.userIdentityCode);
        scanQrCode =(Button)findViewById(R.id.scanQrCode);

        Intent i = getIntent();
        name = i.getStringExtra("userFullName");
        email = i.getStringExtra("email");
        sub = i.getStringExtra("subject");
        idt= i.getStringExtra("idt");


        userFullName.setText(name);
        userEmail.setText(email);
        userSubjectTaught.setText(sub);
        identityCode.setText(idt);

        userFullName.setTextColor(Color.BLUE);
        userEmail.setTextColor(Color.BLUE);
        userSubjectTaught.setTextColor(Color.BLUE);
        identityCode.setTextColor(Color.BLUE);



        scanQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCODE();

            }
        });

    }

    private void scanCODE(){
        Intent i = new Intent(getApplicationContext(),MainActivity3.class);
        Intent ii= getIntent();
        //Intent ii = new Intent(getApplicationContext(),MainActivity3.class);
        codee=ii.getStringExtra("codees");
        i.putExtra("fetchingCode",codee);
        startActivity(i);
    }


}