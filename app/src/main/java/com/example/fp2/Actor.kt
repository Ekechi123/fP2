import java.io.Serializable

data class Actor(
    val name: String,
    val biography: String,
    val birthday: String,
    val place_of_birth: String,
    val profile_path: String
) : Serializable
