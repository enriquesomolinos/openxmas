package factoriaetsia.com.openxmas;

import android.os.Bundle;

public class Question3Activity extends QuestionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        setup();
    }

    public void setup(){
        setTextA("50");
        setTextB("100");
        setTextC("200");
        setLink("https://www.openbank.es/es/inversion/tipos-fondos/openbank-corto-plazo");
        setQuestion("La aportación mínima para la contratación de Fondos de Renta Fija a corto plazo de Openbank es de ****€.");
        setCorrectAnswer(2);
        super.setup();

    }

}
