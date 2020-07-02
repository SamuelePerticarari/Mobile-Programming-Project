package it.univpm.mobile_programming_project.fragment.spese.affittuario;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import it.univpm.mobile_programming_project.fragment.spese.AffittoFragment;
import it.univpm.mobile_programming_project.fragment.spese.BolletteFragment;
import it.univpm.mobile_programming_project.fragment.spese.SommarioFragment;
import it.univpm.mobile_programming_project.fragment.spese.SpeseCondominioFragment;

public class SpesePageAdapterAffittuario extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public SpesePageAdapterAffittuario(@NonNull FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment( Fragment fragment ) {
        fragments.add(fragment);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SommarioFragment();
            case 1:
                return new SpesaComuneFragment();
            case 2:
                return new AffittoFragment();
            case 3:
                return new BolletteFragment();
            case 4:
                return new SpeseCondominioFragment();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments.size();
    }
}
