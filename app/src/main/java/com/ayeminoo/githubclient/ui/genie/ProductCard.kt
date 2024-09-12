package com.ayeminoo.githubclient.ui.genie

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ayeminoo.domain.genie.Product

@Composable
fun ProductCard(
    product: Product,
    onInquiry: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
        ) {
            if (product.image != null) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = product.title,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(text = product.description, style = MaterialTheme.typography.labelSmall)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Category: ${product.category}",
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Total Unit: ${product.availableUnit}",
                style = MaterialTheme.typography.labelSmall
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Outlined.Star, contentDescription = "")
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onInquiry(product) },
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .height(30.dp)
                    .width(100.dp)
            ) {
                Text(
                    text = "Inquiry",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(
    device = "id:pixel_3",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.NONE, showSystemUi = true, showBackground = true
)
@Composable
private fun ProductCardPreview() {
    ProductCard(
        product = Product(
            title = "Iphone 16",
            description = "Apple's Product",
            category = "Smart Phones",
            estimateCash = 60000.0,
            priceUnit = "Baht",
            availableUnit = 4171,
            tag = listOf("apple", "phones"),
            favorite = true,
            image = "",
            id = 1
        ),
        onInquiry = {},
        modifier = Modifier.fillMaxWidth()
    )
}