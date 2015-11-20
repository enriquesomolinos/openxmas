package factoriaetsia.com.openxmas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

import factoriaetsia.com.util.Configuracion;

public class QuestionActivity extends AppCompatActivity  implements View.OnClickListener {



    private int correctAnswer  = 1;
    private final static int START_DRAGGING = 0;
    private final static int STOP_DRAGGING = 1;
    private LinearLayout answerA;
    private LinearLayout answerB;
    private LinearLayout answerC;


    private String link;

    private String textA;
    private String textB;
    private String textC;
    private String question;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTextA() {
        return textA;
    }

    public void setTextA(String textA) {
        this.textA = textA;
    }

    public String getTextB() {
        return textB;
    }

    public void setTextB(String textB) {
        this.textB = textB;
    }

    public String getTextC() {
        return textC;
    }

    public void setTextC(String textC) {
        this.textC = textC;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    private int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);



    }

    public void setup(){
        final  Boolean touched = false;
        answerA = (LinearLayout) findViewById(R.id.answerA);
        answerB = (LinearLayout) findViewById(R.id.answerB);
        answerC = (LinearLayout) findViewById(R.id.answerC);
        ((TextView) findViewById(R.id.textA)).setText(this.getTextA());
        ((TextView) findViewById(R.id.textB)).setText(this.getTextB());
        ((TextView) findViewById(R.id.textC)).setText(this.getTextC());
        ((TextView) findViewById(R.id.textQuestion)).setText(this.getQuestion());
        ((TextView) findViewById(R.id.link)).setText(this.getLink());


        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        if(v.getId()==(R.id.answerA)){
            answerA.setBackgroundColor(Color.LTGRAY);
            if(correctAnswer==1){
                Intent correctIntent = new Intent(getBaseContext(),
                        CorrectAnswerActivity.class);
                final Configuracion configuracion = Configuracion.getInstance(this);
                int correctAnswers =  configuracion.getConfiguracionInt("correctAnswers");
                correctAnswers=correctAnswers+1;
                configuracion.putConfiguracion("correctAnswers",correctAnswers);

                startActivity(correctIntent);
            }else{
                Intent correctIntent = new Intent(getBaseContext(),
                        WrongAnswerActivity.class);

                startActivity(correctIntent);
            }


        }else if(v.getId()==(R.id.answerB)){
            answerB.setBackgroundColor(Color.LTGRAY);
            if(correctAnswer==2){
                Intent correctIntent = new Intent(getBaseContext(),
                        CorrectAnswerActivity.class);
                final Configuracion configuracion = Configuracion.getInstance(this);
                int correctAnswers =  configuracion.getConfiguracionInt("correctAnswers");
                correctAnswers=correctAnswers+1;
                configuracion.putConfiguracion("correctAnswers",correctAnswers);

                startActivity(correctIntent);
            }else{
                Intent correctIntent = new Intent(getBaseContext(),
                        WrongAnswerActivity.class);

                startActivity(correctIntent);
            }

        }else if(v.getId()==(R.id.answerC)){
            answerC.setBackgroundColor(Color.LTGRAY);
            if(correctAnswer==3){
                Intent correctIntent = new Intent(getBaseContext(),
                        CorrectAnswerActivity.class);
                final Configuracion configuracion = Configuracion.getInstance(this);
                int correctAnswers =  configuracion.getConfiguracionInt("correctAnswers");
                correctAnswers=correctAnswers+1;
                configuracion.putConfiguracion("correctAnswers",correctAnswers);
                startActivity(correctIntent);
            }else{
                Intent correctIntent = new Intent(getBaseContext(),
                        WrongAnswerActivity.class);

                startActivity(correctIntent);
            }

        }
    }
}
