package factoriaetsia.com.openxmas;

import android.os.Bundle;

public class Question4Activity extends QuestionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        setup();
    }

    public void setup(){
        setTextA("60%");
        setTextB("70%");
        setTextC("80%");
        setLink("https://www.openbank.es/es/hipotecas-prestamos/hipoteca-open");
        setQuestion("La Hipoteca Open Es una hipoteca concedida por el Banco Santander a clientes de Openbank que ofrece hasta el ****de financiación para tu 2ª vivienda.");
        setCorrectAnswer(2);
        super.setup();

    }

}
