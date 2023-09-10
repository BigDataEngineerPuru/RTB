package transactions.blueprint.flow

import transactions._

import org.apache.spark.sql.{DataFrame, SparkSession,Encoders}

trait source {

  def streamFormat: String

  def folderName: String

  def startingTimeStamp: String

  def waterMarkColumn: String

  def waterMarkTime: String

  def source(implicit spark: SparkSession): DataFrame = {

    spark.readStream
      .format(this.streamFormat)
      .option("header", value = true)
      .option("path", this.folderName)
      .option("inferSchema",true)
      .option("startingTimestamp", this.startingTimeStamp)
      .schema(Encoders.product[Transaction].schema)
      .load()
      .withWatermark(this.waterMarkColumn, this.waterMarkTime)
  }

}
