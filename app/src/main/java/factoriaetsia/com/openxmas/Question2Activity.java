package factoriaetsia.com.openxmas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Question2Activity extends QuestionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        setup();
    }

    public void setup(){
        setTextA("tendrán una comisión de 2%");
        setTextB("tendrán una comisión de 3.5%");
        setTextC("serán gratuitas");
    setLink("https://www.openbank.es/es/cuentas-tarjetas/cuenta-corriente");
        setQuestion("Desde tu Cuenta Corriente de Openbank tus transferencias en euros a la Unión Europea *******");
        setCorrectAnswer(3);
        super.setup();

    }

}
