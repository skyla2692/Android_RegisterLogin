package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText TextInputEditText_SignUp_Name, TextInputEditText_SignUp_BirthDate, TextInputEditText_SignUp_PhoneNumber, TextInputEditText_SignUp_ID, TextInputEditText_SignUp_Password;
    private RelativeLayout RelativeLayout_Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText_SignUp_Name = findViewById(R.id.TextInputEditText_SignUp_Name);
        TextInputEditText_SignUp_BirthDate = findViewById(R.id.TextInputEditText_SignUp_BirthDate);
        TextInputEditText_SignUp_PhoneNumber = findViewById(R.id.TextInputEditText_SignUp_PhoneNumber);
        TextInputEditText_SignUp_ID = findViewById(R.id.TextInputEditText_SignUp_ID);
        TextInputEditText_SignUp_Password = findViewById(R.id.TextInputEditText_SignUp_Password);

        //회원가입 버튼 클릭 시 수행
        RelativeLayout_Continue = findViewById(R.id.RelativeLayout_Continue);
        RelativeLayout_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //입력자 정보 가져오기
                String userName = TextInputEditText_SignUp_Name.getText().toString();
                String userBirthDate = TextInputEditText_SignUp_BirthDate.getText().toString();
                String userPhoneNumber = TextInputEditText_SignUp_PhoneNumber.getText().toString();
                String userID = TextInputEditText_SignUp_ID.getText().toString();
                String userPassword = TextInputEditText_SignUp_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //통신상태의 성공 여부 반환함
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = (boolean) jsonObject.get("success");
                            //회원등록에 성공한 경우
                            if (success) {
                                Toast.makeText(getApplicationContext(), "Registration Succeeded.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {        //회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "Registration Failed.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 volley를 이용해 요청을 함
                RegisterRequest registerRequest = new RegisterRequest(userName, userBirthDate, userPhoneNumber, userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });
    }
}
