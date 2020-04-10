/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.button;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonDayNightDefaultActivityBinding;
import com.jacob.material.databinding.ButtonHighEmphasisActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class ButtonHighEmphasisActivity extends AppCompatActivity {
    private ButtonHighEmphasisActivityBinding binding;
    private int invisiableType;
    private int visiableType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.button_high_emphasis_activity);

        WidgetsUtils.setSystemBarColor(this, android.R.attr.colorBackground);
        WidgetsUtils.setSystemBarLight(this);
        WidgetsUtils.setNavigationBarColor(this, android.R.attr.colorBackground);

        String s1 = "已有账号用户请直接";
        String s2 = "登陆";


        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(s1);
        builder.append(s2);
        builder.setSpan(new LoginClickableSpan(), s1.length(), s1.length() + s2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), s1.length(), s1.length() + s2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        binding.noteTextView.setMovementMethod(LinkMovementMethod.getInstance());//否则不支持ClickableSpan
        binding.noteTextView.setHighlightColor(Color.TRANSPARENT);//避免点击之后，改变背景颜色
        binding.noteTextView.setText(builder);

        invisiableType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        visiableType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

        binding.pwdEditText.setInputType(invisiableType);
        binding.pwdVisiableButton.setChecked(false);
        binding.pwdVisiableButton.setOnClickListener(new OnPwdVisiableButtonClickListener());

        binding.registerButton.setOnClickListener(new OnRegisterButtonClickListener());

        binding.headerButton.setOnClickListener(new OnHeaderButtonClickListener());
    }

    private class OnRegisterButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Snackbar snackbar = Snackbar.make(binding.constraintLayout, "检查Email或电话号码格式", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    private class OnHeaderButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Snackbar snackbar = Snackbar.make(binding.constraintLayout, "上传或拍摄头像", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    private class OnPwdVisiableButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int orlInputType = binding.pwdEditText.getInputType();

            if(orlInputType == invisiableType){
                binding.pwdEditText.setInputType(visiableType);
                binding.pwdVisiableButton.setChecked(true);
            }else{
                binding.pwdEditText.setInputType(invisiableType);
                binding.pwdVisiableButton.setChecked(false);
            }
            binding.pwdEditText.setSelection(binding.pwdEditText.length());
        }
    }

    private class LoginClickableSpan extends ClickableSpan{
        @Override
        public void onClick(@NonNull View view) {
            Snackbar snackbar = Snackbar.make(binding.constraintLayout, "显示登录界面", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(WidgetsUtils.getColorValue(ButtonHighEmphasisActivity.this, R.attr.colorSecondary));
        }
    }




}
