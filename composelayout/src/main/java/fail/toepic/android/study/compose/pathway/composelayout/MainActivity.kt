package fail.toepic.android.study.compose.pathway.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.pathway.composelayout.ui.theme.ComposePathwayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePathwayTheme {
                PhotographerCard2()
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
     * 이렇게 하면 모든 매개 변수의 이름을 지정하지 않고도 컴포저블에 수정자를 지정할 수 있습니다.*/
    Row(modifier
        /* 코드에서 팩토리 확장함수를 통하여 여러 수정자를 연속해서 지정하는 방법을 보여줍니다.
         * 순서가 중요함으로 수정자를 연결할때 주의하여야 합니다. 단일 인자로 연결 됨으로 순서가 최종 결과에 영향을 줍니다.
         * 만약 패딩보다 클릭이 먼저 온다면. 패팅이 클릭에 포함되고.
         * 패딩이 클릭보다 먼저오면 패딩이 클릭에 포함되지 않게 됩니다. */
//        .clickable { /* Ignoring onClick */ }
        .padding(16.dp)
        .clickable { /* Ignoring onClick */ }
    ) {
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
        PhotographerCard2()
    }
}

/* 명시적인 순서는 다양한 수정자가 상호 작용하는 방식을 추론하는데 도움이 됩니다.
 * view 시스템에서는 margin 이 '외부' 에 적용 되고, padding 이 '내부'에 적용 되었고 배경요소의 크기가 그에 따라 조정 되었습니다.
 * 수정자의 구조는 이 동작을 명시적이고 에측가능하게 만들고 원하는 동작을 정확하게 달성 할수 있도록 많은 제어를 제공합니다.
 *
 * 수정자를 사용하면 매우 유연한 방식으로 컴포저블을 수정 할 수 있습니다.
 * 예를 들어 외부 간격을 추가하고 컴포저블의 배경을 변경하고 모서리를 둥글게 하려면 아래와 같이 할 수있습니다.
 */
@Composable
fun PhotographerCard2(modifier: Modifier = Modifier) {
    Row(modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(onClick = { /* Ignoring onClick */ })
        .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )  {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}