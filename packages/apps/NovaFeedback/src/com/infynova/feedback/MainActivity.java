package com.infynova.feedback;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText mDescription;
    private Button mBtnCollect;
    private Button mBtnSubmit;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDescription = findViewById(R.id.edit_description);
        mBtnCollect = findViewById(R.id.btn_collect_logs);
        mBtnSubmit = findViewById(R.id.btn_submit);
        mProgress = findViewById(R.id.progress_bar);

        mBtnCollect.setOnClickListener(v -> collectLogs());
        mBtnSubmit.setOnClickListener(v -> submitReport());
    }

    private void collectLogs() {
        mProgress.setVisibility(View.VISIBLE);
        mBtnCollect.setEnabled(false);

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return captureLogs();
            }

            @Override
            protected void onPostExecute(Boolean success) {
                mProgress.setVisibility(View.GONE);
                mBtnCollect.setEnabled(true);
                if (success) {
                    Toast.makeText(MainActivity.this, "Logs collected successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to collect logs.", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private boolean captureLogs() {
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            File logFile = new File(getExternalFilesDir(null), "nova_log.txt");
            FileOutputStream fos = new FileOutputStream(logFile);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fos.write((line + "\n").getBytes());
            }
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void submitReport() {
        String desc = mDescription.getText().toString().trim();
        if (desc.isEmpty()) {
            Toast.makeText(this, "Please provide a description.", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Mock submission
        Toast.makeText(this, "Report submitted. Thank you for helping improve NovaOS!", Toast.LENGTH_LONG).show();
        finish();
    }
}
