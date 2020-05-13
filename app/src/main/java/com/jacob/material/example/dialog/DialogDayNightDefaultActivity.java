/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonDayNightDefaultActivityBinding;
import com.jacob.material.databinding.DialogDayNightDefaultActivityBinding;
import com.jacob.temp.TempConstant;

public class DialogDayNightDefaultActivity extends AppCompatActivity {
    private DialogDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.dialog_day_night_default_activity);

        String[] choices = {"Choice1", "Choice2", "Choice3"};
        boolean[] choicesInitial = {false, true, false};

        String positiveText = "Accept";
        String negativeText = "Decline";
        String neutralText = "Cancel";
        String title = "Title";
        String message = "Message";


        binding.appCompatAlertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(positiveText, null)
                        .setNegativeButton(negativeText, null)
                        .setNeutralButton(neutralText, null).show();
            }
        });

        binding.materialAlertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(positiveText, null)
                        .setNegativeButton(negativeText, null)
                        .setNeutralButton(neutralText, null).show();
            }
        });


        binding.materialIconDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(positiveText, null)
                        .setNegativeButton(negativeText, null)
                        .setNeutralButton(neutralText, null)
                        .setIcon(R.drawable.icon_access_time).show();
            }
        });


        binding.customViewDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setView(R.layout.dialog_text_view)
                        .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView input = ((AlertDialog) dialog).findViewById(R.id.edit_text);
                                Toast.makeText(DialogDayNightDefaultActivity.this, input.getText(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton(neutralText, null)
                        .setIcon(R.drawable.icon_access_time).show();
            }
        });

        binding.itemListDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setItems(choices, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String item = choices[which];
                                Toast.makeText(DialogDayNightDefaultActivity.this, item, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton(neutralText, null)
                        .show();
            }
        });

        binding.multiSelectDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setMultiChoiceItems(choices, choicesInitial, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {}
                        })

                        .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                AlertDialog dialog = ((AlertDialog) dialogInterface);
                                ListView listView = dialog.getListView();
                                int itemCount = listView.getCount();
                                String str = "";

                                for(int i=0; i<itemCount; i++){
                                    Checkable checkable = (Checkable)listView.getChildAt(i);
                                    if(checkable.isChecked()){
                                        if(str.length() > 0){
                                            str = str + "," + choices[i];
                                        }else{
                                            str = choices[i];
                                        }
                                    }
                                }
                                Toast.makeText(DialogDayNightDefaultActivity.this, str, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton(neutralText, null).show();
            }
        });


        binding.singleSelectDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(DialogDayNightDefaultActivity.this)
                        .setTitle(title)
                        .setSingleChoiceItems(choices, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                            }
                        })

                        .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                AlertDialog dialog = ((AlertDialog) dialogInterface);
                                ListView listView = dialog.getListView();
                                String str = choices[listView.getCheckedItemPosition()];
                                Toast.makeText(DialogDayNightDefaultActivity.this, str, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton(neutralText, null).show();
            }
        });

    }

}
