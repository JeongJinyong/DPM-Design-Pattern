class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nexus = Nexus()
            val unitObserver = UnitObserver()
            nexus.productionObserver(unitObserver)
            nexus.move(3f,2f)
        }
    }
}