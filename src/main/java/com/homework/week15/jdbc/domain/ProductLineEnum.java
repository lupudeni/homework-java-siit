package com.homework.week15.jdbc.domain;

public enum ProductLineEnum {

    CLASSIC_CARS("Classic Cars",
            "Attention car enthusiasts: Make your wildest car ownership dreams come true. " +
                    "Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, " +
                    "you will find great choices in this category. These replicas feature superb attention to detail " +
                    "and craftsmanship and offer features such as working steering system, opening forward compartment," +
                    " opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. " +
                    "The models range in size from 1:10 to 1:24 scale and include numerous limited edition " +
                    "and several out-of-production vehicles. All models include a certificate of authenticity " +
                    "from their manufacturers and come fully assembled and ready for display in the home or office."),
   MOTORCYCLES("Motorcycles", "Our motorcycles are state of the art replicas of classic " +
           "as well as contemporary motorcycle legends such as Harley Davidson, Ducati and Vespa. " +
           "Models contain stunning details such as official logos, rotating wheels, working kickstand, " +
           "front suspension, gear-shift lever, footbrake lever, and drive chain. Materials used include diecast " +
           "and plastic. The models range in size from 1:10 to 1:50 scale and include numerous limited edition " +
           "and several out-of-production vehicles. All models come fully assembled and ready for display " +
           "in the home or office. Most include a certificate of authenticity."),
   PLANES("Planes", "Unique, diecast airplane and helicopter replicas suitable for collections, " +
           "as well as home, office or classroom decorations. Models contain stunning details such as " +
           "official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. " +
           "Most come fully assembled and with a certificate of authenticity from their manufacturers."),
          SHIPS("Ships", "The perfect holiday or anniversary gift for executives, clients, " +
                  "friends, and family. These handcrafted model ships are unique, stunning works of art " +
                  "that will be treasured for generations! They come fully assembled and ready for display " +
                  "in the home or office. We guarantee the highest quality, and best value."),
   TRAINS("Trains", "Model trains are a rewarding hobby for enthusiasts of all ages. " +
           "Whether you're looking for collectible wooden trains, electric streetcars or locomotives, " +
           "you'll find a number of great choices for any budget within this category. " +
           "The interactive aspect of trains makes toy trains perfect for young children. " +
           "The wooden train sets are ideal for children under the age of 5." );
//   TRUCKS_AND_BUSES() Trucks and Buses
//   VINTAGE_CARS() Vintage Cars


    private String productLine;

    private String textDescription;

    private String htmlDescription;

    private byte[] image; //mediumblob

    ProductLineEnum(String productLine, String textDescription) {
        this.productLine = productLine;
        this.textDescription = textDescription;
    }


//
//    @ToString.Exclude
//    private List<Product> productList;


}
