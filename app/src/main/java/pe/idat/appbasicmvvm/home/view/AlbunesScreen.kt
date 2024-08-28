package pe.idat.appbasicmvvm.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pe.idat.appbasicmvvm.home.ViewModel.AlbunesViewModel
import pe.idat.appbasicmvvm.home.ViewModel.HomeViewModel
import pe.idat.appbasicmvvm.home.data.network.response.AlbunesResponse
import pe.idat.appbasicmvvm.home.data.network.response.HistoriaDiariaResponse
import coil.compose.AsyncImage
@Composable
fun AlbumesScreen(homeViewModel: HomeViewModel){
    val albumes by homeViewModel.albunesResponse.observeAsState(emptyList())

    LazyRow{
        items(albumes){
                albumes -> albumesItem(albumes = albumes)
        }
    }
}

@Composable
fun albumesItem(albumes: AlbunesResponse) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .width(250.dp)
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ))
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "AlbumId: ${albumes.albumId}", color = Color.Gray)
                Text(text = "Id: ${albumes.id}", fontWeight = FontWeight.Bold)
                Text(text = "Title: ${albumes.title}", color = Color.Gray)
                Text(text = "Url: ${albumes.url}", color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = albumes.thumbnailUrl,
                    contentDescription = "Thumbnail Url",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                )

            }
        }
    }
}