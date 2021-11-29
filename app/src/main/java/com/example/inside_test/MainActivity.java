package com.example.inside_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
***********고객 정보 입력 화면 만들기 예제***********
    고객의 이름, 나이, 생년월일을 입력 받는 예제
    이름과 나이를 입력받는 입력상자, 생년월일을 표시하는 버튼, 저장 버튼으로 구성됨.
    이름은 텍스트, 나이는 숫자로, 각각 타입에 맞는 키패드를 띄움
    나이 입력상자의 경우, 최대 3자리까지만 입력할 수 있음
    생년월일 버튼의 경우, 오늘 날짜로 초기화 되어 있음
    생년월일 버튼을 클릭할 경우, 날짜 선택창이 나타남
    날짜를 선택하면 버튼 텍스트가 해당 날짜로 바뀜
    저장 버튼을 누르면 토스트로 고객이 입력한 정보를 순서대로 띄어줌
 */
public class MainActivity extends AppCompatActivity {

    EditText name;      // 이름 입력상자
    EditText age;       // 나이 입력상자
    Button visitday;    // 생년월일 입력상자(instance variable)
    // 생년월일 입력상자의 경우 프래그먼트에서도 접근해야 하기 때문에
    // onCreate 내 지역변수가 아닌, 클래스 영역에 선언을 함.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputFilter[] filters = new InputFilter[1];             // age의 입력 최대길이 제한
                filters[0] = new InputFilter.LengthFilter(3);
                age.setFilters(filters);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Date today = new Date();      // birthday 버튼의 초기화를 위해 date 객체와 SimpleDataFormat 사용
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        String result = dateFormat.format(today);

        visitday = (Button) findViewById(R.id.birthday);
        visitday.setText(result);       // 오늘 날짜로 birthday 버튼 텍스트 초기화

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {        // 저장 버튼을 클릭하면 토스트로 고객 정보를 띄워주기
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이름 : " + name.getText().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "나이 : " + age.getText().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "생년월일 : " + visitday.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBirthdayClicked (View v) {
        android.app.DialogFragment newFragment = new DatePickerFragment();   //DatePickerFragment 객체 생성
        newFragment.show(getFragmentManager(), "datePiker");
    }

}
