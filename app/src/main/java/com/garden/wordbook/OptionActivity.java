package com.garden.wordbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class OptionActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        LinearLayout list, add, learn;
        Button reset;

        list = (LinearLayout) findViewById(R.id.list);
        add = (LinearLayout) findViewById(R.id.add);
        learn = (LinearLayout) findViewById(R.id.learn);
        reset = (Button) findViewById(R.id.reset);

        myHelper = new myDBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(OptionActivity.this, R.style.MyDialogTheme);

                ad.setTitle("주의");       // 제목 설정
                ad.setMessage("정말로 모든 단어장을 초기화하시겠습니까?");   // 내용 설정

                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlDB = myHelper.getReadableDatabase();

                        sqlDB.execSQL("delete from wordTBL");
                        sqlDB.close();

                        Toast.makeText(getApplicationContext(), "단어장 초기화됨", Toast.LENGTH_SHORT).show();
                    }
                });

                // 취소 버튼 설정
                ad.setNegativeButton("No", null);
                ad.show();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddWordActivity.class);
                startActivity(intent);
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LearnWordActivity.class);
                startActivity(intent);
            }
        });

    }
}
