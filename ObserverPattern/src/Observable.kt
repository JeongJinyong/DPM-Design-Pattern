interface Observable {
    fun productionObserver(observer: Observer)
    fun destructionObserver(observer: Observer)
    fun move(x: Float, y: Float)
}