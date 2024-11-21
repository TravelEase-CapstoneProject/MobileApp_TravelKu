package org.capstone_project.travelku.ui.presentation.screen.main.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.capstone_project.travelku.ui.icon.Description
import org.capstone_project.travelku.ui.icon.History
import org.capstone_project.travelku.ui.presentation.components.ButtonUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold {
        Box(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = "https://www.w3schools.com/w3images/avatar2.png",
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(shape = CircleShape)
                        .size(100.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    "Nama User",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    "email@example.com",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Edit Profile")
                }
                Spacer(modifier = Modifier.size(16.dp))
                SettingProfileList()
            }
        }
    }
}

@Composable
fun SettingProfileList(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SettingProfile(
                title = "Detail Profile",
                onClick = { /* Handle Detail Profile click */ },
                icon = { Icon(Icons.Default.Person, contentDescription = "Detail Profile") }
            )
            SettingProfile(
                title = "Histori Pemesanan",
                onClick = { /* Handle History click */ },
                icon = { Icon(History, contentDescription = "History") }
            )
            SettingProfile(
                title = "FAQ",
                onClick = { /* Handle History click */ },
                icon = { Icon(Icons.Default.Search, contentDescription = "FAQ") }
            )
            SettingProfile(
                title = "Terms and Condition",
                onClick = { /* Handle Terms and Condition click */ },
                icon = { Icon(Description, contentDescription = "Terms and Condition") }
            )
            SettingProfile(
                title = "Logout",
                onClick = { /* Handle Logout click */ },
                icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout") }
            )
        }
    }
}

@Composable
fun SettingProfile(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.padding(start = 16.dp))
        icon()
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForward,
            contentDescription = "Arrow Forward",
        )
        Spacer(modifier = Modifier.padding(end = 16.dp))
    }
}

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
    }
}