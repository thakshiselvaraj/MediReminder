package com.example.medicineapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayDetailsTest {

    @Rule
    public ActivityTestRule<DisplayDetails> mActivityTestRulen = new ActivityTestRule<>(DisplayDetails.class);

    private DisplayDetails mActivityn = null;

    @Before
    public void setUp() throws Exception {

        mActivityn = mActivityTestRulen.getActivity();
    }

    @Test
    public void testLaunch(){

        View view = mActivityn.findViewById(R.id.btn_add);

        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {

        mActivityn= null;

    }

}