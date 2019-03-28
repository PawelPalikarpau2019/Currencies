package my.projects.currencies.service;

import my.projects.currencies.model.Currencies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrenciesService {
    @GET("api/latest")
    Call<Currencies> loadCurrencies(@Query("access_key") String accessKey);

    @GET("api/latest")
    Call<Currencies> loadCurrencies(@Query("access_key") String accessKey, @Query("symbols") String symbols);
}
