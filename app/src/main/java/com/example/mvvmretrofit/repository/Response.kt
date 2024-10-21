package com.example.mvvmretrofit.repository

import com.example.mvvmretrofit.model.DemoList

//sealed class Response() {
//    class Loading:Response()
//    class Success (val demoList: DemoList): Response()
//    class Error (val errorMessage:String): Response()
//}
/**
 * this method imporve version
 */
//sealed class Response(val data:DemoList?=null,val errorMessage:String?=null) {
//    class Loading:Response()
//    class Success ( demoList: DemoList): Response(data=demoList)
//    class Error ( errorMessages:String): Response(errorMessage=errorMessages)
//}

/**
 * in the above method are used for one api request but not for the other api requests
 * so we need to use the generic
 */
sealed class Response<T>(val data:T?=null,val errorMessage:String?=null) {
    class Loading<T>:Response<T>()
    class Success <T>( data: T?=null): Response<T>(data=data)
    class Error<T> ( errorMessages:String): Response<T>(errorMessage=errorMessages)
}