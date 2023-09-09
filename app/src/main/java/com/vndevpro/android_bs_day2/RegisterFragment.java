package com.vndevpro.android_bs_day2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText edtUserName;
    private TextView tv1;
    private CheckBox cbConfirmTOS, cbConfirmTOS2;
    private Button btnRegister;
    private RadioGroup rgGender;
    private IRegsiterListener callback;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public void setCallback(IRegsiterListener callback) {
        this.callback = callback;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_constraint, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUserName = view.findViewById(R.id.edtUserName);
        tv1 = view.findViewById(R.id.tv1);
        btnRegister = view.findViewById(R.id.btnRegister);
        cbConfirmTOS = view.findViewById(R.id.cbConfirmTOS);
        rgGender = view.findViewById(R.id.rgGender);

        cbConfirmTOS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("TAG", "onCheckedChanged: cbConfirmTOS " + isChecked);
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbMale:
                        Log.d("TAG", "onCheckedChanged: is Male");
                        break;
                    case R.id.rbFemale:
                        Log.d("TAG", "onCheckedChanged: is Female");
                        break;
                    case R.id.rbOther:
                        Log.d("TAG", "onCheckedChanged: is Other");
                        break;
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                boolean isCheckConfirm = cbConfirmTOS.isChecked();
//                if (isCheckConfirm) {
//                    String data = edtUserName.getText().toString();
//                    Log.d("TAG", "onClick: " + data);
//                } else {
//                    Toast.makeText(getContext(), "Please check TOS !", Toast.LENGTH_SHORT).show();
//                }
                if (callback != null){
                    callback.onGotoRegister();
                }
            }
        });

//        btnRegister.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });
    }
}