package vavan.com.secret_base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasesCodesActivity extends Activity {

    EditText etInput;
    TextView tvOutput;
    Button btCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bases_codes);

        etInput = (EditText)findViewById(R.id.etInput_Base);
        tvOutput = (TextView)findViewById(R.id.tvOutput_Base);
        btCheck = (Button)findViewById(R.id.btCheck_Base);

        etInput.setText("10 3\n1231231232");


        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOutput.setText(Check(etInput.getText().toString()));
            }
        });
    }

    protected String Check(String testingString){
        /* Метод осуществляет проверку правильности ввода строки с помощью проверки на с
        * соответсвие шаблону регулярного выражения.
        * Если проверка пройдена происходит считывания всех трёх переманных и необходимые
        * дополнительные проверки.
        * Далее осуществляется поиск повторяющихся числовых последовательностей. Если находится
        * код указанной длины, он добавляется в ArrayList, хранящий уникальные вхождения.
        * Все следующие вхождения попадают в список только если они ещё не встречались.
        * В итоге выводится результат проверки Yes с номерами баз либо No, если они не
        * были найдены.
         */
        int intN=0,intK=0;
        String strN="",strK="",message="",result="";

        ArrayList<String> basesCodes = new ArrayList<>();


        Pattern pattern = Pattern.compile("^\\d{1,5}\\s+\\d[\n]\\d{1,10}$");
        Matcher matcher = pattern.matcher(testingString);
        if (!matcher.matches()){
            result = "Wrong message! Check your text!";
        }
        else {

            int i = 0;
            while (testingString.charAt(i)!=' '){
                strN = strN + testingString.charAt(i);
                i++;
            }
            i++;
            while (testingString.charAt(i)!='\n'){
                strK = strK + testingString.charAt(i);
                i++;
            }
            i++;
            while (i < testingString.length() )
            {   message = message + testingString.charAt(i);
                i++;
            }

            intN = Integer.parseInt(strN);
            intK = Integer.parseInt(strK);

            if (message.length()== intN && intK>=1&&intK<=5 && intN>=1&&intN<=10000 && intK<intN){
                for (int j=0;j<intN-intK;j++){

                    for (int k=j+1;k<intN;k++){
                        String entry = "";
                        if (message.charAt(j)==message.charAt(k)){
                            entry = entry + message.charAt(k);
                            for (int p=1;p<intK&&k+p<intN;p++){
                                if (message.charAt(j+p)==message.charAt(k+p)){
                                    entry = entry + message.charAt(k+p);
                                }
                            }
                        }
                        if (entry.length() == intK) {

                            boolean alreadyHave = false;
                            for (int s=0;s<basesCodes.size();s++){
                                if (entry.equals(basesCodes.get(s))){
                                    alreadyHave = true;
                                    break;
                                }

                            }
                            if (!alreadyHave){
                                basesCodes.add(entry);
                            }
                        }
                    }

                }
                if (basesCodes.size()!=0){
                    result = "Yes " + basesCodes.toString();
                }
                else {
                    result = "No";
                }
            }
            else {
                result = "Wrong message! Check your text!";
            }

        }

        return result;
    }

}
