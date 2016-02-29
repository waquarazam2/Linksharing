package linksharing
enum Seriousness {
    SERIOUS,VERY_SERIOUS,CASUAL

    static Seriousness convertToEnum(String str) {

        if (str.equalsIgnoreCase("serious")) {
            return Seriousness.SERIOUS
        } else if(str.equalsIgnoreCase('very_serious')){
            return Seriousness.VERY_SERIOUS
        }else{
            return Seriousness.CASUAL
        }

    }
}