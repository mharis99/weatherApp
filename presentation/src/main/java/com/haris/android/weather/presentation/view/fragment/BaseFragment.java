
package com.haris.android.weather.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.haris.android.weather.presentation.internal.di.HasComponent;


public abstract class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
