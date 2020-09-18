class AppleAdapter(private val earPhone: EarPhone):AppleLightningPin{
    override fun pinEight() {
        earPhone.maleFourPole()
    }
}