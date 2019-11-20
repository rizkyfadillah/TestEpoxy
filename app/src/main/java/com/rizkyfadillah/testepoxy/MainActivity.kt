package com.rizkyfadillah.testepoxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.state.observe(this, Observer {
            when (it) {
                is MainViewModel.State.ShowData -> showData(it.data)
            }
        })

        viewModel.onViewCreated()
    }

    private fun showData(data: List<Item>) {
        recyclerView.withModels {
            data.mapIndexed { _, item ->
                when (item) {
                    is Model -> customView {
                        id(item.id)
                        model(item)
                    }
                    is Model1 -> customViewHolder {
                        id(item.id)
                        model1(item)
                    }
                    is LoadMoreModel -> loaderView {
                        id("loader")
                        onBind { _, _, _ ->
                            viewModel.loadMore(data)
                        }
                    }
                }
            }
        }
    }

}

data class Model(val id: Int, val text1: String, val text2: String) : Item

data class Model1(val id: Int, val text1: String, val text2: String) : Item

class LoadMoreModel : Item

interface Item
