package com.example.m01guided.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m01guided.R
import com.example.m01guided.model.ForexService
import com.example.m01guided.model.Rates
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicBoolean

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MainViewModel
    val object1:Observable<Int> = Observable.create{emitter ->
        for (i in 0 until 10) {
            Thread.sleep(350)
            emitter.onNext(i)
        }
        emitter.onComplete()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

        list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }

            .subscribeBy(  // named arguments for lambda Subscribers
                onNext = { println(it) },
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
        val obs1 = Observable.just("1","2","3")

        val obs2 = Observable.fromIterable(list)

        val obs3 = Observable.create<Int> { emitter ->
            for (i in 0..20) {
              GlobalScope.launch {
                  emitter.onNext(i)
              }
              }
        }
        var atomic = AtomicBoolean(false)


        val retrofy = Retrofit.Builder().baseUrl("https://api.exchangerate-api.com/v4/latest/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofy.create(ForexService::class.java)

    /*    val newDisposable = service.getRates("USD").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(3)
            .subscribe( { rates:Rates ->
                tv_main.text = rates.rates.toString()
            },
                {e-> Log.i(e.toString(),"erro") }
            )*/


        btn_main.setOnClickListener {
            val newDisposable = service.getRates(et_one.text.toString()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribeBy(  // named arguments for lambda Subscribers
                    onSuccess = { rates:Rates ->
                        tv_main.text = rates.rates.get("EUR").toString()
                        Log.i("success",rates.toString())},
                    onError =  { it.printStackTrace() }
                )

            compositeDisposable.add(newDisposable)
           /* if (atomic.get() !=true) {
                atomic.set(true)
                val disposable =
                    object1.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(  // named arguments for lambda Subscribers
                            {
                                println(it)
                                tv_main.text = it.toString()
                            },
                            {atomic.set(false) },
                            {
                                atomic.set(false)
                                println("Done!")
                                tv_main.text = "done"
                            }
                        )


                compositeDisposable.add(disposable)
            }*/
        }

                val etonefield = et_one.textChanges()
        val ettwofield = et_two.textChanges()
   val etdisp = etonefield.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .retry(3)
    .subscribeBy(  // named arguments for lambda Subscribers
        onComplete = {Log.i(SystemClock.currentThreadTimeMillis().toString(),"now")},
        onError =  { throwable ->  throwable.printStackTrace() }
    )
        compositeDisposable.add(etdisp)




       /* val obsFieldsCombined = Observable.combineLatest(etonefield,ettwofield) {first,last ->
            "$first $last"
        }
*/
    }





    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}
