package src.ScalaCore

object TimeConversion {

  def main(args: Array[String]): Unit = {

  val time = "12:40:22AM"
    println(timeConvert(time))
    val timePM = "12:40:22PM"
    println(timeConvert(timePM))
    val timeabove = "07:05:45PM"
    println(timeConvert(timeabove))
    val timebelow = "07:05:45AM"
    println(timeConvert(timebelow))
  }

  def timeConvert(time:String) : String = {
    val splitTime = time.split(":")
    var converted = 12
    var convertedTime :String =""
    val zoneSplit = splitTime(2).split("P|A")

    if((1 < splitTime(0).toInt && splitTime(0).toInt <= 24 )&&
      (0<=splitTime(1).toInt && splitTime(1).toInt <= 60) &&
      (0<= zoneSplit(0).toInt && zoneSplit(0).toInt <= 60)){
     convertedTime = time.contains("P") match {
      case true => {
        if(splitTime(0).toInt!=12){
        converted = splitTime(0).toInt + 12
        }
        converted.toString +":"+ splitTime(1)+":"+zoneSplit(0)
      }
      case false =>{

        if(splitTime(0).equals("12")){
         splitTime(0) =  splitTime(0).replace("12","00")

        }
        splitTime(0) +":"+ splitTime(1)+":"+zoneSplit(0)
      }
    }
    }
   convertedTime
  }
}
