package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    private EditText numar1, numar2, numar3;
    private CheckBox hold1, hold2, hold3;
    private Button play_button;
    private int sum = 0;
    private int first = 0;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        String[] random_num = {"1", "2", "3", "*"};
        @Override
        public void onClick(View view) {
            int numberOfChecks = 3;
            Random r=new Random();
            if (view.getId() == R.id.play_button) {
                if (!hold1.isChecked()) {
                    int randomNumber=r.nextInt(random_num.length);
                    numar1.setText(random_num[randomNumber]);
                    numberOfChecks--;
                }
                if (!hold2.isChecked()) {
                    int randomNumber=r.nextInt(random_num.length);
                    numar2.setText(random_num[randomNumber]);
                    numberOfChecks--;
                }
                if (!hold3.isChecked()) {
                    int randomNumber=r.nextInt(random_num.length);
                    numar3.setText(random_num[randomNumber]);
                    numberOfChecks--;
                }
                String no = "numbers are" + numar1.getText().toString() + " " + numar2.getText().toString() + " " + numar3.getText().toString();
                Log.d("On Play Button we have", no);

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra(Constants.NUMBER1, numar1.getText().toString());
                intent.putExtra(Constants.NUMBER2, numar2.getText().toString());
                intent.putExtra(Constants.NUMBER3, numar3.getText().toString());
                intent.putExtra(Constants.CHECK_BOX, numberOfChecks);
                startActivityForResult(intent, 3);
            }

            if (sum > 0 && first == 0) {
                first = 1;
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                intent.putExtra(Constants.GAINED_SUM, sum);
                getApplicationContext().startService(intent);
            }

            if (sum >= 300) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                intent.putExtra(Constants.GAINED_SUM, sum);
                getApplicationContext().startService(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        numar1 = (EditText)findViewById(R.id.numar1);
        numar2 = (EditText)findViewById(R.id.numar2);
        numar3 = (EditText)findViewById(R.id.numar3);
        hold1 = (CheckBox) findViewById(R.id.hold1);
        hold2 = (CheckBox)findViewById(R.id.hold2);
        hold3 = (CheckBox)findViewById(R.id.hold3);
        play_button = (Button)findViewById(R.id.play_button);
        play_button.setOnClickListener(buttonClickListener);

        intentFilter.addAction(Constants.actionTypes[0]);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        System.out.println(resultCode);
        if (requestCode == 3) {
            Toast.makeText(this, "The activity returned with result " + 0, Toast.LENGTH_LONG).show();
            sum += resultCode;
            Toast.makeText(this, "Total score " + sum, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.GAINED_SUM, String.valueOf(sum));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.GAINED_SUM)) {
            String gained_sum = savedInstanceState.getString(Constants.GAINED_SUM);
            Toast.makeText(this, "Total score " + gained_sum, Toast.LENGTH_LONG).show();
        }
    }
}