class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val iphone = Iphone()
            val adapter = AppleAdapter(SamsungEarPhone())
            iphone.setEarPhone(adapter)
        }
    }
}