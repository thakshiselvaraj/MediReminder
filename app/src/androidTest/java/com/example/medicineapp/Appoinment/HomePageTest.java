package com.example.medicineapp.Appoinment;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.medicineapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomePageTest {

    @Rule
    public ActivityTestRule<HomePage> homePageActivityTestRule = new ActivityTestRule<HomePage>(HomePage.class);
    private HomePage home = null;

    @Before
    public void setUp() throws Exception {

        home = homePageActivityTestRule.getActivity();
    }


    @Test
    public void testLaunch()
    {
        View view = home.findViewById(R.id.buttonAppo);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {

        home=null;
    }
}