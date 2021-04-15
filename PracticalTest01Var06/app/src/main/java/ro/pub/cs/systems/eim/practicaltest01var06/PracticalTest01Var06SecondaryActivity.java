package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {
    private EditText gained;
    private Button okButton;
    private int gained_sum = 0;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(gained_sum, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        gained = (EditText)findViewById(R.id.gained);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER1)
                && intent.getExtras().containsKey(Constants.NUMBER2) && intent.getExtras().containsKey(Constants.NUMBER3)
                && intent.getExtras().containsKey(Constants.CHECK_BOX)) {
            String number1 = intent.getExtras().get(Constants.NUMBER1).toString();
            System.out.println(number1);
            String number2 = intent.getExtras().get(Constants.NUMBER2).toString();
            String number3 = intent.getExtras().get(Constants.NUMBER3).toString();
            int check_box = intent.getIntExtra(Constants.CHECK_BOX, -1);
            if ((number1.equals(number2) && number2.equals(number3))
                    || (number1.equals(number2) && number3.equals("*"))
                    || (number1.equals(number3) && number2.equals("*"))
                    || (number2.equals(number3) && number1.equals("*"))
                    || (number2.equals("*") && number1.equals("*"))
                    || (number3.equals("*") && number1.equals("*"))
                    || (number2.equals("*") && number3.equals("*"))) {
                if (check_box == 0) {
                    gained_sum += 100;
                    String g = "Gained" + 100;
                    gained.setText(g);
                } else if (check_box == 1) {
                    gained_sum += 50;
                    String g = "Gained" + 50;
                    gained.setText(g);
                } else if (check_box == 2) {
                    gained_sum += 10;
                    String g = "Gained" + 10;
                    gained.setText(g);
                }
            }

            okButton = (Button) findViewById(R.id.ok_button);
            okButton.setOnClickListener(buttonClickListener);
        }
    }
}
