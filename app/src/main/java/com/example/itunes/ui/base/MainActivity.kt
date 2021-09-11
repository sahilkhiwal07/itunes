package com.example.itunes.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunes.R
import com.example.itunes.ui.adapter.SongsAdapter
import com.example.itunes.ui.viewmodel.MyFactory
import com.example.itunes.ui.viewmodel.SongsViewModel
import com.example.itunes.utils.Constants.Companion.SEARCH_TIME_DELAY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var songsAdapter: SongsAdapter
    private lateinit var viewModel: SongsViewModel
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        initViewModel()
        searchQuery()

    }

    private fun searchQuery() {
        et_search.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                // debounce
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_TIME_DELAY)
                    if (viewModel.hasInternetConnection()) {
                        viewModel.makeApiCallForSong(it.toString().trim())
                    } else {
                        viewModel.getSongsByArtistName(it.toString().trim())
                    }
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, MyFactory(application)).get(SongsViewModel::class.java)

        //By default
        viewModel.getAllSongs().observe(this) {
            songsAdapter.submitList(it)
        }

        viewModel.filterSongsLiveData.observe(this) {
            songsAdapter.submitList(it)
        }

        viewModel.getTracks().observe(this) {
            songsAdapter.submitList(it)
        }

    }

    private fun setRecyclerView() {
        songsAdapter = SongsAdapter(this)
        songs_recyclerView.apply {
            adapter = songsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }
}