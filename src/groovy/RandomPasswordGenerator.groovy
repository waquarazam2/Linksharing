package linksharing

class RandomPasswordGenerator {
    static generate()
    {
        return new Random().nextInt(100000)
    }
}