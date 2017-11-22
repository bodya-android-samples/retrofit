package bodya.popov.ru.retrofitfirsttry;

import android.app.Application;

import bodya.popov.ru.retrofitfirsttry.api.GithubApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Popov Bogdan
 */

public class App extends Application {

    private GithubApi mGithubApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getHttpClientWithInterceptor())
                .build();

        mGithubApi = retrofit.create(GithubApi.class);
    }

    public GithubApi getGithubApi() {
        return mGithubApi;
    }

    private OkHttpClient getHttpClientWithInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }
}
