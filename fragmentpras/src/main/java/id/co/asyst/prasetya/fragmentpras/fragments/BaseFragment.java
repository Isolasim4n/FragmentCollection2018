package id.co.asyst.prasetya.fragmentpras.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.co.asyst.prasetya.fragmentpras.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    Button bt_a, bt_b, bt_c;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        bt_a = view.findViewById(R.id.button_a);
        bt_b = view.findViewById(R.id.button_b);
        bt_c = view.findViewById(R.id.button_c);

        bt_a.setOnClickListener(this);
        bt_b.setOnClickListener(this);
        bt_c.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (id) {
            case R.id.button_a:
                fragment = new FirstFragment();
                break;
            case R.id.button_b:
                fragment = new SecondFragment();
                break;
            case R.id.button_c:
                fragment = new ThirdFragment();
                break;
        }
        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}
