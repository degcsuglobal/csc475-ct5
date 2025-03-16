package com.dangrover.danphotogallery

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import android.view.*
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dangrover.danphotogallery.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 3)

        val adapter = PhotoAdapter(requireContext())

        binding.recycler.adapter = adapter

    }


    // the photos are all stored in the assets folder, there are 10

    class PhotoAdapter(
        private val context: Context,
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
            // generate photo ID and drawable from path, they are all numbered numerically as e.g. "photo0.png" in drawable and stored as pngs.

            val photoId = position + 1
            val drawableName = "photo$photoId"
            val drawableResId = context.resources.getIdentifier(drawableName, "drawable", context.packageName)

            Log.d("PhotoAdapter", "Photo ID: $photoId")



            // make the drawable
            val drawable: Drawable? = ContextCompat.getDrawable(context, drawableResId)
            holder.photoImageView.setImageDrawable(drawable)

         /*   holder.cardView.setOnClickListener {
                //onItemClickListener.onItemClick(photoId)
            }*/
        }

        override fun getItemCount(): Int = 6
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