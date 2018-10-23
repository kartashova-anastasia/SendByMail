package best.anastasia.mysendmail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class SecondActivity extends AppCompatActivity {
    public static final String EMAIL_KEY = "EMAIL_KEY";
    public static final String PHONE_KEY = "PHONE_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";

    private AppCompatImageView mPhoto;
    private TextView mMail;
    private TextView mPhone;
    private TextView mPassword;
    private Button mSend;
    private String name;
    private String directory;

    private View.OnClickListener mOnSendClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpg");
            intent.putExtra(Intent.EXTRA_EMAIL,mMail.getText().toString());
           // File downloadedPicture = new File(directory+"/"+name+".jpg");
            //intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(downloadedPicture));
            intent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            intent.putExtra(Intent.EXTRA_TEXT,"Email: "+mMail.getText().toString()+"Phone:"+mPhone.getText().toString());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mPhoto = findViewById(R.id.ivPhoto);
        mMail = findViewById(R.id.tvEmail);
        mPhone = findViewById(R.id.tvPhone);
        mPassword = findViewById(R.id.tvPassword);
        mSend = findViewById(R.id.buttonSend);

        Bundle bundle = getIntent().getExtras();
        mMail.setText(bundle.getString(EMAIL_KEY));
        mPhone.setText(bundle.getString(PHONE_KEY));
        mPassword.setText(bundle.getString(PASSWORD_KEY));

        mSend.setOnClickListener(mOnSendClickListener);
    }
}
