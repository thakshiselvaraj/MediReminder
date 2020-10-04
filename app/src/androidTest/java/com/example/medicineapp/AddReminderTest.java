package com.example.medicineapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddReminderTest {

    @Rule
    public ActivityTestRule<AddReminder> mActivityTestRuler = new ActivityTestRule<>(AddReminder.class);

    private AddReminder mActivityr = null;

    @Before
    public void setUp() throws Exception {

        mActivityr = mActivityTestRuler.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = mActivityr.findViewById(R.id.btn_save);

        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        mActivityr = null;
    }

}