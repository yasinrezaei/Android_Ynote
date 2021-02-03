package com.example.ynote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendEmail extends AppCompatActivity {


    EditText email,subject,text;
    Button send;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendemail);

        configureview();
        bundle=getIntent().getExtras();
        email.setText(bundle.getString("ekey"));
        subject.setText(bundle.getString("subjectkey"));
        text.setText(bundle.getString("textkey"));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String U_email=email.getText().toString();
                String U_subject=subject.getText().toString();
                String U_message=text.getText().toString();

                JavaMailAPI javaMailAPI=new JavaMailAPI(SendEmail.this,U_email,U_subject,U_message);
                javaMailAPI.execute();

                Toast.makeText(SendEmail.this,"Email Sent!" ,Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void configureview(){
        email=(EditText)findViewById(R.id.edt_email_);
        subject=(EditText)findViewById(R.id.edt_subject);
        text=(EditText)findViewById(R.id.edt_text);
        send=(Button)findViewById(R.id.btn_send_mail);


    }
}
