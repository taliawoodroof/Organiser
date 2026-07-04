package app.allulith.ui.impl.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.allulith.ui.impl.theme.OrganiserTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OrganiserLoader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularWavyProgressIndicator()
    }
}

@Preview
@Composable
private fun OrganiserLoaderPreview() {
    OrganiserTheme {
        OrganiserLoader()
    }
}
