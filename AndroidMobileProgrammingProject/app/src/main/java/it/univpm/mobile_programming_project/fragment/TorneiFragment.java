package it.univpm.mobile_programming_project.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.tornei.CreaTorneoFragment;
import it.univpm.mobile_programming_project.tornei.PartecipaTorneoFragment;
import it.univpm.mobile_programming_project.tornei.StoricoTorneiFragment;
import it.univpm.mobile_programming_project.tornei.TorneiPageAdapter;
import it.univpm.mobile_programming_project.view_pager.SpesePageAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TorneiFragment extends Fragment {

    public static final int PARTECIPA = 0;
    public static final int CREA = 1;
    public static final int STORICO = 2;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private int paginaDiLancio;

    public TorneiFragment (){
        this.paginaDiLancio=0;
    }

    public TorneiFragment(int paginaDiLancio) {
        switch (paginaDiLancio) {
            case TorneiFragment.PARTECIPA:
            case TorneiFragment.CREA:
            case TorneiFragment.STORICO:
                this.paginaDiLancio = paginaDiLancio;
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tornei, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pagerAdapter = new TorneiPageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        // Naviga direttamente alla pagina specificata nel costruttore,
        // oppure alla prima pagina se non specificata
        viewPager.setCurrentItem(this.paginaDiLancio);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


                pagerAdapter.notifyDataSetChanged();

                int titoloId = R.string.tornei;
                Activity activity = getActivity();

                switch(tab.getPosition()){
                    case TorneiFragment.PARTECIPA:
                        titoloId = R.string.partecipatorneo;
                        break;

                    case TorneiFragment.CREA:
                        titoloId = R.string.creatorneo;
                        break;

                    case TorneiFragment.STORICO:
                        titoloId = R.string.storicotorneo;
                        break;
                }

                ((HomeActivity)activity).setToolbarTitle( activity.getString(titoloId) );


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
