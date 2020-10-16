package com.example.medicineapp.Appoinment;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.medicineapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class activityAppoinmentTest2 {

    @Rule
    public ActivityTestRule<activityAppoinment> mainActivityTestRule = new ActivityTestRule<activityAppoinment>(activityAppoinment.class);
    private activityAppoinment mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view = mActivity.findViewById(R.id.button1);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}