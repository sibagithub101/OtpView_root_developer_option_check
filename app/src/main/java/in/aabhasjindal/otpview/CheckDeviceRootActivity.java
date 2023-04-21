package com.example.myapplication;


import static com.example.myapplication.DeviceUtils.checkDeveloperOption;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CheckDeviceRootActivity extends AppCompatActivity {
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_device_root);

        findViewById(R.id.btnRoot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DeviceUtils.isDeviceRooted(getApplicationContext())) {
                    rootAlertDialog("This device is rooted. You can't use this app.");
                } else {
                    rootAlertDialog("Your Device Not Rooted");
                }
            }
        });

        findViewById(R.id.btnDeveloper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDeveloperOption(getApplicationContext()) == 1) {
                    check = true;
                    showAlertDialogAndExitApp("Developer Option on Your Device Are you want to off Developer option");
                } else {
                    check = false;
                    rootAlertDialog("Developer Option Off Your Device");
                }
            }
        });
    }


    public void showAlertDialogAndExitApp(String message) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                        startActivity(intent);
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    public void rootAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                       /* Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();*/
                    }
                });

        alertDialog.show();
    }
}