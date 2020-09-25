# Observer Pattern

- 옵서버 패턴(observer pattern)은 객체의 상태 변화를 관찰하는 관찰자들, 즉 옵저버들의 목록을 객체에 등록하여 상태 변화가 있을 때마다 메서드 등을 통해 객체가 직접 목록의 각 옵저버에게 통지하도록 하는 디자인 패턴이다. 주로 분산 이벤트 핸들링 시스템을 구현하는 데 사용된다. 발행/구독 모델로 알려져 있기도 하다. - 위키백과
***
나 같은 멍청이는 디자인 패턴은 공부할 때마다 도대체 무슨 설명인지 이해하기가 힘들다.  
그래서 직접 코드를 작성해보지 않으면 이해할 수 없다.
***
![옵서버 패턴](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Observer.svg/854px-Observer.svg.png)  
옵저버 패턴을 검색하면 가장 흔히 나오는 이미지이다.  
사실 나는 이 이미지를 봐도 이해가 가지 않았다.  
그래서 공부를 해보았다.
***
## 옵저버의 탐색을 넥서스에서 받아보기
- Intreface인 [`Observer`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/ObserverPattern/src/Observer.kt)와 [`Observable`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/ObserverPattern/src/Observable.kt)을 만들어준다.
- 옵저버 패턴이기때문에 [`UnitObserver`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/ObserverPattern/src/UnitObserver.kt)을 만들어준다.,
- 옵저버를 조종하고 관리할 [`Nexus`](https://github.com/JeongJinyong/DPM-Design-Pattern/blob/master/ObserverPattern/src/Nexus.kt)까지 만들어준다.
- 이로서 준비는 끝났다. 나는 넥서스에서 옵저버에게 명령을내리고 옵저버가 정찰한 내용을 넥서스에서 받도록 만들었다.
- 이제 블리자드로 취직하러간다.
```Kotlin
interface Observer {
    fun reconnaissance(x: Float, y: Float)
}
```
> 옵저버를 구현한다 옵저버는 정찰을하며 지형과 상대방을 인식한다
```Kotlin
interface Observable {
    fun productionObserver(observer: Observer)
    fun destructionObserver(observer: Observer)
    fun move(x: Float, y: Float)
}
```
> 옵저버블을 구현하여 옵저버를 생산또는 파괴에대해 관리를하며 움직이는 명령을 내리도록한다.
```Kotlin
class UnitObserver : Observer {
    override fun reconnaissance(x: Float, y: Float) {
        println("$x , $y")
    }
}
```
> 옵저버 유닛은 넥서스에서 명령을 받아 출력을하고
```Kotlin
class Nexus : Observable {

    private val observerList = mutableListOf<Observer>()

    override fun productionObserver(observer: Observer) {
        observerList.add(observer)
    }

    override fun destructionObserver(observer: Observer) {
        observerList.remove(observer)
    }

    override fun move(x: Float, y: Float) {
        observerList.forEach {
            it.reconnaissance(x, y)
        }
    }
}
```
> 넥서스는 옵저버에게 명랑을 내리도록한다.
물론 이상한점은 있을수있다. 하지만 그건 넘어가도록 하자
***
![옵저버](https://coinpan.com/files/attach/images/198/800/207/090/b29ba5dbc4818d27269f02610c36604c.jpg)
- 스타크래프트 옵저버는 본인이 이동한곳의 지형을 모두 파악하여 사용자에게 보여준다
- 지형파악은 실시간으로 이루어지며 옵저버의 변화가 있을때 바로바로 업데이트를 해준다.
- 위 사항을 미루어 보았을때 옵저버 패턴은 옵저버를 만들어서 변화가 이루어진걸 바로 업데이트해주는거라고 이해할수있다.
