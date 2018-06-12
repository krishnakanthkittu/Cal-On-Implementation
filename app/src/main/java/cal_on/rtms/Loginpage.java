package cal_on.rtms;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import cal_on.usbterminal.R;

public class Loginpage extends AppCompatActivity {
Button login;
EditText userid,password;
    String user="Cal-On";
    String pass="Cal-On";
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        login=(Button)findViewById(R.id.add);
        userid=(EditText)findViewById(R.id.tetx);
        password=(EditText)findViewById(R.id.second);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userid.length()==0 && password.length()==0){
                    String toSpeak = "Please Enter the UserID and Password";
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(getApplicationContext(), "Please Enter the UserID and Password", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(Loginpage.this, Homepage.class);
                    startActivity(i);

                }
                else if(userid.length()==6 && password.length()==6){
                    String user1=userid.getText().toString();
                    String pass1=password.getText().toString();

                    if(user1.equals(user) && pass1.equals(pass)) {
                        Intent i = new Intent(Loginpage.this, Homepage.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Login Sucessfully Done", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login Fail",Toast.LENGTH_SHORT).show();
                    }
                }
               else{
                    String toSpeak = "Please Enter the Correct UserID and Password";
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Toast.makeText(getApplicationContext(), "Please Enter the Correct UserID and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
