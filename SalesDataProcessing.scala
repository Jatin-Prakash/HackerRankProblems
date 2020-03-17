package src.ScalaCore

import java.text.SimpleDateFormat

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import java.util.Date
//Made the case class, to suite the csv data
case class SalesDataRecord(region : String,
                           country : String,
                           item_type : String,
                           sales_channel : String,
                           order_priority : String,
                           orderDate : Date,
                           orderId : Float,
                           shipDate : Date,
                           unitSold : Int,
                           unitPrice : Float,
                           unitCost : Float,
                           totalRevenue : Float,
                           totalCost : Float,
                           toalProfit : Double)

object SalesDataProcessing {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("SalesDataProcessing")
    val context = new SparkContext(conf)
    val session = SparkSession.builder().config(conf).getOrCreate()
    /*val salesData = session.read.option("header",true).csv("/home/ubuntu/IdeaProjects/SparkDataframes/src/data/100 Sales Records.csv")
    salesData.show()
    */
    //inducing the data, in the case class
    val salesRecord = context.textFile("/home/ubuntu/IdeaProjects/SparkDataframes/src/data/textVersionSalesRecord.csv").
      map(stringArgs => {
        val salesValues = stringArgs.split(",")
        new SalesDataRecord(salesValues(0),salesValues(1),salesValues(2),salesValues(3),
          salesValues(4),new SimpleDateFormat("m/dd/yyyy").parse(salesValues(5)),salesValues(6).toFloat,
            new SimpleDateFormat("m/dd/yyyy").parse(salesValues(7)),salesValues(8).toInt,salesValues(9).toFloat,salesValues(10).toFloat,salesValues(11).toFloat,
          salesValues(12).toFloat,salesValues(13).toDouble) })
//making a map according to region and country
  val mapKeysCountry = salesRecord.map(salesDataRecord => (salesDataRecord.country,salesDataRecord.toalProfit))
    val mapKeysRegion = salesRecord.map(salesDataRecord => (salesDataRecord.region,salesDataRecord.toalProfit))
//fetching the size, data
    println(mapKeysRegion.collect().size)
    mapKeysRegion.collect().foreach(println)
    //grouping by region
    println("grouping by region")
    val groupedByRegion = mapKeysRegion.groupByKey()
    groupedByRegion.collect().foreach(println)
    println("the size got reduced to ->"+groupedByRegion.collect().size)

    //reduce by key
    val reducedByKey = mapKeysRegion.reduceByKey(_+_)
    println("reduce by key")
    reducedByKey.collect().foreach(println)

    //CombineByKey

    type profitCollector = (Int,Double)
    type regionsProfit = (String, (Int,Double))

    val createCombiner = (profit : Double)=>(1,profit)
    val combineProfit = (collectProfit : profitCollector, profit : Double) => {
      val (regions, totalProfit) = collectProfit
      (regions + 1, totalProfit + profit)
    }
    val mergeProfit = (collector1 : profitCollector,collector2 : profitCollector) =>{
      val (regions1, totalProfit1) = collector1
      val (regions2, totalProfit2) = collector2
      println("these are the values "+regions1+ " : "+regions2 +" : "+totalProfit1)
      (regions1+regions2,totalProfit1+totalProfit2)
    }

    val runAverage = (regions : regionsProfit) =>{
      val(name,(regionstotal,totalProfit)) = regions
      (name , totalProfit/regionstotal)
    }

    val combined = mapKeysRegion.combineByKey(createCombiner,combineProfit,mergeProfit)
    combined.collect().foreach(println)
    val avg = combined.collectAsMap().map(runAverage)
    avg.foreach(ps =>{
      val(name,avg) = ps
      println("name of the region "+name +" have avg profit "+avg)
    })
    println("without creating different event")
    val combineAgain = mapKeysRegion.combineByKey(
      (profit) =>(1,profit),
      (combine : (Int,Double), profit) =>(combine._1+1,combine._2+profit),
      (combine1 :  (Int,Double), combine2 :  (Int,Double)) => (combine1._1+combine2._1,combine1._2+combine2._2)
    ).map{case (key,value) => (key, value._2/value._1)}.collectAsMap().foreach(println)

  }

}


















