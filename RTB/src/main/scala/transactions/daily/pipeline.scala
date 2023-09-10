package transactions.daily

import org.apache.spark.sql.{DataFrame, SparkSession}

object pipeline extends transactions.blueprint.pipeline {

  implicit val spark = SparkSession
    .builder()
    .appName("Transaction Processing Application")
    .config("spark.master", "local[*]")
    .getOrCreate()

  def source(implicit spark: SparkSession): DataFrame =
    transactions.daily.flow
      .source("csv", "source", "2023--01-01", "1 hour", "transactionTime")
      .source

  def processing(dataFrame: DataFrame)(
    implicit spark: SparkSession
  ): DataFrame = transactions.daily.flow.processing(dataFrame).ProcessingLogic

  def sink(dataFrame: DataFrame)(implicit spark: SparkSession): Unit =
    transactions.daily.flow
      .sink("sink", "checkpoint", "append", "csv", "1 minutes")
      .Sink(dataFrame)

  def main(args: Array[String]) = {

    sink(processing(source))
  }

}
