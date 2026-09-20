package io.novaos.ui.motion

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.novaos.ui.motion.NovaMotionUtils.novaTransform
import kotlinx.coroutines.launch

@Composable
fun NovaMotionDemo() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val appScale = remember { Animatable(1f) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("NovaOS Motion Demo", color = Color.White)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Demo 1: Interruptible Spring & Haptics
        Box(
            modifier = Modifier
                .size(100.dp)
                .novaTransform(scale = appScale.value)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF007FAC))
                .clickable {
                    scope.launch {
                        NovaHaptics.performHeavyClick(context)
                        // Animate scale with high-elasticity spring
                        appScale.animateTo(
                            targetValue = 1.5f,
                            animationSpec = NovaPhysicsSpecs.AppLaunchSpring
                        )
                        appScale.animateTo(
                            targetValue = 1f,
                            animationSpec = NovaPhysicsSpecs.AppLaunchSpring
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text("Launch", color = Color.White)
        }
        
        Spacer(modifier = Modifier.height(64.dp))
        
        // Demo 2: Logarithmic Overscroll
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.DarkGray.copy(alpha = 0.3f))
                .novaOverscroll()
        ) {
            items(20) { index ->
                Text(
                    "Overscroll Item $index",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
