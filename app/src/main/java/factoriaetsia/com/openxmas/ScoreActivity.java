package factoriaetsia.com.openxmas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import factoriaetsia.com.util.Configuracion;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final Configuracion configuracion = Configuracion.getInstance(this);
        int correctAnswers =  configuracion.getConfiguracionInt("correctAnswers");
        correctAnswers=correctAnswers+1;

        Toast.makeText(
                getBaseContext(),
                "Respuestas acertadas:"+ correctAnswers,
                Toast.LENGTH_LONG).show();
    }
}
