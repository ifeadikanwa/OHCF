package com.example.ohcf;

import java.util.ArrayList;

public class FormFields {

    private static ArrayList<Integer> FieldIds = new ArrayList<>();;
    public FormFields() {
        FieldIds.add(R.string.last_name);
        FieldIds.add(R.string.first_name);
        FieldIds.add(R.string.middle_initial);
        FieldIds.add(R.string.address);
        FieldIds.add(R.string.city);
        FieldIds.add(R.string.state);
        FieldIds.add(R.string.zip);
        FieldIds.add(R.string.home_phone);
        FieldIds.add(R.string.social_security);
        FieldIds.add(R.string.date_of_birth);
        FieldIds.add(R.string.age);
        FieldIds.add(R.string.sex);
        FieldIds.add(R.string.marital_status);
        FieldIds.add(R.string.drivers_lic_num);
        FieldIds.add(R.string.employee_name);
        FieldIds.add(R.string.employee_address);
        FieldIds.add(R.string.work_phone);
        FieldIds.add(R.string.email_address);
        FieldIds.add(R.string.cell_phone);
        FieldIds.add(R.string.emerg_phone);
        FieldIds.add(R.string.emerg_contact_name);
        FieldIds.add(R.string.allergy);
        FieldIds.add(R.string.family_history);
    }

    public static ArrayList<Integer> getFieldIds(){
        return FieldIds;
    }
}
