// Print type I
print("Hello I ")

// Print type II
println("Hello II")

// Scala'yı Javadan ayıran en büyük özellikleredn biri artık return keyi yok.

// En son yazılan satır return value oluyor.
{
  "Hello World"
}

{
  "Hello World"
  2+2
}


// val: immutable değer atamak için kullanılır.
// Scala değerin hangi tipte olduğunu tanıyarak a değişkeninin tipini atıyor.
// Yine de en baştan tip belirmekte fayda var.
val a = 2
val b: Int = 2
// Char tek tırnakla tanımlıyoruz
val c: Char = 'C'

// var: Mutable değer tanımı yapmamızı sağlar.
// var: yani değere tekrardan bir atama yapabiliriz.

var mutable_variable: Int = 2
mutable_variable = a + 2

// NOT: Bu objenin tipini değiştiremeyiz
//mutable_variable = "String"

// String tanımları
val str: String = "ABC"

// Uzun bir string ihtiyacı varsa:
val longStr: String =
  """Satır 1
     Satır 2
     Satır 3
    """.stripMargin

// Scala'da Java'daki gibi ";" kullanmamıza gerek yok.


// String Interpolation

val plan : String = "lets gonna learn scala"
val year : Int = 2023

print(s"$plan in $year")

// Decimal manipulation
val percantage : String = "Percent"
val d : Double = 1.9134058345305305

s"$percantage - $d"
f"$percantage - $d%2.3f"

//val res5: String = Percent and 1.9134058345305305
//val res6: String = Percent and 1,913