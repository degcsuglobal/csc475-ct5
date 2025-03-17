package com.dangrover.danphotogallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * A fragment that displays a single photo.
 * All photos are stored in the drawables folder and referenced by number only.
 **/

class PhotoFragment : Fragment() {
    private var photoNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoNumber = it.getInt("photoNumber")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var myView : View =  inflater.inflate(R.layout.fragment_photo, container, false)

        // set the image in our imageview based on photoNumber
        var image = resources.getIdentifier("photo$photoNumber", "drawable", context?.packageName)
        myView.findViewById<ImageView>(R.id.imageView).setImageResource(image)

        return myView;
    }

    companion object {
        @JvmStatic
        fun newInstance(photoNumber: Int) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putInt("photoNumber", photoNumber)
                }
            }
    }
}