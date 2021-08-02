package com.example.f.Fragment

//import com.example.f.Api.ApiRepository
//import com.example.f.Factory.HomeVMFactory
//import com.example.f.ViewModel.HomeViewModel
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.f.Api.BookClient
import com.example.f.Model.Books
import com.example.f.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class HomeFragment : Fragment() {

//    private lateinit var viewModel: HomeViewModel
    private val TAG: String = "Test"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataApi()

//        val repository = ApiRepository()
//        val factory = HomeVMFactory(repository)
//        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
//        viewModel.getBooks()
//        viewModel.myResponse.observe(this, Observer { response ->
//            Log.d("Response", response. )
//        })
    }

//    override fun onStart() {
//        super.onStart()
//
//        getDataApi()
//    }

    private fun getDataApi() {
        BookClient.instance.getBooks()
                .enqueue(object : Callback<List<Books>> {
                    override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            showMsg(result.toString())
                        }
                    }

                    override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                        showMsg(t.toString())
                    }

                })
    }

    private fun showMsg(message: String) {
        Log.d(TAG, message)
    }


}