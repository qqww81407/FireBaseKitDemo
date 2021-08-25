package tw.com.firebasekitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnQrcode, btnImage,btnFace,btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews(){
        btnQrcode = findViewById(R.id.btnQrcode);
        btnImage = findViewById(R.id.btnImage);
        btnFace = findViewById(R.id.btnFace);
        btnText = findViewById(R.id.btnText);

        btnQrcode.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,QRcodeActivity.class);
            startActivity(intent);
        });

        btnImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,ImageLabelActivity.class);
            startActivity(intent);
        });

        btnFace.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FaceActivity.class);
            startActivity(intent);
        });

        btnText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,TextActivity.class);
            startActivity(intent);
        });


    }
}