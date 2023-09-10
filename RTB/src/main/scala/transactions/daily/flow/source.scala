package transactions.daily.flow

case class source(streamFormat: String,
                  folderName: String,
                  startingTimeStamp: String,
                  waterMarkTime: String,
                  waterMarkColumn: String)
    extends transactions.blueprint.flow.source
