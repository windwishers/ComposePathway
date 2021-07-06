package fail.toepic.android.study.compose.pathway.composepathway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fail.toepic.android.study.compose.pathway.composepathway.ui.theme.ComposePathwayTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MyScreenContent()
            }
        }
    }
}

@Composable
/* @Composable ()->Unit 과 같이 함수 선언 앞에도 @Composable 이 붙는다는 점에 유의 하자. */
fun MyApp(content : @Composable ()-> Unit){
    ComposePathwayTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    /* Greeting 함수를 위해 다른 배경생을 지정하려면 내용을 포함하는 Surrface를 선언해야합니다.
     * Surface 내부에 중첩 된 요소는 Surface의 배경색 위에 그려집니다.  */
    Surface(color = Color.Yellow) {
        /* Modifier-수정자는 어떻게 배치 되고, 표시되며, 부모레이아웃 안에서 어떻게 동작 하는지에 대하여 말해줍니다.
        * 변수에 할당하고 재활용 될 수 있으며, 몇개의 수정자를 채이닝 하거나, 팩토리 확장함수 등을 이용하여 연속사용 할 수 있습니다? (맞나?_
        * padding 은 요소 주변의 주어진 수치의 공간을 추가합니다.  */
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }

}

/* 인자가 없는 컴포즈 함수는  @Preview 어노테이션으로 미리보기가 가능합니다.
*  name 인자를 이용하여 프리뷰 이름을 지정할 수 있습니다. */
@Preview(showBackground = true,name="Text Preview")
@Composable
fun DefaultPreview() {
    MyApp{
        MyScreenContent()
    }
}

@Composable
fun MyScreenContent(){
    /* 컴포즈 함수를 호출하면 UI 계층구조에 요소가 추가 됩니다. 여러부분에서 동일한 함수를 호출 하여,
     * 새 요소를 추가 할수 있습니다. */
    Column {
        Greeting("Android")
        /* Divider  는 수평 구분선을 그려줍니다.   */
        Divider(color = Color.Black)
        Greeting("there")
    }
}