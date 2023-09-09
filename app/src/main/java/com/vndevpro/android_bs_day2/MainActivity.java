package com.vndevpro.android_bs_day2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IRegsiterListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = LoginFragment.newInstance("Hai", "HN");
        getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("FRAG").commit();
        RegisterFragment registerFragment2 = RegisterFragment.newInstance("Hai", "HN");
        registerFragment2.setCallback(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container, registerFragment2).addToBackStack("FRAG").commit();

//        getSupportFragmentManager().beginTransaction().remove(loginFragment);


//        RegisterFragment registerFragment = RegisterFragment.newInstance("", "");
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.containerB, registerFragment)
//                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int count = getSupportFragmentManager().getFragments().size();
        Log.d(TAG, "onCreate: " + count);
    }

    public static final int REQUEST_REGISTER = 1;
    public static final int REQUEST_HOME = 2;

    @Override
    public void onGotoRegister() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//        startActivityForResult(intent,REQUEST_REGISTER);
        registerResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> registerResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()  == RESULT_OK){
                Intent intent= result.getData();
                Log.d(TAG, "onActivityResult: RESULT_OK"+intent.getStringExtra("DATA"));
            }
            if (result.getResultCode()  == RESULT_CANCELED){
                Log.d(TAG, "onActivityResult: RESULT_CANCELED");
            }
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REGISTER){
            if (resultCode == RESULT_OK){
                //do something

                Log.d(TAG, "onActivityResult: RESULT_OK "+data.getStringExtra("DATA"));
            }
            if (resultCode == RESULT_CANCELED){
                //do something
                Log.d(TAG, "onActivityResult: RESULT_CANCELED");
            }
        }
    }
}