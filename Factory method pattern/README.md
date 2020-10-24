# Factory method pattern

- 팩토리 메서드 패턴(Factory method pattern)은 객체지향 디자인 패턴이다. Factory method는 부모(상위) 클래스에 알려지지 않은 구체 클래스를 생성하는 패턴이며. 자식(하위) 클래스가 어떤 객체를 생성할지를 결정하도록 하는 패턴이기도 하다. 부모(상위) 클래스 코드에 구체 클래스 이름을 감추기 위한 방법으로도 사용한다.

Factory Method라는 패턴 이름이 적절하지 못한데, 이름으로 인해 객체를 생성하는 메소드를 Factory method라 오해하는 개발자가 많이 있다(Allen Holub의 말을 인용.) 이런 생성 메소드가 모두 Factory method 패턴을 사용하는 것은 아니다. Template Method의 생성 패턴 버전으로 볼 수 있는데 Template Method를 알지 못한다면 그 패턴을 먼저 이해하는 것이 Factory Method를 이해하기 수월할 것이다.

Factory Method가 중첩되기 시작하면 굉장히 복잡해 질 수 있다. 또한 상속을 사용하지만 부모(상위) 클래스를 전혀 확장하지 않는다. 따라서 이 패턴은 extends 관계를 잘못 이용한 것으로 볼 수 있다. extends 관계를 남발하게 되면 프로그램의 엔트로피가 높아질 수 있으므로 Factory Method 패턴의 사용을 주의해야 한다. - 위키백과
***
나 같은 멍청이는 디자인 패턴은 공부할 때마다 도대체 무슨 설명인지 이해하기가 힘들다.  
그래서 직접 코드를 작성해보지 않으면 이해할 수 없다.
***
![팩토리 메서드 패턴](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/FactoryMethod.svg/2560px-FactoryMethod.svg.png)
팩토리 메서드 패턴을 검색하면 가장 흔히 나오는 이미지이다.  
사실 나는 이 이미지를 봐도 이해가 가지 않았다.  
그래서 공부를 해보았다.
***
## 개발자 최종빌드 치킨집 주문서 만들기
- Intreface인 [`Chicken`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Factory%20method%20pattern/src/Chicken.kt)를 만들어 줍니다.
- 치킨은 자고로 양념이 중요하기때문에 [`Seasoning`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Factory%20method%20pattern/src/Seasoning.kt)을 만들어 줍니다.
- 우선 시범적으로 Seasoning한 치킨 3가지를 만들어볼께요 [`FriedChicken`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Factory%20method%20pattern/src/FriedChicken.kt),[`MarinatedChicken`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Factory%20method%20pattern/src/MarinatedChicken.kt),[`SoysauceAndGarlicChicken`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Factory%20method%20pattern/src/SoysauceAndGarlicChicken.kt) 을 만들어줍시다.
- 이제 준비는 끝났습니다. 우리는 치킨집 주문시스템을 만들었으니 치킨튀기는건 지금까지 번돈으로 사오도록합시다. 남들이 좋은거 만들었을껍니다.
```Kotlin
interface Chicken {
    val price: Int
}
```
> 우리는 돈벌려고 치킨집을 차린거니까 가장 중요한 가격을 정합니다.
```Kotlin
enum class Seasoning {
    Fried, Marinated, SoySauceAndGarlic
}
```
> 양념은 후라이드, 양념, 간장마늘로 정해줍니다. 원하는게 있다면 추가해주세요 ㅋㅋㅋ
```Kotlin
class FriedChicken : Chicken {
    override val price: Int = 15000
}
class MarinatedChicken :Chicken{
    override val price: Int = 18000
}
class SoysauceAndGarlicChicken :Chicken{
    override val price: Int = 19000
}
```
> 요즘 치킨값이 너무 비쌉니다 ... ㅠ 다른곳은 19000원이길래 우린 싸게 많이팝시다 ㅠ
```Kotlin
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(order(Seasoning.Fried))
            println(order(Seasoning.Marinated))
            println(order(Seasoning.SoySauceAndGarlic))
        }

        private fun order(seasoning: Seasoning) = when (seasoning) {
            Seasoning.Fried -> FriedChicken().price
            Seasoning.Marinated -> MarinatedChicken().price
            Seasoning.SoySauceAndGarlic -> SoysauceAndGarlicChicken().price
        }
    }
}
```
> order 메서드를 만들어서 손님들이 선택한 양념에따라 가격을 뿌려주도록 만들었습니다.
***
![치킨](https://thumb.mt.co.kr/06/2015/03/2015032614251581375_2.jpg)
- 위 예제를 통하여 위키백과를 다시 살펴보자면
- Factory method는 부모(상위) 클래스에 알려지지 않은 구체 클래스를 생성하는 패턴이며. 자식(하위) 클래스가 어떤 객체를 생성할지를 결정하도록 하는 패턴이기도 하다. 치킨을 통하여 다른 여러 양념에따른 치킨을 생성하였다 즉 양념치킨(자식클래스)이 어떤 객체로 생성될지 결정되었다.
- 위 사항을 미루어 보았을때 확장성이 용이하며 동일한 형태로 프로그래밍도 가능하고 전체적인 설게에도 좋지만 위키백과에 나온것처럼 이름때문에 오해도 많이받으며 비슷한 Template Method도 많이 비슷하며 중첩되기 시작하면 복잡하다.
