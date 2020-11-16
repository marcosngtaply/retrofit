package com.example.retrofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.bootstrap.APIClient;
import com.example.retrofit.model.Post;
import com.example.retrofit.resource.PostResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    /**
     * Método de exemplo para listar serviços na internet
     * Utilizando o retrofit
     */
    public void listarPosts(View view) {
        //Passo 6 - Criar Função para trabalhar como  retrofit (Retrofit)
        retrofit = APIClient.getClient();

        //Passo 7 - Fazer a IoC e injeção de dependência da interface (contrato) PostResource
        PostResource postResource = retrofit.create(PostResource.class);

        //Passo 8 - Fazer o método/operação preferido
        Call<List<Post>> lista = postResource.get();

        //Passo 9 - Utilizar a estrutura de dados FILA (FIFO) para trabalhar com chamadas assincronas.
        lista.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //O método onResponse retorna os dados da lista (operação sucesso 200)
                List<Post> posts = response.body();

                for (int i = 0; i < posts.size(); i++) {
                    Log.i("post", String.format("%d %s", i, posts.get(i).toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //Método responsável pela tratativa de erros
                Toast.makeText(getApplicationContext(),
                        "Ocorreu um erro no serviço.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}