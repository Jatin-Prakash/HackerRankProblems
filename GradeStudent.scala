package src.ScalaCore

object GradeStudent {

  def main(args: Array[String]): Unit = {
   val grades =  Array(73,67,38,33)
    gradingStudents(grades).foreach(println)
  }


  def gradingStudents(grades : Array[Int]) : Array[Int] = {

     var roundedGradeList = collection.mutable.Buffer[Int]()

     grades.foreach(grade => {

        var rounded = grade>=38 match {
         case  true => {
           val module = grade+(5- grade%5)
           if (module-grade<3)
            module
           else
             grade
         }
         case false => {
           grade
         }
       }
       roundedGradeList+=rounded
     })
    roundedGradeList.toArray
  }
}
