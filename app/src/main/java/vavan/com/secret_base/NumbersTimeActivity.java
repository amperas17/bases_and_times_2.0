package vavan.com.secret_base;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersTimeActivity extends Activity {

    final int DIALOG_TIME_FIRST = 1;
    final int DIALOG_TIME_SECOND = 2;

    int hoursFirst = 0;
    int minutesFirst = 0;
    int hoursSecond = 0;
    int minutesSecond = 0;

    private TextView tvResult;
    private Button btCount, btFirstTime, btSecondTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_time);

        btFirstTime = (Button)findViewById(R.id.btFirstTime_Num);
        btSecondTime = (Button)findViewById(R.id.btSecondTime_Num);
        btCount = (Button)findViewById(R.id.btCount_Num);

        tvResult = (TextView)findViewById(R.id.tvResult_Num);


        btFirstTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TIME_FIRST);
            }
        });

        btSecondTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TIME_SECOND);
            }
        });

        btCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diff_string="";

                int timeFirst = hoursFirst*60 + minutesFirst;
                int timeSecond = hoursSecond*60 + minutesSecond;

                int diff_minutes = timeSecond - timeFirst;

                if (diff_minutes < 0){
                    diff_minutes = 24*60 + diff_minutes;
                }

                diff_string = "The difference is: "+(diff_minutes/60)+" hours and " + (diff_minutes%60)+" minutes.";

                tvResult.setText(diff_string);
            }
        });

    }

    protected Dialog onCreateDialog(int id){

        switch (id){
            case DIALOG_TIME_FIRST:
                TimePickerDialog timePickerDialogFirst = new TimePickerDialog(this,myCallBackFirst, hoursFirst,minutesFirst,true);
                return timePickerDialogFirst;

            case DIALOG_TIME_SECOND:
                TimePickerDialog timePickerDialogSecond = new TimePickerDialog(this,myCallBackSecond, hoursSecond,minutesSecond,true);
                return timePickerDialogSecond;

            default:
                return super.onCreateDialog(id);
        }

    }

    OnTimeSetListener myCallBackFirst = new OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            String time_str = "";
            hoursFirst = hours;
            minutesFirst = minutes;

            if (hours<10){
                time_str = "0" ;
            }
            time_str = time_str + hours + ':';

            if (minutes<10){
                time_str = time_str + "0" ;
            }
            time_str = time_str + minutes;

            btFirstTime.setText(time_str);
        }
    };

    OnTimeSetListener myCallBackSecond = new OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            String time_str = "";
            hoursSecond = hours;
            minutesSecond = minutes;

            if (hours<10){
                time_str = "0" ;
            }
            time_str = time_str + hours + ':';

            if (minutes<10){
                time_str = time_str + "0" ;
            }
            time_str = time_str + minutes;

            btSecondTime.setText(time_str);
        }
    };

}
