package net.kikuchy.enumadapter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Spinner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EnumArrayAdapterUnitTest {
    enum Sample {
        A, B, C, D, E, F, G, H, I, J, K, M, N
    }

    Spinner view;

    Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Before
    public void init() {
        view = new Spinner(getContext());
    }

    @Test
    public void selectedItemIsEnum() throws Exception {
        EnumArrayAdapter<Sample> toStringAdapter = new EnumArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, Sample.values(), null);
        view.setAdapter(toStringAdapter);
        view.setSelection(Sample.F.ordinal());
        assertEquals(Sample.F, view.getSelectedItem());

        EnumArrayAdapter<Sample> constantAdapter = new EnumArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, Sample.values(), new Stringify<Sample>() {
            @Override
            public String invoke(Sample target) {
                return "hello";
            }
        });
        view.setAdapter(constantAdapter);
        view.setSelection(Sample.M.ordinal());
        assertEquals(Sample.M, view.getSelectedItem());
    }
}