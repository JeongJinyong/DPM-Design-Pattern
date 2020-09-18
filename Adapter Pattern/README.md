# Adapter Pattern

- 어댑터 패턴(Adapter pattern)은 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 패턴으로, 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해준다. - 위키백과
***
나 같은 멍청이는 디자인 패턴은 공부할 때마다 도대체 무슨 설명인지 이해하기가 힘들다.  
그래서 직접 코드를 작성해보지 않으면 이해할 수 없다.
***
![어댑터 패턴](https://t1.daumcdn.net/cfile/tistory/24231F4C575EACA210)  
어댑터 패턴을 검색하면 가장 흔히 나오는 이미지이다.  
사실 나는 이 이미지를 봐도 이해가 가지 않았다.  
그래서 공부를 해보았다.
***
## 삼성이어폰을 아이폰에 연결하기.
- Interface인 [`EarPhone`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/EarPhone.kt)과 [`AppleLightningPin`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/AppleLightningPin.kt)을 만들어준다.
- 어댑터 패턴이기때문에 Class[`AppleAdapter`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/AppleAdapter.kt)을 만들어준다.
- 삼성이어폰 Class [`SamsungEarPhone`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/SamsungEarPhone.kt)과 아이폰 Class [`Iphone`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/Iphone.kt)를 만들어준다.
- 이로서 준비는 끝났다 나는 이어폰의 4극단자와 아이폰을 연결해주는 [`AppleAdapter`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/Adapter%20Pattern/src/AppleAdapter.kt)을 만든사람이다.  
이제 애플에 취직하는 날만 기다리겠다.
```kotlin
class SamsungEarPhone:EarPhone {
    override fun maleFourPole() {
        println("Combination Device")
    }
}
```
> 삼성이어폰은 'EarPhone'을 구현하도록 만들고
```kotlin
class AppleAdapter(private val earPhone: EarPhone):AppleLightningPin{
    override fun pinEight() {
        earPhone.maleFourPole()
    }
}
```
> 애플어댑터는 `AppleLightningPin`을 구현하도록 만들고 `EarPhone`을 생성자로 받아주면 우린 애플에 취직할수있다.
```kotlin
class Iphone {

    fun setEarPhone(appleLightningPin: AppleLightningPin){
        appleLightningPin.pinEight()
        println("EarPhone Combination")
    }

}
```
> `Iphone`에서 `setEarPhone`을 통하여 어댑터와 연결된 이어폰을 아이폰과 연결해주는 작업을 한다.
***
![젠더](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F264C044F587778B00D)  
- 그림과 같이 어댑터를 이용하여 4극단자 이어폰과 아이폰을 연결하는 작업을 해당 내용을 통하여 배워보았다.
- 어댑터 패턴은 기존시스템에 제3의 기술이 필요할때 어댑터를 만들어서 주로 사용하니  
흔히 돼지코 또는 충전어댑터를 생각하면 이해하기 쉬웠던 것 같다.
