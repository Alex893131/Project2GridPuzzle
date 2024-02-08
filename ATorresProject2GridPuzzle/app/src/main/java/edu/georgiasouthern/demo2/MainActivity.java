package edu.georgiasouthern.demo2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Animation animation;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result)->{
        if (result.getResultCode() == RESULT_OK) {
            Bitmap image = (Bitmap) result.getData().getExtras().get("data");
            imageView.setImageBitmap(image);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_animation);
        animation.setInterpolator(new LinearInterpolator());
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(v -> {
            imageView.startAnimation(animation);
//            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//            startActivity(intent);
        });

        Button bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, 1);
            launcher.launch(intent);
        });

    }
}