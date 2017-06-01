package com.example.administrator.microentertainment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.microentertainment.Untils.OkHttpUntils;

public class MainActivity extends Activity implements View.OnClickListener {

    String pnum ="13097340262";
    Button btn_getCode,btn_matchCode;
    EditText edt_putCode;

    Handler mhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();




    }
    private void init() {
        btn_getCode= (Button) findViewById(R.id.btn_getCode);
        btn_matchCode= (Button) findViewById(R.id.btn_register);
        edt_putCode= (EditText) findViewById(R.id.edt_inputCode);
//
//        mhandler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                switch (msg.what){
//                    case 1:
//
//                        break;
//                }
//            }
//        };
    }
    private void event() {
        btn_getCode.setOnClickListener(this);
        btn_matchCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_getCode:
                new OkHttpUntils().getCode(pnum);
                break;
            case R.id.btn_register:
                String Code=edt_putCode.getText().toString();
                new OkHttpUntils().macthCode(Code);
                break;
        }
    }
}
