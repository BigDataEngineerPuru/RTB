package transactions.blueprint.flow

import org.apache.spark.sql.{DataFrame, SparkSession}

trait processing {

  val dataFrame: DataFrame

  def ProcessingLogic(implicit spark: SparkSession) :DataFrame

}
