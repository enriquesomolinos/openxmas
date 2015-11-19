package factoriaetsia.com.openxmas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WrongAnswerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v.getId() == (R.id.button)) {
            Intent scoreActivity = new Intent(getBaseContext(),
                    ScoreActivity.class);


            startActivity(scoreActivity);
        }
    }
}
