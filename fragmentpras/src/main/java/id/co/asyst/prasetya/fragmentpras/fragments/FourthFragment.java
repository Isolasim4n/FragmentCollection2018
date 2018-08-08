package id.co.asyst.prasetya.fragmentpras.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.co.asyst.prasetya.fragmentpras.R;
import id.co.asyst.prasetya.fragmentpras.utility.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText nameEt, addressEt;
    Button submitButton;
    OnSubmitButtonListener listener;
    ImageView dateIv;
    TextView dateEt;
    DatePickerDialog datePickerDialog;

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(String name, String address, String birthday) {
        FourthFragment fourthFragment = new FourthFragment();

        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putString("alamat", address);
        bundle.putString("birthday", birthday);
        fourthFragment.setArguments(bundle);

        return fourthFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        nameEt = view.findViewById(R.id.name_editText);
        addressEt = view.findViewById(R.id.adrress_editText);
        submitButton = view.findViewById(R.id.submit_button);
        Calendar c = Calendar.getInstance();

        dateIv = view.findViewById(R.id.date_imageView);
        dateEt = view.findViewById(R.id.date_textview);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

        if (getArguments() != null) {
            nameEt.setText(getArguments().getString("nama", ""));
            addressEt.setText(getArguments().getString("alamat", ""));
            dateEt.setText(getArguments().getString("birthday", ""));
        }

        int year = 2000, month = 0, day = 1;
        String selectedDate = dateEt.getText().toString();
        if (!selectedDate.equalsIgnoreCase("")) {
            c.setTime(DateUtils.saveDate("dd MMMM yyyy", selectedDate));

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        submitButton.setOnClickListener(this);
        dateIv.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String name = nameEt.getText().toString();
        String address = addressEt.getText().toString();
        switch (v.getId()) {
            case R.id.submit_button:
                if (TextUtils.isEmpty(name)) {
                    nameEt.setError("Isi nama dulu dong");
                } else if (TextUtils.isEmpty(address)) {
                    addressEt.setError("Isi alamat dulu dong");
                } else {
                    listener.onSubmitButton(nameEt.getText().toString(), addressEt.getText().toString(), dateEt.getText().toString());
                    getActivity().getSupportFragmentManager().popBackStack();
                }

                break;
            case R.id.date_imageView:
                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + " " + (month + 1) + " " + year;
        dateEt.setText(DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnSubmitButtonListener) {
            listener = (OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Activity try to implement interface");
        }
    }

    public interface OnSubmitButtonListener {
        void onSubmitButton(String name, String address, String birthday);
    }
}
