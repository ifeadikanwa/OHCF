package com.example.ohcf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuardianInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuardianInfoFragment extends Fragment {
    ImageButton back_button;
    ImageButton forward_button;
    TextInputEditText last_name_edit;
    TextInputEditText first_name_edit;
    TextInputEditText middle_initial_edit;
    TextInputEditText date_of_birth_edit;
    TextInputEditText age_edit;
    TextInputEditText sex_edit;
    TextInputEditText marital_status_edit;

    FirestoreRepository firestoreRepository = new FirestoreRepository();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuardianInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuardianInfoFragment newInstance(String param1, String param2) {
        GuardianInfoFragment fragment = new GuardianInfoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guardian_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        last_name_edit = view.findViewById(R.id.last_name);
        first_name_edit = view.findViewById(R.id.first_name);
        middle_initial_edit = view.findViewById(R.id.middle_initial);
        date_of_birth_edit = view.findViewById(R.id.date_birth);
        age_edit = view.findViewById(R.id.age);
        sex_edit = view.findViewById(R.id.sex);
        marital_status_edit = view.findViewById(R.id.marital_status);

        back_button = view.findViewById(R.id.backward_button);
        forward_button = view.findViewById(R.id.forward_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new GuardianInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomForm customForm = new CustomForm();

                if(last_name_edit.getText().toString().trim().length() == 0 || last_name_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setLast_name(last_name_edit.getText().toString());
                }

                if(first_name_edit.getText().toString().trim().length() == 0 || first_name_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setFirst_name(first_name_edit.getText().toString());
                }

                if(middle_initial_edit.getText().toString().trim().length() == 0 || middle_initial_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setMiddle_initial(middle_initial_edit.getText().toString());
                }

                if(date_of_birth_edit.getText().toString().trim().length() == 0 || date_of_birth_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setDate_of_birth(date_of_birth_edit.getText().toString());
                }

                if(age_edit.getText().toString().trim().length() == 0 || age_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setAge(age_edit.getText().toString());
                }

                if(sex_edit.getText().toString().trim().length() == 0 || sex_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setSex(sex_edit.getText().toString());
                }

                if(marital_status_edit.getText().toString().trim().length() == 0 || marital_status_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setMarital_status(marital_status_edit.getText().toString());
                }

                firestoreRepository.db
                        .collection(FirestoreRepository.CUSTOM_FORM_COLLECTION)
                        .add(customForm)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i("User Info 1", "completed 1");
                                goToNextFragment();
                            }
                        });
            }
        });

    }

    private void goToNextFragment() {
        Fragment fragment = new GuardianInfo2Fragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}