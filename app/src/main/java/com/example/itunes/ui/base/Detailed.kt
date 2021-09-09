package com.example.itunes.ui.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.itunes.R
import com.example.itunes.data.model.Result
import kotlinx.android.synthetic.main.activity_detailed.*

class Detailed : AppCompatActivity() {

    private lateinit var result: Result

    companion object {
        const val SONGS_KEY = "SONGS_KEY"
        fun sendData(context: Context, result: Result): Intent {
            val intent = Intent(context, Detailed::class.java)
            intent.putExtra(SONGS_KEY, result)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        result = intent.getParcelableExtra<Result>(SONGS_KEY)!!

        setDataToDetailedActivity()

    }

    private fun setDataToDetailedActivity() {
        d_trackName.text = result.trackName
        d_artistName.text = result.artistName
        d_collectionName.text = result.collectionName
        d_trackNumber.text = result.trackNumber.toString()
        d_releaseDate.text = result.releaseDate

        Glide.with(applicationContext).load(result.artworkUrl100).into(d_image)
    }
}