import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fp2.databinding.MovieItemBinding

class ActorAdapter(private val actors: List<Actor>, private val clickListener: (Actor) -> Unit) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.binding.tvTitle.text = actor.name

        // Load actor's profile image with Glide
        val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
        Glide.with(holder.binding.root.context).load(imageUrl).into(holder.binding.ivPoster)

        // Set click listener to navigate to details page
        holder.binding.root.setOnClickListener {
            clickListener(actor)
        }
    }

    override fun getItemCount() = actors.size
}
