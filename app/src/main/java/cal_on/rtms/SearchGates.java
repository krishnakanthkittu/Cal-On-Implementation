package cal_on.rtms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cal_on.usbterminal.R;

public class SearchGates extends AppCompatActivity {
    RadioButton deviceidu,ipaddres;
    LinearLayout rfid,rfid80;
    EditText tagsno;
    CustomerDataBase cddb;
    SQLiteDatabase data;
    String empty;
    TextView wardnum,micropocket,street,gateno,door,noofgates,population,latitude,longitude;

    private static final String TAG = "SearchGates";
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_new1);
        toolbar.setTitle("   Search Gates");
        setSupportActionBar(toolbar);
        wardnum=(TextView)findViewById(R.id.wardnum);
        micropocket=(TextView)findViewById(R.id.micropocket);
        street=(TextView)findViewById(R.id.street);
        gateno=(TextView)findViewById(R.id.gateno);
        door=(TextView)findViewById(R.id.door);
        noofgates=(TextView)findViewById(R.id.noofgates);
        population=(TextView)findViewById(R.id.population);
        latitude=(TextView)findViewById(R.id.latitude);
        longitude=(TextView)findViewById(R.id.longitude);

        tagsno=(EditText)findViewById(R.id.sno);
        deviceidu=(RadioButton)findViewById(R.id.radiohouse);
        ipaddres=(RadioButton)findViewById(R.id.radiocommercial);
        rfid=(LinearLayout)findViewById(R.id.rdif);
        rfid80=(LinearLayout)findViewById(R.id.rdif80);
        search=(Button)findViewById(R.id.search);
        cddb=new CustomerDataBase(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(tagsno.length()==0) {
    String error="Please Enter Tag S.No";
   Toast.makeText(getApplicationContext(),"Please Enter Tag S.No",Toast.LENGTH_SHORT).show();
   tagsno.setError(error);
}
else {
    empty=tagsno.getText().toString();
    cddb=new CustomerDataBase(getApplicationContext());
    data=cddb.getReadableDatabase();
    Cursor cursor=cddb.getdata(empty,data);
    if (cursor.moveToFirst()){
        String Village=cursor.getString(0);
        String mobilenum=cursor.getString(1);
        String setup1=cursor.getString(3);
        String amount1=cursor.getString(2);
        String Village1=cursor.getString(4);
        String mobilenum1=cursor.getString(5);
        String setup11=cursor.getString(6);
        String amount11=cursor.getString(7);
        String amount111=cursor.getString(8);

        gateno.setText(Village);
        door.setText(mobilenum);
        noofgates.setText(setup1);
        wardnum.setText(amount1);
        population.setText(Village1);
        latitude.setText(mobilenum1);
        longitude.setText(setup11);
        micropocket.setText(amount11);
        street.setText(amount111);

        rfid80.setVisibility(View.VISIBLE);
    }
    else {
        Toast.makeText(getApplication(),"Details Not Found",Toast.LENGTH_LONG).show();
    }

}
            }
        });
        deviceidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rfid.setVisibility(View.VISIBLE);

            }
        });
        ipaddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfid.setVisibility(View.INVISIBLE);

            }
        });
    }
}
