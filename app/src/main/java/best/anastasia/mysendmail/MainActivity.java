package best.anastasia.mysendmail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mMail;
    private EditText mPhone;
    private EditText mPassword;
    private Button mView;
    private Button mCamera;
    final int CAMERA_REQUEST = 1;
    private Uri picUri;


    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isEmailValid() && isPhoneValid()&& isPasswordValid()) {
                Intent startProfileIntent =
                        new Intent(MainActivity.this, SecondActivity.class);
                startProfileIntent.putExtra(SecondActivity.EMAIL_KEY, mMail.getText().toString());
                startProfileIntent.putExtra(SecondActivity.PHONE_KEY, mPhone.getText().toString());
                startProfileIntent.putExtra(SecondActivity.PASSWORD_KEY, mPassword.getText().toString());
                startActivity(startProfileIntent);
            } else {
                showMessage(R.string.email_input_error);
            }
        }
    };

    private View.OnClickListener mOnEnterCamera = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                // Намерение для запуска камеры
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_REQUEST);
            } catch (ActivityNotFoundException e) {
                // Выводим сообщение об ошибке
                showMessage(R.string.camera_error);

            }

        }
    };

    private boolean isEmailValid() {
        return !TextUtils.isEmpty(mMail.getText())
                && Patterns.EMAIL_ADDRESS.matcher(mMail.getText()).matches();
    }

    private boolean isPhoneValid() {
        return !TextUtils.isEmpty(mPhone.getText())
                && Patterns.PHONE.matcher(mPhone.getText()).matches();
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(mPassword.getText());
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMail = findViewById(R.id.etMail);
        mPhone = findViewById(R.id.etPhone);
        mPassword = findViewById(R.id.etPassword);
        mView = findViewById(R.id.buttonView);
        mCamera = findViewById(R.id.buttonPhoto);


        mView.setOnClickListener(mOnEnterClickListener);
        mCamera.setOnClickListener(mOnEnterCamera);
    }
}
