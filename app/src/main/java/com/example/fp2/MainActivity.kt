import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fp2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Use lifecycleScope correctly (no need to define it manually)
        lifecycleScope.launch {
            try {
                // Fetch data in the background thread
                val actors = withContext(Dispatchers.IO) {
                    ActorApiService.fetchActors() // Assuming this is a suspend function
                }

                // Create adapter and set it to RecyclerView
                val adapter = ActorAdapter(actors) { actor ->
                    // Start the details activity when an actor is clicked
                    val intent = Intent(this@MainActivity, ActorDetailsActivity::class.java)
                    intent.putExtra("actor", actor)  // Pass the actor object to the details activity
                    startActivity(intent)
                }

                // Set the RecyclerView layout and adapter
                binding.rvMovies.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rvMovies.adapter = adapter
            } catch (e: Exception) {
                // Show error message if something goes wrong
                Toast.makeText(this@MainActivity, "Error loading actors", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
