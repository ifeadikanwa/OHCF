package com.example.ohcf;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuardianInfo2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuardianInfo2Fragment extends Fragment {
    TextInputEditText address_edit;
    TextInputEditText city_edit;
    TextInputEditText state_edit;
    TextInputEditText zip_edit;
    TextInputEditText emer_contact_name_edit;
    TextInputEditText emer_contact_phone_edit;
    TextInputEditText home_phone_edit;

    FirestoreRepository firestoreRepository = new FirestoreRepository();

    ImageButton back_button;
    ImageButton forward_button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuardianInfo2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuardianInfo2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuardianInfo2Fragment newInstance(String param1, String param2) {
        GuardianInfo2Fragment fragment = new GuardianInfo2Fragment();
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
        return inflater.inflate(R.layout.fragment_guardian_info2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        address_edit = view.findViewById(R.id.address);
        city_edit = view.findViewById(R.id.city);
        state_edit = view.findViewById(R.id.state);
        zip_edit = view.findViewById(R.id.zip);
        emer_contact_name_edit = view.findViewById(R.id.emergency_contact_name);
        emer_contact_phone_edit = view.findViewById(R.id.emergency_phone);
        home_phone_edit = view.findViewById(R.id.home_phone);

        back_button = view.findViewById(R.id.backward_button2);
        forward_button = view.findViewById(R.id.forward_button2);

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

                if(address_edit.getText().toString().trim().length() == 0 || address_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setAddress(address_edit.getText().toString());
                }

                if(city_edit.getText().toString().trim().length() == 0 || city_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setCity(city_edit.getText().toString());
                }

                if(state_edit.getText().toString().trim().length() == 0 || state_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setState(state_edit.getText().toString());
                }

                if(zip_edit.getText().toString().trim().length() == 0 || zip_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setZip(zip_edit.getText().toString());
                }

                if(emer_contact_name_edit.getText().toString().trim().length() == 0 || emer_contact_name_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setEmergency_contact_name(emer_contact_name_edit.getText().toString());
                }

                if(emer_contact_phone_edit.getText().toString().trim().length() == 0 || emer_contact_phone_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setEmergency_phone(emer_contact_phone_edit.getText().toString());
                }

                if(home_phone_edit.getText().toString().trim().length() == 0 || home_phone_edit.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    customForm.setHome_phone(home_phone_edit.getText().toString());
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
        Fragment fragment = new GuardianInfo3Fragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}