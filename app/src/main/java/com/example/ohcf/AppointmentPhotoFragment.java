package com.example.ohcf;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentPhotoFragment extends Fragment {
    ImageButton forward_button;
    ImageButton back_button;
    ImageButton importPicture;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> formString;

    public AppointmentPhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentPhotoFragment newInstance(String param1, String param2) {
        AppointmentPhotoFragment fragment = new AppointmentPhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        formString = new ArrayList<>();
        for (Field field : R.string.class.getDeclaredFields())
        {
            if (Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers()) && field.getType().equals(int.class))
            {
                try
                {
                    formString.add(getString(getResources().getIdentifier(field.getName(), "string", getActivity().getPackageName())));
                } catch (IllegalArgumentException e)
                {
                    // ignore
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        back_button = view.findViewById(R.id.reg_backward_button);
        forward_button = view.findViewById(R.id.reg_forward_button);
        importPicture = view.findViewById(R.id.imageButton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MyAppointmentFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PhotoToTextFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        importPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/Download/");
                intent.setDataAndType(uri, "image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == PICK_IMAGE) {
            Uri selectedImage = data.getData();

            FirebaseVisionImage image;
            try {
                image = FirebaseVisionImage.fromFilePath(getActivity(), selectedImage);
                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                        .getOnDeviceTextRecognizer();
                Task<FirebaseVisionText> result =
                        detector.processImage(image)
                                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                                    @Override
                                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                        FirebaseVisionText res = firebaseVisionText;
                                        String resultText = res.getText();
                                        Log.d("resultText", res.getText());
                                        Fragment fragment = new PhotoToTextFragment();
                                        Bundle args = new Bundle();
                                        for (FirebaseVisionText.TextBlock block: res.getTextBlocks()) {
                                            String blockText = block.getText();
                                            Float blockConfidence = block.getConfidence();
                                            List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                                            Point[] blockCornerPoints = block.getCornerPoints();
                                            Rect blockFrame = block.getBoundingBox();

                                            for (FirebaseVisionText.Line line: block.getLines()) {
                                                String lineText = line.getText().toLowerCase();
                                                Log.d("forminfo", lineText);
                                                for(String formField: formString) {
                                                    if (lineText.contains(formField.toLowerCase()) && formField.length() > 2) {
                                                        args.putString(formField, lineText);
                                                        continue;
                                                    }
                                                }
                                                Float lineConfidence = line.getConfidence();
                                                List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                                                Point[] lineCornerPoints = line.getCornerPoints();
                                                Rect lineFrame = line.getBoundingBox();
                                                for (FirebaseVisionText.Element element: line.getElements()) {
                                                    String elementText = element.getText();
                                                    Float elementConfidence = element.getConfidence();
                                                    List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                                                    Point[] elementCornerPoints = element.getCornerPoints();
                                                    Rect elementFrame = element.getBoundingBox();
                                                }
                                            }
                                        }
                                        fragment.setArguments(args);
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                    }
                                })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Task failed with an exception
                                                Log.d("error", e.getMessage());
                                                // ...
                                            }
                                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}