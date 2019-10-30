package com.another.javreviewguided.ui.main;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.another.javreviewguided.JEmployee;
import com.another.javreviewguided.R;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        JEmployee mjEmployee = new JEmployee(12,"adf");
            mjEmployee.setPosition("something else for position");
            KEmployee mkEmployee = new KEmployee(21,"asdf","asdf");
        TextView tv = getView().findViewById(R.id.message);

        tv.setText(mkEmployee.toString());
    }

}
