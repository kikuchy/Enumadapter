package net.kikuchy.enumadapter.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import net.kikuchy.enumadapter.EnumArrayAdapter;
import net.kikuchy.enumadapter.ResourceStringify;
import net.kikuchy.enumadapter.Stringify;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // You don't need string converted list or array of Enum values.
        SpinnerAdapter adapter = new EnumArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Phones.values(), new ResourceStringify<Phones>(this) {
            @Override
            public int getStringResource(Phones target) {
                int res = 0;
                switch (target) {
                    case IPHONE: res = R.string.iphone; break;
                    case ANDROID:res = R.string.android; break;
                    case WINDOWS_PHONE: res = R.string.windows_phone; break;
                    case BLACKBERRY: res = R.string.blackberry; break;
                    case FIREFOX_OS: res = R.string.firefox_os; break;
                    case UBUNTU_PHONE: res = R.string.ubuntu_phone; break;
                }
                return res;
            }
        });
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Now, you can use Enum value directly!!
                Phones p = (Phones) adapterView.getSelectedItem();
                Toast.makeText(MainActivity.this, String.format("Oh, you are %s user!?", p.toString()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
