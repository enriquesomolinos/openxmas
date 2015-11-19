package factoriaetsia.com.openxmas;


import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import  factoriaetsia.com.util.*;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SeekBar seekAhorros = (SeekBar) findViewById(R.id.seekAhorros);
        final SeekBar seekMesesCasa = (SeekBar) findViewById(R.id.seekMesesCasa);
        final SeekBar seekMesesTrabajo = (SeekBar) findViewById(R.id.seekMesesTrabajo);
        final SeekBar seekSalario = (SeekBar) findViewById(R.id.seekSalario);
        setupBusqueda();


        final Configuracion configuracion = Configuracion.getInstance(this);

        Button aceptar = (Button) findViewById(R.id.button1);

        aceptar.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    arg0.setBackgroundResource(R.drawable.saveg);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    arg0.setBackgroundResource(R.drawable.save);
                }
                return false;
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                configuracion.putConfiguracion("ahorros",
                        seekAhorros.getProgress());
                configuracion.putConfiguracion("salario",
                        seekSalario.getProgress());
                configuracion.putConfiguracion("mesescasa",
                        seekMesesCasa.getProgress());
                configuracion.putConfiguracion("mesestrabajo",
                        seekMesesTrabajo.getProgress());

                finish();
            }
        });
    }


    public void setupBusqueda() {
        final SeekBar seekSalario = (SeekBar) findViewById(R.id.seekSalario);
        seekSalario.setMax(100);



        final SeekBar seekMesesTrabajo = (SeekBar) findViewById(R.id.seekMesesTrabajo);
        seekMesesTrabajo.setMax(30);
        final SeekBar seekMesesCasa = (SeekBar) findViewById(R.id.seekMesesCasa);
        seekMesesCasa.setMax(60);
        final SeekBar seekAhorros = (SeekBar) findViewById(R.id.seekAhorros);
        seekAhorros.setMax(20);
        int salario = Configuracion.getInstance(this)
                .getConfiguracionInt("salario");
        int mesestrabajo = Configuracion.getInstance(this)
                .getConfiguracionInt("mesestrabajo");
        int mesescasa = Configuracion.getInstance(this)
                .getConfiguracionInt("mesescasa");
        int ahorros = Configuracion.getInstance(this)
                .getConfiguracionInt("ahorros");

        if (salario > 0) {
            seekSalario.setProgress(salario);
        } else {
            seekSalario.setProgress(50);
        }
        if (mesestrabajo > 0) {
            seekMesesTrabajo.setProgress(mesestrabajo);
        } else {
            seekMesesTrabajo.setProgress(6);
        }

        if (mesescasa > 0) {
            seekMesesCasa.setProgress(mesescasa);
        } else {
            seekMesesCasa.setProgress(24);
        }
        if (ahorros > 0) {
            seekAhorros.setProgress(ahorros);
        } else {
            seekAhorros.setProgress(20);
        }


        final TextView txtAhorro = (TextView) findViewById(R.id.textAhorros);
        final TextView txtSalario = (TextView) findViewById(R.id.textsalario);
        final TextView txtMesesCasa = (TextView) findViewById(R.id.textTiempoCasa);
        final TextView txtmesesTrabajo = (TextView) findViewById(R.id.textTiempoTrabajo);


        txtmesesTrabajo.setText(seekMesesTrabajo.getProgress() + " Meses");
        seekMesesTrabajo
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        if (seekMesesTrabajo.getProgress() == 0)
                            seekMesesTrabajo.setProgress(1);
                        txtmesesTrabajo.setText(seekMesesTrabajo.getProgress()
                                + " Meses");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1,
                                                  boolean arg2) {
                        txtmesesTrabajo.setText(seekMesesTrabajo.getProgress()
                                + " Meses");
                    }
                });

        txtMesesCasa.setText(seekMesesCasa.getProgress() + " Meses");
        seekMesesCasa
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        if (seekMesesCasa.getProgress() == 0)
                            seekMesesCasa.setProgress(1);
                        txtMesesCasa.setText(seekMesesCasa.getProgress()
                                + " Meses");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1,
                                                  boolean arg2) {
                        txtMesesCasa.setText(seekMesesCasa.getProgress()
                                + " Meses");
                    }
                });

        txtSalario.setText(seekSalario.getProgress() + " Mil Euros");
        seekSalario
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        if (seekSalario.getProgress() == 0)
                            seekSalario.setProgress(1);
                        txtSalario.setText(seekSalario.getProgress()
                                + " Mil Euros");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1,
                                                  boolean arg2) {
                        txtSalario.setText(seekSalario.getProgress()
                                + " Mil Euros");
                    }
                });


        txtAhorro.setText(seekAhorros.getProgress() + " Mil Euros");
        seekAhorros
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                        if (seekAhorros.getProgress() == 0)
                            seekAhorros.setProgress(1);
                        txtAhorro.setText(seekAhorros.getProgress()
                                + " Mil Euros");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1,
                                                  boolean arg2) {
                        txtAhorro.setText(seekAhorros.getProgress()
                                + " Mil Euros");
                    }
                });
    }


}
