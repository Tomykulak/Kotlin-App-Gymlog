package xokruhli.finalproject.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xokruhli.finalproject.R
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink70
import xokruhli.finalproject.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseRow(exercise: Exercise,
                onRowClick: () -> Unit = {}
) {
    Surface(shape = RoundedCornerShape(20),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp),
        color = Pink70,
        onClick = onRowClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.10f)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_barbell),
                    contentDescription = "barbell_icon",
                    tint = Color.Unspecified)
            }
            Column(modifier = Modifier
                .weight(0.50f)
                .padding(start = 10.dp)) {
                //Text
                Text(text = exercise.name!!,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}