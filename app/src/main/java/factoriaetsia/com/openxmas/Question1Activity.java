package factoriaetsia.com.openxmas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Question1Activity extends QuestionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        setup();
    }

    public void setup(){
        setTextA("Un");
        setTextB("Dos");
        setTextC("Tres");
       setLink("https://www.openbank.es/es/ahorro/cuenta-bienvenida-ahorro");
        setQuestion("Si abres una Cuenta de ahorro en OpenBank tendrás a un tipo de interés nominal del 2% los primeros **** mes(es).");
        setCorrectAnswer(3);
        super.setup();

    }

}
