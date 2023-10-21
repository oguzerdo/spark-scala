print("hello world!")

2 + 2

"Hello mate"

// Bir değere atamadığımız halde arka planda bir değişkene atılıyor
res2.charAt(5)


// Fakat buna bir değer atayamıyoruz
// res2 = "Come on lets change"

//@format:off
/**********************************************************************
 *                             [Any]                                 *
 *********************************************************************
 **   [AnyVal]                   *      [AnyRef (java.lang.Object)] **
 *********************************************************************
 ***  [Double]                   *      [List]                     ***
 ***  [Float]                    *      [Option]                   ***
 ***  [Long]                     *      [YourClass]                ***
 ***  [Int]                      *                                 ***
 ***  [Short]                    *                                 ***
 ***  [Byte]                     *                                 ***
 ***  [Unit] -> It is different  *                                 ***
 ***  [Boolean]                  *                                 ***
 ***  [Char]                     *                                 ***
 *********************************************************************
 *                                       [Null]                      *
 *********************************************************************
 *                           [Nothing]                               *
 *********************************************************************/
//@format:on

// Javada her tip bir objedir.
// Scala'da ise her şey Any'e bağlı.
// AnyVal Scala'nın tipleri, AnyRef ise Java'dan referans alınan tipler.


val a : Int = 5
var b : Int = 6

// a değişkeni immutable bir objedir.
// a değişkenine tekrardan atama yapamayız, b değerine ise tekrar atama yapabiliriz.


val c : List[Any] = List(1, "Youtube", true, () => "Hi there!")
val d = c(2)

// Unit, Java'da herhangi bir objeye tekabül etmeyen void'e denk geliyor.
// Fonksiyonunuzun dönüş tipini Unit olarak belirleyebiliyorsunuz.
// Java'da bir şey Null olabilir, Scala da ise Nothing