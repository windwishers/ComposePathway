package fail.toepic.android.study.compose.pathway.composepathway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
//        Surface(color = MaterialTheme.colors.background) {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    /* Greeting 함수를 위해 다른 배경생을 지정하려면 내용을 포함하는 Surface 를 선언해야합니다.
     * Surface 내부에 중첩 된 요소는 Surface 의 배경색 위에 그려집니다.  */
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
fun MyScreenContent(names: List<String> = listOf("Android", "there")){

    val countState = remember { mutableStateOf(0) }

    /*  fillMaxHeight 수정자는 전체 높이를 차지하게 합니다. fillMaxSize() 나 fillMaxWidth() 도 사용이 가능합니다.  */
    Column(modifier = Modifier.fillMaxHeight()) {
        /* 특정 weight  로 화면을 차지 하도록 하기 위해 weight Modifier 를 사용 할 수 있습니다.
        * 나머지에는 유연하지 않기 때문에-weight 가 없기때문에 Column 은 나머지 Column 이 영역을 차지한후
        * 나머지 영역 전체를 점유 합니다.
        *   */
        Column(modifier = Modifier.weight(1f)) {
            NameList()
        }
        Counter(
            count = countState.value,
            updateCount = { newCount ->
                countState.value = newCount
            }
        )
    }
}

@Composable
fun Counter(count : Int, updateCount : (Int)->Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            /*
            * Compose에서 Kotlin을 활용하는 또 다른 예로서
            * Button사용자가 if...else문장을 사용하여 탭한 횟수에 따라의 배경색을 변경할 수 있습니다 .
             */
            backgroundColor = if(count>5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String> = List(1000) { "Hello Android #$it" }, modifier: Modifier = Modifier) {
    // Column 은 기본적으로 스크롤 할 수 없습니다.
//    Column(modifier = modifier) {
    /*
     * 참고 :LazyColumn 같은 아이를 재활용하지 않습니다 RecyclerView.
     * 스크롤 할 때 새로운 Composables를 방출하고
     * Composables를 방출하는 것은 Android 인스턴스화에 비해 상대적으로 저렴하기 때문에
     * 여전히 성능이 Views 보다 좋습니다.
     */
    LazyColumn(modifier = modifier) {
        items(items = names){ name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}