package id.co.asyst.prasetya.fragmentpras;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.co.asyst.prasetya.fragmentpras.fragments.FourthFragment;
import id.co.asyst.prasetya.fragmentpras.fragments.InputBottomSheet;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, FourthFragment.OnSubmitButtonListener, InputBottomSheet.OnSubmitButtonListener {
    TextView nameTv, addressTv, birthdayTv;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameTv = findViewById(R.id.nama_textView);
        addressTv = findViewById(R.id.alamat_textView);
        submitButton = findViewById(R.id.input_button);
        birthdayTv = findViewById(R.id.tanggal_lahir_textView);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.input_button:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FourthFragment fourthFragment = FourthFragment.newInstance(nameTv.getText().toString(), addressTv.getText().toString(), birthdayTv.getText().toString());


                transaction.replace(android.R.id.content, fourthFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                break;
        }
    }

    @Override
    public void onSubmitButton(String name, String address, String birthday) {
        nameTv.setText(name);
        addressTv.setText(address);
        birthdayTv.setText(birthday);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.input_main:
                InputBottomSheet inputBottomSheet = InputBottomSheet.newInstance(nameTv.getText().toString(), addressTv.getText().toString(), birthdayTv.getText().toString());
                inputBottomSheet.show(getSupportFragmentManager(), "Input Bottom Sheet");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
