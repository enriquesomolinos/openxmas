package factoriaetsia.com.openxmas;

import  factoriaetsia.com.util.*;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable{

    ProgressDialog pd;
    LocationManager mLocationManager;
    Location mLocation;
    MyLocationListener mLocationListener;

    Location currentLocation = null;



    private static final int MENU_MAPA = 1;
    private static final int MENU_VER_REALIDAD_AUMENTADA = 2;
    private static final int MENU_WELCOME = 3;
    private static final int MENU_VER_OPCIONES = 4;
    private static final int MENU_SALIR = 5;
    private static final int MENU_SCORE = 6;
    private static final int MENU_NOTIFICACION = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        buscarGPS();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification() {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(getBaseContext(), MapActivity.class);
        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(getBaseContext())
                .setContentTitle("OpenXMax")
                .setContentText("Quieres Ganar Puntos para obtener beneficios OpenBank? Descubre en el Mapa donde estan localizados y Juega.")
                .setSmallIcon(R.drawable.opxmas)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.opxmas, "Juega", pIntent)
                .build();
        notificationManager.notify(0, n);
    }

    

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(1, MainActivity.MENU_WELCOME, 0, R.string.menu_welcome)
                .setIcon(android.R.drawable.ic_dialog_map);
        menu.add(2,
                MainActivity.MENU_MAPA, 0,
                R.string.menu_ver_lugares).setIcon(
                android.R.drawable.ic_menu_directions);

        menu.add(3, MainActivity.MENU_VER_REALIDAD_AUMENTADA, 0,
                R.string.menu_ver_realidad_aumentada).setIcon(
                android.R.drawable.ic_menu_camera);

        menu.add(4, MainActivity.MENU_VER_OPCIONES, 0,
                R.string.menu_opciones).setIcon(
                android.R.drawable.ic_menu_search);
        menu.add(5, MainActivity.MENU_SCORE, 0, R.string.Score).setIcon(
                android.R.drawable.ic_lock_power_off);

        menu.add(6, MainActivity.MENU_NOTIFICACION,0,R.string.Notificacion).setIcon(
                R.drawable.opxmas);

        menu.add(7, MainActivity.MENU_SALIR, 0, R.string.Salir).setIcon(
                android.R.drawable.ic_lock_power_off);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MainActivity.MENU_MAPA:

                Intent intent = new Intent(getBaseContext(),
                        MapActivity.class);
                if (currentLocation != null) {
                    intent.putExtra("latitud",
                            (int) (currentLocation.getLatitude() * 1E6));
                    intent.putExtra("longitud",
                            (int) (currentLocation.getLongitude() * 1E6));

                }
			/*
			 * startActivity(intent);
			 */

                /*if (currentLocation != null) {
                    // Intent intent = new Intent(getBaseContext(),
                    // GoogleMapsActivity.class);
                    intent.putExtra("latitud",
                            (int) (currentLocation.getLatitude() * 1E6));
                    intent.putExtra("longitud",
                            (int) (currentLocation.getLongitude() * 1E6));
                    intent.putExtra("latitud",
                            currentLocation.getAltitude());
*/
                // Intent intent = new Intent(android.content.Intent.ACTION_VIEW
                // ,Uri.parse("geo:"+ currentLocation.getLongitude() + "," +
                // currentLocation.getLatitude()+ "?z=20"));


                startActivity(intent);

                return true;
            case MainActivity.MENU_WELCOME:


                Intent intentMasCercanos = new Intent(getBaseContext(),
                        WelcomeActivity.class);

                startActivity(intentMasCercanos);

                return true;
            case MainActivity.MENU_SCORE:


                Intent scoreAct = new Intent(getBaseContext(),
                        ScoreActivity.class);

                startActivity(scoreAct);

                return true;

            case MainActivity.MENU_VER_REALIDAD_AUMENTADA:

                Intent intentAumentada = new Intent(getBaseContext(),
                        ARActivity.class);

                if (currentLocation != null) {
                    intentAumentada.putExtra("latitud",
                            (int) (currentLocation.getLatitude() * 1E6));
                    intentAumentada.putExtra("longitud",
                            (int) (currentLocation.getLongitude() * 1E6));

                }
                startActivity(intentAumentada);

                return true;


            case MainActivity.MENU_VER_OPCIONES:

                Intent opciones = new Intent(getBaseContext(),
                        SettingsActivity.class);

                startActivity(opciones);


                return true;
            case MainActivity.MENU_NOTIFICACION:
                sendNotification();
                return true;

            case MainActivity.MENU_SALIR:
                // handleGetReviews();

                System.exit(0);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    private void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }

    private void buscarGPS() {

        /*DialogInterface.OnCancelListener dialogCancel = new DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialog) {
                Toast.makeText(
                        getBaseContext(),
                        "Señal no encotrada",
                        Toast.LENGTH_LONG).show();
                handler.sendEmptyMessage(0);
                Intent settingsIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivityForResult(settingsIntent, 0);
            }

        };*/

      /*  pd = ProgressDialog.show(this,
                "Buscando señal","Estamos procediendo a buscar su localización",
                true, false, dialogCancel);*/

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
                        LocationManager.GPS_PROVIDER, 10000, 40, mLocationListener);
                Looper.loop();
                Looper.myLooper().quit();
            }catch(SecurityException e){
                Log.v("error",e.getMessage());
            }

        } else if (mLocationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            Looper.prepare();

            mLocationListener = new MyLocationListener();
            try {
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 10000, 40,
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
            if (currentLocation != null) {

				/*
				 * String slatitude=
				 * String.valueOf(currentLocation.getLatitude()); String
				 * slongitude= String.valueOf(currentLocation.getLongitude());
				 *
				 * currentLocation.setLatitude(Double.parseDouble(slatitude.
				 * substring(0, 8)));
				 * currentLocation.setLongitude(Double.parseDouble
				 * (slongitude.substring(0, 8)));
				 */

                Log.v("Latitud:", "" + currentLocation.getLatitude());
                Log.v("Longitud:", " " + currentLocation.getLongitude());
            }



        }
    };

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                    pd = null;
                    Toast.makeText(
                            getBaseContext(),
                           "Señal no encontrada",
                            Toast.LENGTH_LONG).show();

                    // openOptionsMenu();
                }

                setCurrentLocation(loc);

                handler.sendEmptyMessage(0);
                // writeSignalGPS();
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
