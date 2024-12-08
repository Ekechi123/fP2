import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

object ActorApiService {
    private val client = OkHttpClient()

    // TMDb API key
    private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private const val BASE_URL = "https://api.themoviedb.org/3/person/popular?api_key=$API_KEY"

    // Make network request to fetch popular actors
    fun fetchActors(): List<Actor> {
        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        return try {
            // Execute the request and get the response
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }

                // Parse the JSON response
                val jsonResponse = response.body?.string()
                val actorsList = mutableListOf<Actor>()

                if (jsonResponse != null) {
                    val jsonObject = JSONObject(jsonResponse)
                    val results = jsonObject.getJSONArray("results")

                    for (i in 0 until results.length()) {
                        val actorJson = results.getJSONObject(i)
                        val name = actorJson.getString("name")
                        val biography = actorJson.getString("biography")
                        val profilePath = actorJson.getString("profile_path")
                        val birthday = actorJson.getString("birthday")
                        val placeOfBirth = actorJson.getString("place_of_birth")

                        // Create Actor object and add to the list
                        val actor = Actor(name, biography, profilePath, birthday, placeOfBirth)
                        actorsList.add(actor)
                    }
                }
                actorsList
            }
        } catch (e: Exception) {
            // Log the error and return an empty list if something goes wrong
            e.printStackTrace()
            emptyList<Actor>()
        }
    }
}

