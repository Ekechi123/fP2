import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fp2.databinding.ActivityDetailsBinding // Import the generated ViewBinding class

class ActorDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the Actor object from the Intent (assuming it's Serializable)
        val actor = intent.getSerializableExtra("actor") as Actor

        // Set the actor details on the views using ViewBinding
        binding.tvName.text = actor.name
        binding.tvBiography.text = actor.biography
        binding.tvBirthday.text = "Birthday: ${actor.birthday}"
        binding.tvPlaceOfBirth.text = "Place of Birth: ${actor.place_of_birth}"

        // Load actor profile picture using Glide
        val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
        Glide.with(this).load(imageUrl).into(binding.ivProfile)
    }
}
