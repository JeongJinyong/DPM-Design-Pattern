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