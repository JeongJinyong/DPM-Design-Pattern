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