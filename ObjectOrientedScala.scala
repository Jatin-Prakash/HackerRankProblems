package src.ScalaCore

object ObjectOrientedScala {

  def main(args: Array[String]): Unit = {

    val test = new testOOScala("amigo")
     println(test.greet())
    print(test.say)
    val testInherit = new testInheritence("Inherited")

  }

  val listOfClasses = List(new testOOScala("Zak"), new testOOScala("Hank"), new testOOScala("Nick"))
 val sortedList = listOfClasses.sortBy(_.name)
 println(sortedList)
}




class testOOScala(val name : String) {

  def greet() : String = {
    println( s"hello from $name")
    s"We say hello again to $name"
  }

  override def toString: String = s"User($name)"
  val say  = name
}

class testInheritence(nameFromInheritence : String) extends testOOScala(name = "Inherited") {
  override val name: String = nameFromInheritence

}