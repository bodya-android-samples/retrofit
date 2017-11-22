package bodya.popov.ru.retrofitfirsttry;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import bodya.popov.ru.retrofitfirsttry.bean.ResponseBean;
import bodya.popov.ru.retrofitfirsttry.bean.UserBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserDetailsAdapter mUserDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        RecyclerView userDetailsListRecyclerView = findViewById(R.id.user_details_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        userDetailsListRecyclerView.setLayoutManager(manager);

        mUserDetailsAdapter = new UserDetailsAdapter(new ArrayList<UserBean>());
        userDetailsListRecyclerView.setAdapter(mUserDetailsAdapter);

        Button loadDataButton = findViewById(R.id.get_users_button);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }

    private void loadData() {
        ((App) getApplication())
                .getGithubApi()
                .getData("Moonlight")
                .enqueue(mCallback);
    }

    private Callback<ResponseBean> mCallback = new Callback<ResponseBean>() {
        @Override
        public void onResponse(@NonNull Call<ResponseBean> call, @NonNull Response<ResponseBean> response) {
            ResponseBean responseBean = response.body();
            if (responseBean != null) {
                mUserDetailsAdapter.setData(responseBean.getItems());
            }
        }

        @Override
        public void onFailure(@NonNull Call<ResponseBean> call, @NonNull Throwable t) {
            Toast.makeText(MainActivity.this, "A network error occurred", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(t);
        }
    };
}
