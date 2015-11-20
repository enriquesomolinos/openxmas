package factoriaetsia.com.openxmas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import factoriaetsia.com.util.Configuracion;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final Configuracion configuracion = Configuracion.getInstance(this);
        int correctAnswers =  configuracion.getConfiguracionInt("correctAnswers");
        int salario =  configuracion.getConfiguracionInt("salario");


        TextView correctTect =(TextView) findViewById(R.id.textCorrect);

        TextView regalo1 =(TextView) findViewById(R.id.regalo1);
        TextView regalo2 =(TextView) findViewById(R.id.regalo2);
        TextView regalo3 =(TextView) findViewById(R.id.regalo3);



        String resp = "Preguntas correctas: " + correctAnswers;
        correctTect.setText(resp);

        ImageView image1 =(ImageView) findViewById(R.id.image1);
        ImageView image2 =(ImageView) findViewById(R.id.image2);
        ImageView image3 =(ImageView) findViewById(R.id.image3);
        ImageView imageRegalo =(ImageView) findViewById(R.id.imageRegalo);

        if(correctAnswers==0){
            image1.setImageDrawable(getDrawable(R.drawable.imagea));
            image2.setImageDrawable(getDrawable(R.drawable.imageb));
            image3.setImageDrawable(getDrawable(R.drawable.imagec));
            imageRegalo.setImageDrawable(getDrawable(R.drawable.scooor));
        }else if(correctAnswers==1){
            image1.setImageDrawable(getDrawable(R.drawable.imagea2));
            image2.setImageDrawable(getDrawable(R.drawable.imageb));
            image3.setImageDrawable(getDrawable(R.drawable.imagec));
            imageRegalo.setImageDrawable(getDrawable(R.drawable.scooor));
        }else if(correctAnswers==2){
            image1.setImageDrawable(getDrawable(R.drawable.imagea2));
            image2.setImageDrawable(getDrawable(R.drawable.imageb2));
            image3.setImageDrawable(getDrawable(R.drawable.imagec));

        }else {
            image1.setImageDrawable(getDrawable(R.drawable.imagea2));
            image2.setImageDrawable(getDrawable(R.drawable.imageb2));
            image3.setImageDrawable(getDrawable(R.drawable.imagec2));
            imageRegalo.setImageDrawable(getDrawable(R.drawable.regalo));
            if(salario>60){
                regalo1.setText("Invierte el 100% de la exposición total en renta fija pública y privada con una rentabilidad de ");
                regalo2.setText("2.5%");
                regalo3.setText("Oferta anterior: 1% https://www.openbank.es/es/inversion/tipos-fondos/openbank-corto-plazo");
            }else{
                regalo1.setText("Domicilia tu nómina, pensión o ingreso mensual en la Cuenta Nómina Open y te ingresamos    :");
                regalo2.setText("150€");
                regalo3.setText("Oferta anterior:100€ https://www.openbank.es/es/cuentas-tarjetas/cuenta-nomina");
            }
        }

        Toast.makeText(
                getBaseContext(),
                "Respuestas acertadas:"+ correctAnswers,
                Toast.LENGTH_LONG).show();
    }
}
