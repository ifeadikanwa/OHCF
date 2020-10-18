package com.example.ohcf;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreRepository {
    public static final String TAG = FirestoreRepository.class.getSimpleName();
    public static final String CUSTOM_FORM_COLLECTION = "Custom_Form";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    void saveCustomForm(String last_name, String first_name, String middle_initial, String address, String city, String state, String zip, String home_phone, String social_security, String date_of_birth, String age, String sex, String marital_status, String drivers_license_num, String employee_name, String employee_address, String work_phone, String email_address, String cell_phone, String emergency_contact_name, String emergency_phone){
        CustomForm customForm = new CustomForm( last_name, first_name, middle_initial, address, city, state, zip, home_phone, social_security, date_of_birth, age, sex, marital_status, drivers_license_num, employee_name, employee_address, work_phone, email_address, cell_phone, emergency_contact_name, emergency_phone);
        db.collection(CUSTOM_FORM_COLLECTION)
                .add(customForm)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "SUCCESS");
                    }
                });


    }
}
