package com.example.markers;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final ListView lv = findViewById(R.id.list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<msg> calling = api.getHeroes();

        calling.enqueue(new Callback<msg>() {
            @Override
            public void onResponse(Call<msg> call, Response<msg> response) {
                msg data = response.body();

                ArrayList<msg.msg_info> hero = data.getMsg();

                String[] latitude   = new String[hero.size()];
                int j =0;
                String[] longitude =new String[hero.size()];

                for (int i = 0; i < hero.size(); i++) {

                    latitude[i]  =     hero.get(i).getLatitude();
                    longitude[i] =     hero.get(i).getLongitude();
                    Log.e("Latitude", latitude[i]);

                }
                ArrayAdapter ar = new ArrayAdapter(MapsActivity.this, android.R.layout.simple_list_item_1, latitude);

                for (j=0 ; j <hero.size();j++)
                {
                    longitude[j] =  hero.get(j).getLongitude();

                }
                ArrayAdapter a = new ArrayAdapter(MapsActivity.this,android.R.layout.simple_list_item_1,longitude);
                lv.setAdapter(ar);



            }
            @Override
            public void onFailure(Call<msg> call, Throwable t) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int i = 0;
//        Double la = Double.parseDouble(lat[i]);
//        Double lt = Double.parseDouble(lng[i]);

//         Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0,0);
        mMap.addMarker(new MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.getMaxZoomLevel();
        mMap.getMinZoomLevel();



    }


}
