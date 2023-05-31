public class Elo {
    
    public static double expectedScore (int R1, int R2) {
        return 1.0 /( 1.0 + Math.pow(10.0, ((double) (R2 - R1) ) / 400));
    }

    public static int newRating (int rating,
                                 double expectedScore,
                                 double actualScore)
    {
        return (int) Math.round(rating + KFactor(rating) *
                    (actualScore - expectedScore));
    }


    private static double KFactor (int rating) {
        if(rating > 2000) {
            return 20;
        }
        else {
            return 32;
        }
    }
}
