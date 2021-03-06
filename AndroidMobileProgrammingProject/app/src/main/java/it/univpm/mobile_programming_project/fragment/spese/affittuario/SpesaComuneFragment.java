package it.univpm.mobile_programming_project.fragment.spese.affittuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;

import java.util.Date;
import java.util.List;

import it.univpm.mobile_programming_project.HomeActivity;
import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.spese.listener.HandlerSpesaPagataListener;
import it.univpm.mobile_programming_project.fragment.spese.listener.OnSpesaPagataListener;
import it.univpm.mobile_programming_project.fragment.spese.recycler.InterfaceSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.adapter.SommarioSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.adapter.SpesaComuneSpeseAdapter;
import it.univpm.mobile_programming_project.fragment.spese.recycler.view_holder.SpesaViewHolder;
import it.univpm.mobile_programming_project.models.Spesa;
import it.univpm.mobile_programming_project.utils.firebase.FirebaseFunctionsHelper;
import it.univpm.mobile_programming_project.utils.recycler_view.RecyclerViewClickListener;
import it.univpm.mobile_programming_project.utils.shared_preferences.UtenteSharedPreferences;


public class SpesaComuneFragment extends Fragment implements RecyclerViewClickListener {

    private RecyclerView recyclerViewSpesaComune;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Spesa> speseSpesaComune;
    private UtenteSharedPreferences utenteSharedPreferences;
    private FirebaseFunctionsHelper firebaseFunctionsHelper;
    private LinearLayout linearLayout;
    private HandlerSpesaPagataListener handlerSpesaPagata;

    public SpesaComuneFragment(List<Spesa> speseSpesaComune, HandlerSpesaPagataListener handlerSpesaPagata) {
        this.speseSpesaComune = speseSpesaComune;
        this.handlerSpesaPagata = handlerSpesaPagata;
    }

    public static RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        firebaseFunctionsHelper = new FirebaseFunctionsHelper();
        utenteSharedPreferences =new UtenteSharedPreferences(getContext());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spesa_comune, container, false);
        linearLayout = view.findViewById(R.id.LinearLayoutNessunaSpesa);
        if (speseSpesaComune.size()==0){
            linearLayout.setVisibility(View.VISIBLE);
        }else {
            linearLayout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerViewSpesaComune = view.findViewById(R.id.recyclerViewSpesaComune);
        recyclerViewSpesaComune.setHasFixedSize(true);
        recyclerViewSpesaComune.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewSpesaComune.setLayoutManager(layoutManager);

        adapter = new SpesaComuneSpeseAdapter(this.speseSpesaComune, this);
        recyclerViewSpesaComune.setAdapter(adapter);

        this.handlerSpesaPagata.addListener(new OnSpesaPagataListener() {
            @Override
            public void notifySpesaPagata(Spesa spesaPagata) {
                ((InterfaceSpeseAdapter)adapter).updateSpesa(spesaPagata);
            }
        });
    }


    @Override
    public void onClick(View view, Object object) {
        final SpesaViewHolder spesaHolder = (SpesaViewHolder)object;
        final Spesa spesa = spesaHolder.adapter.getSpesa(spesaHolder.getAdapterPosition());
        String idCasa = utenteSharedPreferences.getIdCasa();

            ((HomeActivity) SpesaComuneFragment.this.getActivity()).startLoading();

            SpesaComuneFragment.this.firebaseFunctionsHelper.pagaSpesa(spesa.getIdSpesa(), idCasa).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                @Override
                public void onComplete(@NonNull Task<Boolean> task) {

                    // Handle Error
                    if (!task.isSuccessful()) {
                        Exception e = task.getException();
                        if (e instanceof FirebaseFunctionsException) {
                            FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                            FirebaseFunctionsException.Code code = ffe.getCode();
                            Object details = ffe.getDetails();
                        }
                        ((HomeActivity) SpesaComuneFragment.this.getActivity()).stopLoading();
                        return;
                    }

                    Boolean isSpesaPagata= task.getResult();
                    if (isSpesaPagata) {
                        spesaHolder.txtNonPagata.setVisibility(View.GONE);
                        spesaHolder.txtPagata.setVisibility(View.VISIBLE);
                        spesaHolder.btnPaga.setVisibility(View.GONE);

                        handlerSpesaPagata.pagaSpesa( spesa );

                        Toast.makeText(SpesaComuneFragment.this.getContext(), "Spesa pagata con successo.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SpesaComuneFragment.this.getContext(), "Errore nel pagamento della spesa.", Toast.LENGTH_LONG).show();
                    }
                    ((HomeActivity) SpesaComuneFragment.this.getActivity()).stopLoading();
                }
            });
    }

}
