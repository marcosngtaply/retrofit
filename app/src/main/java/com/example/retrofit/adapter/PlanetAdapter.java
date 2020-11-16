package com.example.retrofit.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.model.Planet;

import java.util.List;

public class PlanetAdapter extends BaseAdapter {

    private final Context context;
    private final List<Planet> planets;

    public PlanetAdapter(Context context, List<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }

    @Override
    public int getCount() {
        return this.planets != null ? this.planets.size() : 0;
    }

    @Override
    public Planet getItem(int i) {
        return this.planets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_planet, viewGroup, false);
        }

        //Procura o item dentro da lista para ser 'exibido' na listView
        Planet planets = getItem(posicao);

        //Criar a referência de atributos/objeto java para ser customizar uma listView
        TextView txtPlanetName, txtRotationPeriod, txtOrbitalPeriod, txtDiameter,
                txtClimate, txtGravity, txtTerrain, txtSurfaceWater, txtPopulation;

        try {
            txtPlanetName = view.findViewById(R.id.txtPlanetName);
            txtRotationPeriod = view.findViewById(R.id.txtRotationPeriod);
            txtOrbitalPeriod = view.findViewById(R.id.txtOrbitalPeriod);
            txtDiameter = view.findViewById(R.id.txtDiameter);
            txtClimate = view.findViewById(R.id.txtClimate);
            txtGravity = view.findViewById(R.id.txtGravity);
            txtTerrain = view.findViewById(R.id.txtTerrain);
            txtSurfaceWater = view.findViewById(R.id.txtSurfaceWater);
            txtPopulation = view.findViewById(R.id.txtPopulation);

            txtPlanetName.setText(planets.getName());
            txtRotationPeriod.setText(planets.getRotation_period());
            txtOrbitalPeriod.setText(planets.getOrbital_period());
            txtDiameter.setText(planets.getDiameter());
            txtClimate.setText(planets.getClimate());
            txtGravity.setText(planets.getGravity());
            txtTerrain.setText(planets.getTerrain());
            txtSurfaceWater.setText(planets.getSurface_water());
            txtPopulation.setText(planets.getPopulation());
        } catch (Exception e) {
            Log.e("Erro!", "Erro ao extrair valores dos campos" + e.getMessage());
        }
        return view;
    }

}
