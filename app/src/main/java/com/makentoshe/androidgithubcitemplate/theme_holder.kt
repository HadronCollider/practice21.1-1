enum class ThemeHolder {
    INSTANCE;
    var realTheme = 'w'
        private set
    var chosenTheme = 'w'
        private set
    var mainNeedsToBeChanged = true
    fun changeTheme(newTheme: Char) {
        chosenTheme = newTheme
    }
    fun changeRealTheme(newTheme: Char) {
        realTheme = newTheme
    }
}