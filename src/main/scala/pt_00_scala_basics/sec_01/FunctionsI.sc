// Functions

// Scala'da bir functionı def ifadesi ile tanımlarız.
//def functionName() = {}

// Function tanımı
def justPrint() = {
  print("Selaminko")
}

// Function kullanımı
justPrint()


// Değişken tanımlarında olduğu gibi dönüş değerini ekrana yazmakta fayda var.
// Java'daki void'e denk gelen obje Scala'da Unit, herhangi bir değer içermez.
def justPrint2(): Unit = {
  print("Selaminko")
}

// Functionlarda parametre kullanımı

def parametricFunction(age: Int) : Int = {
  age + 1
}

parametricFunction(36)

// Functionlardan dönen değerlere işlem yapabiliriz
parametricFunction(32).toString

// Default parameter kullanımı
def add(m: Int = 0, n: Int ) = {
  m + n
}

add(n=3)
add(5, 6)
add(n=3, m=3)