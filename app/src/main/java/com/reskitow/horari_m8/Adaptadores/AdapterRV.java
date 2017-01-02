package com.reskitow.horari_m8.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reskitow.horari_m8.Model.Horari;
import com.reskitow.horari_m8.R;

import java.util.ArrayList;

/**
 * Created by RW-PC on 27/12/2016.
 */

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.HorariViewHolder> {

    private ArrayList<Horari> horaris;

    public AdapterRV() {
        this.horaris = new ArrayList<>();
    }

    @Override
    public AdapterRV.HorariViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta_horario, parent, false);
        return new HorariViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterRV.HorariViewHolder holder, int position) {
        Horari h = horaris.get(position);
        Context context = holder.itemView.getContext();
        holder.nomModul.setText(h.getModul());
        holder.nomGrup.setText(h.getGrup());
        holder.nomProfessor.setText(context.getString(R.string.txt_professor) + " " + h.getProfessor());
        holder.horari.setText(context.getString(R.string.txt_hora_inici) + " " + h.getHoraInici() + " " +
                context.getString(R.string.txt_hora_final) + " " + h.getHoraFinal());
        holder.nomAula.setText(context.getString(R.string.txt_aula) + " " + h.getAula());
    }

    @Override
    public int getItemCount() {
        return horaris.size();
    }

    public class HorariViewHolder extends RecyclerView.ViewHolder {

        private TextView nomModul;
        private TextView nomGrup;
        private TextView nomProfessor;
        private TextView horari;
        private TextView nomAula;

        public HorariViewHolder(View itemView) {
            super(itemView);
            nomModul = (TextView) itemView.findViewById(R.id.modul_widget);
            nomGrup = (TextView) itemView.findViewById(R.id.nom_widget);
            nomProfessor = (TextView) itemView.findViewById(R.id.profe_widget);
            horari = (TextView) itemView.findViewById(R.id.horas_horari);
            nomAula = (TextView) itemView.findViewById(R.id.aula_widget);
        }
    }

    public void setHoraris(ArrayList<Horari> horaris) {
        this.horaris = horaris;
        notifyDataSetChanged();
    }
}
