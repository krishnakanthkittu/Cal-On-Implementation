package cal_on.rtms;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cal_on.usbterminal.R;

public class Gates extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    RadioButton deviceidu,ipaddres,house,resid,bulk,apart;
    LinearLayout rfid,submitl,rfid1,rfid2,rfid6,rdif8;
    Spinner spinner,spinner1,spinner2;
    EditText tagid,retagid,gateno,doorno,unitgate,population;
    String[] country =  {"Select Ward","Ward 1","Ward 2"};
    String[] country1 = {"Select Micropocket" ,"Micropocket 1","Micropocket 2"};
    String[] country2 = {"Select Street ","Anna Street","Phase 4"};
    private static final int REQUEST_LOCATION = 1;
    TextView getlo;
    EditText Latitude1,Longitude1;
    LocationManager locationManager;
    String lattitude,longitude;
    Button submit;

    DBHelper mydb;
    CustomerDataBase cddb;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_new1);
        toolbar.setTitle("   Gates");
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        deviceidu=(RadioButton)findViewById(R.id.radiohouse);
        ipaddres=(RadioButton)findViewById(R.id.radiocommercial);
        rfid=(LinearLayout)findViewById(R.id.rdif);
        rfid1=(LinearLayout)findViewById(R.id.rdif1);
        rfid2=(LinearLayout)findViewById(R.id.rdif2);
        rfid6=(LinearLayout)findViewById(R.id.rdif6);
        rdif8=(LinearLayout)findViewById(R.id.rdif8);
        submit=(Button)findViewById(R.id.submit);

        getlo=(TextView)findViewById(R.id.get);
        Latitude1=(EditText)findViewById(R.id.latitude) ;
        Longitude1=(EditText)findViewById(R.id.longitude) ;

        tagid=(EditText)findViewById(R.id.input_name) ;
        retagid=(EditText)findViewById(R.id.input_email);
        gateno=(EditText)findViewById(R.id.input_name6);
        doorno=(EditText)findViewById(R.id.input_email7);
        unitgate=(EditText)findViewById(R.id.input_email8);
        population=(EditText)findViewById(R.id.input_email9);

        mydb = new DBHelper(this);
        cddb=new CustomerDataBase(this);

    submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String error="Data Dones Not Match";
        String string=tagid.getText().toString();
        String string1=retagid.getText().toString();
        if(string.equals(string1)){
            String editText1 = tagid.getText().toString();
            String str="";
            String editText2 = gateno.getText().toString();
            String editText3 = doorno.getText().toString();
            String editText5 = unitgate.getText().toString();
            String editText4 = spinner.getSelectedItem().toString();

            String editText6 = population.getText().toString();
            String editText7 = Latitude1.getText().toString();
            String editText8 = Longitude1.getText().toString();
            String editText9 = spinner1.getSelectedItem().toString();
            String editText10 = spinner2.getSelectedItem().toString();

            result = cddb.insertContact(editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10);

            if (tagid.length() != 0 && gateno.length() != 0 && doorno.length() != 0 && unitgate.length() !=0) {

                tagid.setText("");
                retagid.setText("");
                gateno.setText("");
                doorno.setText("");
                unitgate.setText("");
                population.setText("");
                Latitude1.setText("");
                Longitude1.setText("");


                Toast.makeText(getApplicationContext()," Details  Successfully Stored",Toast.LENGTH_SHORT).show();
            }
            else if (tagid.length() == 0 && gateno.length()== 0 && doorno.length() ==0 && unitgate.length() ==0) {
                Toast.makeText(getApplicationContext(),"Please Enter the Details",Toast.LENGTH_SHORT).show();

            }
        }
        else{
            tagid.setError(error);
            retagid.setError(error);
        }

    }
});
        getlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
            }
        });

        submitl=(LinearLayout)findViewById(R.id.submitl);
        deviceidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rfid.setVisibility(View.VISIBLE);
                rfid1.setVisibility(View.VISIBLE);
                rfid2.setVisibility(View.VISIBLE);
                rfid6.setVisibility(View.VISIBLE);
                rdif8.setVisibility(View.VISIBLE);
                submitl.setVisibility(View.VISIBLE);
            }
        });
        ipaddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfid.setVisibility(View.INVISIBLE);
                rfid1.setVisibility(View.INVISIBLE);
                rfid2.setVisibility(View.INVISIBLE);
                rfid6.setVisibility(View.INVISIBLE);
                rdif8.setVisibility(View.INVISIBLE);
                submitl.setVisibility(View.INVISIBLE);
            }
        });
        spinner= (Spinner) findViewById(R.id.panchayat);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);
        spinner1= (Spinner) findViewById(R.id.panchayat1);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country1);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(aa1);



        spinner2= (Spinner) findViewById(R.id.panchayat2);
        spinner2.setOnItemSelectedListener(this);


        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country2);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(aa2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(Gates.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (Gates.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Gates.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Latitude1.setText (lattitude);
                Longitude1.setText (longitude);

            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                Latitude1.setText (lattitude);
                Longitude1.setText (longitude);

            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                Latitude1.setText (lattitude);
                Longitude1.setText (longitude);

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
