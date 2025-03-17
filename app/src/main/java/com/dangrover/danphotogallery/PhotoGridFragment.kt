package com.dangrover.danphotogallery

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController

import com.dangrover.danphotogallery.databinding.FragmentGridBinding

/**
 * Fragment showing the grid of all the photos
 **/

class PhotoGridFragment : Fragment() {

    private var _binding: FragmentGridBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestoryView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = PhotoAdapter(requireContext(), this)
        binding.recycler.adapter = adapter
    }

    class PhotoAdapter(
        private val context: Context,
        private val parentFragment: Fragment

   //     private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<PhotoViewHolder>() {

        /*
        interface OnItemClickListener {
            fun onItemClick(photoId: Int)
        }*/

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
            return PhotoViewHolder(view)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
            // Get photo with this number from drawables and set it in the image view
            val photoId = position + 1
            val drawable: Drawable? = ContextCompat.getDrawable(context,
                context.resources.getIdentifier("photo$photoId", "drawable", context.packageName))
            holder.photoImageView.setImageDrawable(drawable)

            holder.cardView.setOnClickListener {
                // Create a PhotoFragment passing  our number and show it.
                val photoFragment = PhotoFragment.newInstance(photoId)
                findNavController(parentFragment).navigate(R.id.action_GridFragment_to_photoFragment,
                    photoFragment.arguments)
            }
        }

        override fun getItemCount(): Int = 17
    }


    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: ImageView = itemView.findViewById(R.id.photoImageView)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}