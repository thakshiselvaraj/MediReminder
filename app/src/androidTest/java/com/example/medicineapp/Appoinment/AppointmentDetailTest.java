package com.example.medicineapp.Appoinment;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.medicineapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentDetailTest {

    @Rule
    public ActivityTestRule<AppointmentDetail> appointmentDetailActivityTestRule = new ActivityTestRule<AppointmentDetail>(AppointmentDetail.class);
    private AppointmentDetail appointmentDetail = null;

    @Before
    public void setUp() throws Exception {
        appointmentDetail = appointmentDetailActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view = appointmentDetail.findViewById(R.id.buttonAddNew);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        appointmentDetail = null;
    }
}