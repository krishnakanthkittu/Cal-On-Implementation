package cal_on.rtms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import cal_on.usbterminal.R;

public class Updaterfid extends AppCompatActivity {
    RadioButton deviceidu,ipaddres;
    LinearLayout rfid,submitl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterfid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_new1);
        toolbar.setTitle("   Update RFID");
        setSupportActionBar(toolbar);
        deviceidu=(RadioButton)findViewById(R.id.radiohouse);
        ipaddres=(RadioButton)findViewById(R.id.radiocommercial);
        rfid=(LinearLayout)findViewById(R.id.rdif);
        submitl=(LinearLayout)findViewById(R.id.submitl);
        deviceidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              rfid.setVisibility(View.VISIBLE);
                submitl.setVisibility(View.VISIBLE);
            }
        });
        ipaddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfid.setVisibility(View.INVISIBLE);
                submitl.setVisibility(View.INVISIBLE);
            }
        });
    }
}
