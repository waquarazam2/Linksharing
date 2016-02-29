package linksharing

public enum Visibility {
    PUBLIC, PRIVATE

    static Visibility convertToEnum(String str) {

        if (str.equalsIgnoreCase("public")) {
            return Visibility.PUBLIC
        } else {
            return Visibility.PRIVATE
        }

    }
}
