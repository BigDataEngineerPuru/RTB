package transactions.daily
import transactions._
import org.apache.spark.sql.SparkSession
import org.scalatest.funsuite.AnyFunSuite

class pipeline extends AnyFunSuite {


  implicit val spark = SparkSession
    .builder()
    .appName("Transaction Processing Application")
    .config("spark.master", "local[*]")
    .getOrCreate()

  import spark.implicits._

  val transactionDataFrame = Seq(
    // first data frame
    Transaction(1,1,"A","B","iraq",2,"A","D","india",1232141421,new java.sql.Timestamp(123,9,10,1,11,2,1)),
    Transaction(1,1,"A","B","india",2,"A","D","india",1232141421,new java.sql.Timestamp(123,9,10,1,12,2,1)),
    Transaction(1,1,"A","B","india",2,"A","D","india",1232141421,new java.sql.Timestamp(123,9,10,1,13,2,1)),
    Transaction(5,1,"A","B","india",2,"A","D","india",100,new java.sql.Timestamp(123,9,10,1,13,2,1)),
  ).toDF()

  test("following code processes business logic") {
    val output  = transactions.daily.flow.processing(transactionDataFrame).ProcessingLogic.as[AntiMoneyLaunderingStats].rdd

    assert(output.collect().length == 2)

    assert(output.collect().toList.filter(_.customerId == 1)(0).totalTransactionTOHRJMap == Map("iraq" -> 1))

    assert(output.collect().toList.filter(_.customerId == 1)(0).totalTransactionAmount == 3696424263L)

  }

}
