package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.copernic.rodriguez.albert.m7t1.R;
import cat.copernic.rodriguez.albert.m7t1.classes.Negoci;


public class BotiguesAdapter extends RecyclerView.Adapter<BotiguesAdapter.ViewHolder> {

    //Member variables
    private final ArrayList<Negoci> mBotiguesData;
    private Context mContext;

    BotiguesAdapter(Context context, ArrayList<Negoci> botiguesData) {
        this.mBotiguesData = botiguesData;
        this.mContext = context;
    }

    //Necessari per crear el ViewHolder
    //Fa falta crear list_item
    @NonNull
    @Override
    public BotiguesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_botigues, parent, false));
    }

    //Per donar dades al ViewHolder
    @Override
    public void onBindViewHolder(BotiguesAdapter.ViewHolder holder, int position) {
        //Obtenir l'indret actual
        Negoci currentBotiga = mBotiguesData.get(position);
        //Omplir els TextView de dades
        holder.bindTo(currentBotiga);
    }

    //Mètode per determinar la quantitat de dades
    @Override
    public int getItemCount() {
        return mBotiguesData.size();
    }

    //ViewHolder per representar cada casella de dades
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Variables membres per als TextView
        private TextView mNomNegoci; //CANVIAR
        private TextView mDescripcioNegoci;
        private TextView mUbicacioNegoci;

        //Constructor pel ViewHolder, fet servir a onCreateViewHolder()
        ViewHolder(View itemView) {
            super(itemView);

            //Inicialitzar les Views
            mNomNegoci= itemView.findViewById(R.id.txtNom);
            //mInfoText = itemView.findViewById(R.id.subTitle);
            mDescripcioNegoci = itemView.findViewById(R.id.txtDescripcio);
            mUbicacioNegoci = itemView.findViewById(R.id.txtUbicacioNegoci);

            //Establir el onClickListener a tota la vista
            itemView.setOnClickListener(this);
        }

        void bindTo(Negoci currentNegoci) {
            //Populate the textviews with data
            mNomNegoci.setText(currentNegoci.getNomNegoci());
            mDescripcioNegoci.setText(currentNegoci.getDescripcioNegoci());
            mUbicacioNegoci.setText(currentNegoci.getUbicacioNegoci());
        }


        //Per quan es cliqui, s'obri una pantalla (intent) amb l'indret.
        @Override
        public void onClick(View view) {
            Negoci currentNegoci = mBotiguesData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, BotigaConcretaReceptor.class);
            detailIntent.putExtra("idNegoci", currentNegoci.getIdNegoci());
            detailIntent.putExtra("nomNegoci", currentNegoci.getNomNegoci());
            detailIntent.putExtra("descripcioNegoci", currentNegoci.getDescripcioNegoci());
            detailIntent.putExtra("ubicacioNegoci", currentNegoci.getUbicacioNegoci());
            detailIntent.putExtra("idUsuari", currentNegoci.getIdUsuari());
            mContext.startActivity(detailIntent);
        }
    }
}

