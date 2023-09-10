package transactions.blueprint.flow

import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{DataFrame, SparkSession}

trait sink {

  def path:String

  def checkpointName:String

  def outputMode:String

  def outputFormat:String

  def triggerTime:String

  def Sink(dataFrame: DataFrame)(implicit spark: SparkSession): Unit = {

    dataFrame
      .writeStream
      .trigger(Trigger.ProcessingTime(this.triggerTime))
      .outputMode(this.outputMode)
      .format(this.outputFormat)
      .option("path", this.path)
      .option("checkpointLocation", this.checkpointName)
      .start()
      .awaitTermination()
  }
}
