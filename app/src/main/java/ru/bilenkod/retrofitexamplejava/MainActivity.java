package ru.bilenkod.retrofitexamplejava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonInfoApi jsonInfoApi = retrofit.create(JsonInfoApi.class);
        Call<List<Post>> posts = jsonInfoApi.getPosts();
        posts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    result.setText(String.format(Locale.getDefault(), "Code: %d", response.code()));
                    return;
                }
                List<Post> postList = response.body();
                StringBuilder content = new StringBuilder();
                if (postList != null) {
                    for (Post post : postList) {
                        content.append("ID: ").append(post.getId()).append("\n");
                        content.append("User ID: ").append(post.getUserId()).append("\n");
                        content.append("Name: ").append(post.getName()).append("\n");
                        content.append("Content: ").append(post.getBody()).append("\n\n");
                    }
                }
                result.setText(content.toString());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

}
