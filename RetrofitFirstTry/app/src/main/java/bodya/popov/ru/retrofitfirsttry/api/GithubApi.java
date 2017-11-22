package bodya.popov.ru.retrofitfirsttry.api;

import java.util.List;

import bodya.popov.ru.retrofitfirsttry.bean.ResponseBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api к Github
 * Пример запроса: https://api.github.com/search/users?q=moonightcs
 *
 * @author Popov Bogdan
 */

public interface GithubApi {


    @GET("/search/users")
    Call<ResponseBean> getData(@Query("q") String query);

}
