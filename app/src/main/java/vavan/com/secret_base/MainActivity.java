package vavan.com.secret_base;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
/*
*added on github
*/

public class MainActivity extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setIndicator("Секретная база");
        tabSpec.setContent(new Intent(this, BasesCodesActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setIndicator("Разница во времени");
        tabSpec.setContent(new Intent(this, NumbersTimeActivity.class));
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(1);
    }

}
