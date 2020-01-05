package src.ScalaCore

object PracticeScala {

  def main(args: Array[String]): Unit = {

    val stringList = List("tiger", "sheep", "buffalo","deer")
    practiceForloop(stringList)
    val intList = List(1,2,56,2,7,6,4)
    print(sort(intList))
    val arr = Array(-4, 3, -9, 0, 4, 1 )
    plusminus(arr)
    val exp = new AllAnimal;
    exp.getstart
  }


  def plusminus(arr : Array[Int]) : Unit = {
    var pos = 0
    var neg = 0
    var zero = 0
    val length = arr.length
    for(i <- arr){
     if(i <0){
       neg+=1
     }else if(i>0){
       pos+=1
     }else{
       zero +=1
     }
    }

    println( BigDecimal(pos.toFloat/length).setScale(6,BigDecimal.RoundingMode.HALF_UP))
    println(BigDecimal(neg.toFloat/length).setScale(6,BigDecimal.RoundingMode.HALF_UP))
    println(BigDecimal(zero.toFloat/length).setScale(6,BigDecimal.RoundingMode.HALF_UP))
  }

  def practiceForloop(lis : List[String]) : Unit = {

    val passed = for{

      values <- lis
      if(values == "tiger" || values == "camel")

    }yield "eaten"

    for( values <- lis if(values !="goat")){
      val saved = values
    }

    println(lis(2))
    println(passed)
  }

  def sort (numbers : List[Int]) : List[Int] ={

    numbers.sorted
  }
}


class AllAnimal extends Animal with tail with respiratory{
  def getstart  {

    stop
  }

   override def stop{
    println("this is overriden")
  }
  def speak{
    println("this is defined in AllAnimal")
  }
}



abstract class Animal {
  def speak
}

trait tail {
  def movetail{
    println("movingtail")
  }
  def stop: Unit = {
    println("stop moving tail")
  }
}

trait respiratory{
  def breathing{
    println("it alive")
  }

  def stop{
    println("its dead")
  }
}

