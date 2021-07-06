package fail.toepic.android.study.compose.pathway.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.pathway.composelayout.ui.theme.ComposePathwayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePathwayTheme {
                PhotographerCard()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    /* 대부분의 컴포저블은 선택적인 수정자 매개변수를 허용하여 호출자가 수정을 할 수 있게 만듬으로서 좀더 유연하게 만들어 집니다.
     * 자신만의 컴포저블을 생성하는 경우 수정자를 매개 변수로 사용하는 것을 고려하고 기본값을 Modifier-즉 아무것도
     * 하지 않은 빈 수정자)를 사용합니다.
     * Convention-규칙에 따라 Modifier-수정자는 함수의 첫번째 선택적 매개 변수로 지정됩니다.
     * 이렇게 하면 모든 매개 변수의 이름을 지정하지 않고도 컴포저블에 수정자를 지정할 수 있습니다.
     *
     *
     */
    Row(modifier) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }
        Column(
            modifier = Modifier
//                    컬럼에 8dp 패딩을 추가
                .padding(start = 8.dp)
/* 수평가운데 로 정렬.
 * 일부 레이아웃은 자신의 레이아웃에과 특성에만 적용되는 Modifier-수정자를 제공합니다.
 * 예를 들어 Row 는 (RowScope 를 받는 컨텐츠를 통해서) weight 나 align 등을 제공합니다.
 * 이러한 범위 지정은 형식 안정성을 제공함으로서 다른 레이아웃에서 의미없는 수정자를 실수로 잘 못 사용 할 수 없게 만듭니다.   */
                .align(Alignment.CenterVertically)
        )  {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    ComposePathwayTheme {
        PhotographerCard()
    }
}