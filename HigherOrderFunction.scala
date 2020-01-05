package src.ScalaCore

object HigherOrderFunction {

  def main(args: Array[String]): Unit = {

    val hof = new HoFuntion
    val tuMax = (2,5,34)
    val tu = (2,12)
    println(hof.maximum(tuMax,hof.max))
   println( hof.conditional[(Int,Int)](tu,hof.p,hof.f))
    hof.fzero[(Int)](9)(10,hof.jsutPrint)
    hof.testList()
    println(hof.reduceApp(List(2,4,5,3),0.0)(_+_))
    println(hof.reduceApp(List(3,6,1,9),false){(i,a) => if(a)a else (i==9)})
    println(hof.reduceApp(List(List(2,3,9,7,1)),false){(l,sumgreater) =>
      var sum = 0

        for(i <- l)
          sum +=i
      if(sum > 40)
        true
      else
      sumgreater
    })

    println(hof.divideWithOption(3,7))
    println(hof.divideWithOption(3,0))
  }
}

class HoFuntion{


  val max = (x:Int, y:Int) => if(x>y) x else y

  def maximum(tu : Tuple3[Int,Int,Int], f : (Int,Int) => Int) : Int ={
    f(tu._1,f(tu._2,tu._3))
  }

  /**
    * approcah : the function that need to be applied reptedly if f :(A,B)=>B.
    * this function will be applied for every element present in the list
    * @param l
    * @param start
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def reduceApp[A,B](l:List[A], start : B)(f : (A,B)=>B) : B = {
    var a = start
    for( i <- l)
      a = f(i,a)
    a
  }


  /**
    *  This function demonstrate teh use type parameter. This give the flexibility
    *  to define the type while invoking the function. This reduces the need of overloading
    * @param x
    * @param p
    * @param f
    * @tparam A
    * @return
    */
  def conditional[A](x : A, p : (A) => Boolean, f : (A) => A) : A = {

    if(p(x)){
     f(x)
    }else x
  }

  def p[A](value : A) : Boolean = {

    true
  }

  def fzero[A](x: A)(y :A,f: A => Unit): A = {
    f(y)
    x }

  def jsutPrint[A](param : A) : Unit = {
    println("this is "+param)
  }
  def f[A](value: A):A ={

    value
  }

def divideWithOption(divisor : Int,dividend : Int) : Option[Double] = {
  if(dividend ==0) None
  else Option(divisor / dividend)
}

  def testList() : Unit = {
    val tstList = List(2,4,8,4,6,1,9)
    tstList.distinct.drop(2).foreach(println)
  }
}



