package cat.copernic.rodriguez.albert.m7t1.apartats_donant;

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
import cat.copernic.rodriguez.albert.m7t1.classes.Oferta;

public class MiraOfertesPropiesAdapter extends RecyclerView.Adapter<MiraOfertesPropiesAdapter.ViewHolder> {

    //Member variables
    private final ArrayList<Oferta> mOfertesData;
    private Context mContext;

    MiraOfertesPropiesAdapter(Context context, ArrayList<Oferta> ofertesData) {
        this.mOfertesData = ofertesData;
        this.mContext = context;
    }

    //Necessari per crear el ViewHolder
//Fa falta crear list_item
    @NonNull
    @Override
    public MiraOfertesPropiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.donant_list_item, parent, false));
    }

    //Per donar dades al ViewHolder
    @Override
    public void onBindViewHolder(MiraOfertesPropiesAdapter.ViewHolder holder, int position) {
        //Obtenir l'indret actual
        Oferta currentOferta = mOfertesData.get(position);
        //Omplir els TextView de dades
        holder.bindTo(currentOferta);
    }

    //Mètode per determinar la quantitat de dades
    @Override
    public int getItemCount() {
        return mOfertesData.size();
    }

    //ViewHolder per representar cada casella de dades
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Variables membres per als TextView
        private TextView mNomText; //CANVIAR
        private TextView mInfoText; //CANVIAR

        //Constructor pel ViewHolder, fet servir a onCreateViewHolder()
        ViewHolder(View itemView) {
            super(itemView);

            //Inicialitzar les Views
            mNomText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);

            //Establir el onClickListener a tota la vista
            itemView.setOnClickListener(this);
        }

        void bindTo(Oferta currentOferta) {
            //Populate the textviews with data
            mNomText.setText(currentOferta.getTitolOferta());
            mInfoText.setText(currentOferta.getDescripcioOferta());
        }


        //Per quan es cliqui, s'obri una pantalla (intent) amb l'indret.
        @Override
        public void onClick(View view) {
            Oferta currentOferta = mOfertesData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, OfertaConcretaDonant.class);
            detailIntent.putExtra("titol", currentOferta.getTitolOferta());
            detailIntent.putExtra("descripcio", currentOferta.getDescripcioOferta());
            detailIntent.putExtra("horari", currentOferta.getHorariRecogida());
            detailIntent.putExtra("ubicacio", currentOferta.getUbicacioNegoci());
            detailIntent.putExtra("posicio", currentOferta.getIdOferta());
            System.out.println(detailIntent.putExtra("posicio", currentOferta.getIdOferta()));
            System.out.println("La posició a l'adapter és: " + currentOferta.getIdOferta());
            System.out.println("Adapter: " + currentOferta.toString());
            mContext.startActivity(detailIntent);
        }
    }
}

