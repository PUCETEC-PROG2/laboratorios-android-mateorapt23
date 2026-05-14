package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ec.edu.ec.edu.puce.githubclient.ui.components.RepoItem

@Preview(showBackground = true)
@Composable
fun RepoList(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en desarrollo movil",
            avatarUrl = "https://icon-icons.com/download-file?file=https%3A%2F%2Fimages.icon-icons.com%2F2415%2FPNG%2F256%2Fandroid_original_wordmark_logo_icon_146654.png&id=146654&pack_or_individual=pack",
            language = "Kotlin"
        )

        RepoItem(
            name = "Repositorio de Django",
            description = "Repositorio creado en desarrollo movil",
            avatarUrl = "https://e7.pngegg.com/pngimages/159/366/png-clipart-django-python-computer-icons-logo-python-text-label-thumbnail.png",
            language = "Kotlin"
        )

        RepoItem(
            name = "Repositorio de React",
            description = "Repositorio creado en desarrollo movil",
            avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSR-IEdVMz6l9i4mpwSqUiyU25QW0PXU_h1Ig&s",
            language = "Kotlin"
        )

        RepoItem(
            name = "Repositorio de Colibri",
            description = "Repositorio creado en desarrollo movil",
            avatarUrl = "https://img.freepik.com/vector-premium/logotipo-hummingbird_215413-53.jpg",
            language = "Kotlin"
        )

    }
}