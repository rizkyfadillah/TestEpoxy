package com.rizkyfadillah.testepoxy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    sealed class State {

        class ShowData(
            val data: MutableList<Item>
        ) : State()

    }

    private fun pushState(state: State) {
        _state.value = state
    }

    fun onViewCreated() {
        val list = mutableListOf<Item>()

        val data1 = (1..15).map {
            Model(it, "Text this $it", "Text that $it")
        }
        val data2 = (16..30).map {
            Model1(it, "Text this $it", "Text that $it")
        }
        list.addAll(data1)
        list.addAll(data2)

        list.add(LoadMoreModel())

        pushState(State.ShowData(list))
    }

    fun loadMore(data: List<Item>) {
        val list = mutableListOf<Item>()

        list.addAll(data.filterNot { it is LoadMoreModel })

        val toBeAdded = (31..40).map {
            Model(it, "Text this $it", "Text that $it")
        }

        list.addAll(toBeAdded)

        pushState(State.ShowData(list))
    }

}
