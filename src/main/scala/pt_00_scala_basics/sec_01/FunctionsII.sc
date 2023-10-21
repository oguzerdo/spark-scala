// Functions II

def anonim = (x: Int) => {
  x + 2
}

// anonim bir fonksiyon tipi
anonim

// Integer alan ve Integer dönen bir function tanımladık
anonim(2)
anonim.apply(2)


val anotherAnonim = () => "Selaminko"

anotherAnonim
anotherAnonim()
anotherAnonim.apply()

// Bir fonksiyonu anonim fonksiyona çevirme

def add3to(m: Int, n: Int) : Int = {
  m + n
}

val add3Func = add3to _

add3Func

add3Func(2,5)
add3Func.apply(2, 5)

// Dönen çıktıyı tutma

val result = add3Func.apply(2, 5)
print(result)


// Anonim fonksiyonlarda default değer veremiyoruz, default değerleri bir fonk tipine çeviremez.
//def anonim2 = (x: Int, y: Int = 4) => {
//  x + 2


// Fonksiyonları parçalı bir şekde çalıştırmma

def multiplyBy(m: Int) = {
  m * 2
}


def multiply(m: Int, n: Int)= {
  m * n
}
// firstResult artık parametre alan ve sonucunda integer dönen bir fonksyion haline geldi
val firstResult = multiply(2, _: Int)
//val firstResult: Int => Int = <function>
// Burada şunu yaptık: multiply fonksiyonunu al, 1. parametresini 2 yap.
// Geri kalanını sonra vereceğimiz bir fonksiyon haline çevir.

firstResult(5)
firstResult.apply(5)

/*
  'total' isminde bir anonim fonksiyon tanımı yapın ve bu fonksiyon
  String ve Int olacak şekilde iki parametre alsın
  işlem olarak string değerinin uzunluğu ve ikinci parametre toplansın.

  Oluşturduğunuz total fonksiyonu önce string parametresi alan bir fonksiyon
  tarafından çagırılıp bir değere atanarak farklı bir değerde uygulanmasını
  sağlayınız.

*/

def total(str: String, n: Int) = {
  str.length + n
}

def strIncome(str: String) : (Int => Int) = {
  total(str, _: Int)
}

val result = strIncome("dort").apply(2)
