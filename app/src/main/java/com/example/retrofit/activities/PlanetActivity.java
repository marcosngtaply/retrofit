package com.example.retrofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.adapter.PlanetAdapter;
import com.example.retrofit.bootstrap.PlanetAPI;
import com.example.retrofit.model.DefaultModel;
import com.example.retrofit.model.Planet;
import com.example.retrofit.resource.PlanetsResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlanetActivity extends AppCompatActivity {

    ListView listViewPlanets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
    }

    public void listarPlanets(View view) {
        //Criar função para trabalhar com o retrofit

        Retrofit retrofit = PlanetAPI.getClient();

        /*Fazer a Inverção de Controle e injeção de dependência da interface
        (contrato) PostResource */

        PlanetsResource planetResource = retrofit.create(PlanetsResource.class);

        //Fazer o método/operação pretendido.

        Call<DefaultModel> lista = planetResource.get();

        /* Utilizar a estrutura de dados FILA (FIFO) para trabalhar
        com chamadas assíncronas.
        */
        try {
            lista.enqueue(new Callback<DefaultModel>() {
                @Override
                public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                    // O método onResponse retorna os dados do recurso(resource) consumido.
                    try {
                        List<Planet> planetas = response.body().getResults();

                        PlanetAdapter p = new PlanetAdapter(getApplicationContext(), planetas);

                        listViewPlanets = findViewById(R.id.planetsList);
                        listViewPlanets.setAdapter(p);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                                        "no processamento.\n\n" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("lista -- Erro", "\n\n" + e.getMessage() + "\n" +
                                "\n");
                    }
                }

                @Override
                public void onFailure(Call<DefaultModel> call, Throwable t) {
                    //Método responsável pelos erros.
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                            "no serviço.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("app-planets", "\n\n" + t.getMessage() + "\n\n");
                }
            });
        } catch (Exception e) {
            Log.i("ERRO!\n", "Erro - na busca de dados da API" + e.getMessage());
        }
    }
}
