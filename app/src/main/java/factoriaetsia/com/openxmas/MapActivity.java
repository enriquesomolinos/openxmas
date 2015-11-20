package factoriaetsia.com.openxmas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import factoriaetsia.com.map.*;

public class MapActivity  extends com.google.android.maps.MapActivity implements Runnable {


    LocationManager mLocationManager;
    Location mLocation;
    MyLocationListener mLocationListener;
    private MapLocationViewer mapView;
    private GeoPoint miUbicacion;
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        //  setContentView(R.layout.activity_map);

        int latitud = 0;
        int longitud = 0;
        if (extras != null) {

            latitud = (extras.getInt("latitud"));
            longitud = (extras.getInt("longitud"));
            miUbicacion = new GeoPoint(latitud, longitud);

        }
        FrameLayout frame = new FrameLayout(this);

        mapView = new MapLocationViewer(this,
                "0v-sfh2YiI0fRbCG8Jh2Unzuu3W_qRMOXhSZ8tQ");
        // frame.addView(findViewById(R.layout.modomapa),FrameLayout.LayoutParams.WRAP_CONTENT);
        frame.addView(mapView);


        LinearLayout back = new LinearLayout(this);
        back.setBackground(getResources().getDrawable(R.drawable.dibujooors2));
        frame.addView(back);
        setContentView(frame);


        mapView.setBuiltInZoomControls(false);
        mapView.displayZoomControls(false);

        mapView.invalidate();

        GeoPoint center = new GeoPoint(40411403, -3693940);


        mapView.postInvalidate();
        if(miUbicacion!=null) {
            mapView.getController().animateTo(miUbicacion);
            mapView.getController().setZoom(16);
        }
        /*mapView.getManager().addMapLocation(
                new MapLocation(mapView, "Punto de encuentro 1", 40411374, -3693841,
                        MapLocation.TYPE_BUBBLE_OFF));
        mapView.getManager().addMapLocation(
                new MapLocation(mapView, "Punto de encuentro 2", 40411460, -3693835,
                        MapLocation.TYPE_BUBBLE));
        mapView.getManager().addMapLocation(
                new MapLocation(mapView, "Punto de encuentro 3", 40411467 , -3693961,
                        MapLocation.TYPE_BUBBLE));
        mapView.getManager().addMapLocation(
                new MapLocation(mapView, "Punto de encuentro 4", 40411380, -3693966,
                        MapLocation.TYPE_BUBBLE));
*/
        if(miUbicacion!=null) {
            mapView.getManager().addMapLocation(
                    new MapLocation(mapView, "Estas aqui", miUbicacion,
                            MapLocation.TYPE_ANDROID));
        }
        mapView.invalidate();

        writeSignalGPS();

    }
    private void writeSignalGPS() {

        Thread thread = new Thread(this);
        thread.start();

    }

    public void run() {




        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Looper.prepare();

            mLocationListener = new MyLocationListener();

            try {

                mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 100000, 0, mLocationListener);
                Looper.loop();
                Looper.myLooper().quit();
            }catch(SecurityException e){
                Log.v("error", e.getMessage());
            }

        } else if (mLocationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {



            Looper.prepare();

            mLocationListener = new MyLocationListener();

            try {
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 100, 0,
                        mLocationListener);
                Looper.loop();
                Looper.myLooper().quit();
            }catch(SecurityException e){
                Log.v("error",e.getMessage());
            }

        } else {

			/*
			 * Toast.makeText(this.getApplication().getApplicationContext(),
			 * getResources().getString(R.string.gps_signal_not_found),
			 * Toast.LENGTH_LONG).show();
			 */


            Intent settingsIntent = new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivityForResult(settingsIntent, 0);

        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // pd.dismiss();




            mLocationManager.removeUpdates(mLocationListener);

        }
    };

    private void pintarUnicoDestino(GeoPoint p) {
        mapView.getManager().addMapLocation(
                new MapLocation(mapView, "Estas Aqui", p,
                        MapLocation.TYPE_ANDROID));


    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(
                        getBaseContext(),
                        "Señal  encontrada:"+loc.getLatitude()+"-"+loc.getLongitude(),
                        Toast.LENGTH_LONG).show();

                double latitude = loc.getLatitude();
                double longitude = loc.getLongitude();

                miUbicacion = new GeoPoint((int) (latitude * 1E6),
                        (int) (longitude * 1E6));


                    pintarUnicoDestino(miUbicacion);


                MapLocation localizacion = mapView.getManager().getMapLocations()
                        .get(0);

                localizacion.setPoint(miUbicacion);
                mapView.postInvalidate();
                mapView.getController().animateTo(miUbicacion);
                 writeSignalGPS();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {

			/*
			 * if(provider.equals(LocationManager.GPS_PROVIDER))
			 * Toast.makeText(getApplicationContext(), "Gps Desactivado",
			 * Toast.LENGTH_SHORT).show(); else
			 * if(provider.equals(LocationManager.NETWORK_PROVIDER))
			 * Toast.makeText(getApplicationContext(), "WIFI Desactivado",
			 * Toast.LENGTH_SHORT).show();
			 */
        }

        @Override
        public void onProviderEnabled(String provider) {
			/*
			 * if(provider.equals(LocationManager.GPS_PROVIDER))
			 * Toast.makeText(getApplicationContext(), "Gps Activo",
			 * Toast.LENGTH_SHORT).show(); else
			 * if(provider.equals(LocationManager.NETWORK_PROVIDER))
			 * Toast.makeText(getApplicationContext(), "WIFI Activo",
			 * Toast.LENGTH_SHORT).show();
			 */

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v("MyLocationListener", "onStatusChanged");
        }
    }

}
