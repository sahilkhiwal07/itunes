package com.example.itunes.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunes.R
import com.example.itunes.ui.adapter.SongsAdapter
import com.example.itunes.ui.viewmodel.MyFactory
import com.example.itunes.ui.viewmodel.SongsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var songsAdapter: SongsAdapter
    private lateinit var viewModel: SongsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        initViewModel()
        searchArtist()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, MyFactory(application)).get(SongsViewModel::class.java)

        viewModel.getAllSongs().observe(this, {
            songsAdapter.submitList(it)
        })

        viewModel.makeApiCall("Camila Cabello")
    }

    private fun searchArtist() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.makeApiCall(newText.toString())
                return false
            }

        })
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