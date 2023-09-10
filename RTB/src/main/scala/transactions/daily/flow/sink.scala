package transactions.daily.flow

case class sink(path: String,
                checkpointName: String,
                outputMode: String,
                outputFormat: String,
                triggerTime:String) extends transactions.blueprint.flow.sink
