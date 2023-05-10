package edu.fullerton.fz.finalproject411
//Favorites.kt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */


class Favorites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView = view.findViewById<VideoView>(R.id.video_view)
        val imageView = view.findViewById<ImageView>(R.id.image_view)

        // Set your video URI. For example:
        val videoUri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.btc2)
        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(context)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        videoView.setOnPreparedListener { mp ->
            mp.start()
        }

        // Set your image. For example:
        imageView.setImageResource(R.drawable.eth_logo)
    }

}
