package tw.com.firebasekitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.util.List;


public class ImageLabelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_label);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.gym);
        InputImage image = InputImage.fromBitmap(bitmap,0);
        labelImages(image);

    }

    private void labelImages(InputImage image) {
        ImageLabelerOptions options =
                new ImageLabelerOptions.Builder()
                        .setConfidenceThreshold(0.8f)
                        .build();

        // [START get_detector_options]
        ImageLabeler labeler = ImageLabeling.getClient(options);
        // [END get_detector_options]

        /*
        // [START get_detector_default]
        // Or use the default options:
        ImageLabeler detector = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        // [END get_detector_default]
        */

        // [START run_detector]
        Task<List<ImageLabel>> result =
                labeler.process(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<ImageLabel>>() {
                                    @Override
                                    public void onSuccess(List<ImageLabel> labels) {
                                        // Task completed successfully
                                        // [START_EXCLUDE]
                                        // [START get_labels]
                                        for (ImageLabel label : labels) {
                                            String text = label.getText();
                                            float confidence = label.getConfidence();

                                            Log.i("Lcc",text);
                                            Log.i("Lcc",""+confidence);
                                        }
                                        // [END get_labels]
                                        // [END_EXCLUDE]
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
        // [END run_detector]
    }

    private void configureAndRunImageLabeler(InputImage image) {
        // [START on_device_image_labeler]
        // To use default options:
        ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);

        // Or, to set the minimum confidence required:
        // ImageLabelerOptions options =
        //     new ImageLabelerOptions.Builder()
        //         .setConfidenceThreshold(0.7f)
        //         .build();
        // ImageLabeler labeler = ImageLabeling.getClient(options);

        // [END on_device_image_labeler]

        // Process image with custom onSuccess() example
        // [START process_image]
        labeler.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        // Task completed successfully
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                    }
                });
        // [END process_image]

        // Process image with example onSuccess()
        labeler.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        // [START get_image_label_info]
                        for (ImageLabel label : labels) {
                            String text = label.getText();
                            float confidence = label.getConfidence();
                            int index = label.getIndex();
                        }
                        // [END get_image_label_info]
                    }
                });
    }



}