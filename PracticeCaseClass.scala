package src.ScalaCore

import scala.collection
import scala.collection.immutable
import scala.collection.immutable.Vector


object PracticeCaseClass {
  def main(args: Array[String]): Unit = {

    // the method will take string and will match with different value

    practiceMatch("enroll")
    practiceMatch("notDesigned")
    practiceMatch("this is conditional")
    practiceMatch("kuch bhi bhejo")
    println(reversedStringWhenNotNull("Perfect",checkForNotNull))
  }

  def practiceMatch(stringToMatch : String) : Unit = {

    val methodToCall = stringToMatch match {
      case  s2match if s2match.equals("this is conditional") => s2match
      case "enroll" => "call enroll method"
      case other => createsVector(stringToMatch)
      case _ => "thi is use of wilcard"

    }
    if(methodToCall.isInstanceOf[IndexedSeq[Int]])
    for(i <-  methodToCall.asInstanceOf[IndexedSeq[Int]]){
      println(i)
    }else{
      println(methodToCall)
    }

  }

  def createsVector(knowTheLength : String):IndexedSeq[Int] = {

    for(i <- 0 to knowTheLength.length) yield i

  }

  def reversedStringWhenNotNull(word:String, checkForNotNull : String =>Boolean) :String = {

    if(checkForNotNull(word)){
      word.reverse
    }else{
      word
    }
  }

  def checkForNotNull(word : String) :Boolean ={
    if(word !=null)
      true
    else{
      false
    }
  }
}
