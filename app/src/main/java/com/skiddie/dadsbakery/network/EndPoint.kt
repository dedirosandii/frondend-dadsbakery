package com.skiddie.dadsbakery.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface EndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.login.LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name: String,
                 @Field("email") email: String,
                 @Field("password") password: String,
                 @Field("password_confirmation") password_confirmation: String,
                 @Field("address") address: String,
                 @Field("city") city: String,
                 @Field("houseNumber") houseNumber: String,
                 @Field("phoneNumber") phoneNumber: String): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.login.LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage: MultipartBody.Part): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<Any>>

    @GET("user")
    fun profile(): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.ProfileResponse>>

    @GET("food")
    fun home(): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.home.HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("food_id") food_id: String,
                 @Field("user_id") user_id: String,
                 @Field("quantity") quantity: String,
                 @Field("total") total: String,
                 @Field("status") status: String): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.checkout.CheckoutResponse>>

    @GET("transaction")
    fun transaction(): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<com.skiddie.dadsbakery.model.reponse.transaction.TransactionResponse>>

    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
                          @Field("status") status: String): Observable<com.skiddie.dadsbakery.model.reponse.Wrapper<Any>>
}