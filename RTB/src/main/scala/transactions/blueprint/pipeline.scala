package transactions.blueprint

import org.apache.spark.sql.{DataFrame, SparkSession}

trait pipeline {

  implicit val spark :SparkSession

  def source(implicit spark: SparkSession):DataFrame

  def sink(dataFrame: DataFrame)(implicit spark: SparkSession) : Unit

  def processing(dataFrame: DataFrame)(implicit spark: SparkSession) :DataFrame

}
