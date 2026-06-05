package ec.edu.puce.githubclient.models

data class UpdateRepositoryPayload(
    val name: String,
    val description: String?
)