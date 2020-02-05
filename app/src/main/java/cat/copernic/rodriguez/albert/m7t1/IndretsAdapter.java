package cat.copernic.rodriguez.albert.m7t1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IndretsAdapter extends RecyclerView.Adapter<IndretsAdapter.ViewHolder> {

    //Member variables
    private final ArrayList<Indret> mIndretsData;
    private Context mContext;

    //Constructor
    public IndretsAdapter(Context context, ArrayList<Indret> indretsData) {
        this.mIndretsData = indretsData;
        this.mContext = context;
    }


    //Necessari per crear el ViewHolder
    //Fa falta crear list_item
    @NonNull
    @Override
    public IndretsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    //Per donar dades al ViewHolder
    @Override
    public void onBindViewHolder(IndretsAdapter.ViewHolder holder, int position) {
        //Obtenir l'indret actual
        Indret currentIndret = mIndretsData.get(position);
        //Omplir els TextView de dades
        holder.bindTo(currentIndret);
    }

    //Mètode per determinar la quantitat de dades
    @Override
    public int getItemCount() {
        return mIndretsData.size();
    }


    //ViewHolder per representar cada casella de dades
    class ViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener*/ {

        //Variables membres per als TextView
        private TextView mNomText;
        private TextView mInfoText;

        //Constructor pel ViewHolder, fet servir a onCreateViewHolder()
        ViewHolder(View itemView) {
            super(itemView);

            //Inicialitzar les Views
            mNomText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);

            //Establir el onClickListener a tota la vista
            //itemView.setOnClickListener(this);
        }

        void bindTo(Indret currentSport) {
            //Populate the textviews with data
            mNomText.setText(currentSport.getNom());
            mInfoText.setText(currentSport.getInfo());
        }


        //Per quan es cliqui, s'obri una pantalla (intent) amb l'indret.
        /*
        @Override
        public void onClick(View view) {
            Indret currentSport = mIndretsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            mContext.startActivity(detailIntent);
        }*/
    }
}
