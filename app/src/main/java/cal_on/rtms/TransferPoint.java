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

public class TransferPoint extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    RadioButton deviceidu,ipaddres;
    LinearLayout rfid,submitl,rfid1,rfid2;
    Spinner spinner,spinner1;
    String[] country = {"Select Cluster No              "};
    String[] country1 = {"Select Micropockets         "};
    private static final int REQUEST_LOCATION = 1;
    TextView getlo;
    EditText Latitude1,Longitude1;
    LocationManager locationManager;
    String lattitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_point);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo_new1);
        toolbar.setTitle("   Transfer Point");
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        deviceidu=(RadioButton)findViewById(R.id.radiohouse);
        ipaddres=(RadioButton)findViewById(R.id.radiocommercial);
        rfid=(LinearLayout)findViewById(R.id.rdif);
        rfid1=(LinearLayout)findViewById(R.id.rdif1);
        rfid2=(LinearLayout)findViewById(R.id.rdif2);
        getlo=(TextView)findViewById(R.id.get);
        Latitude1=(EditText)findViewById(R.id.latitude) ;
        Longitude1=(EditText)findViewById(R.id.longitude) ;
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
                submitl.setVisibility(View.VISIBLE);
            }
        });
        ipaddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfid.setVisibility(View.INVISIBLE);
                rfid1.setVisibility(View.INVISIBLE);
                rfid2.setVisibility(View.INVISIBLE);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(TransferPoint.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (TransferPoint.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(TransferPoint.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
