package transactions.daily.flow

import transactions._

import org.apache.spark.sql.{DataFrame, SparkSession}

import org.apache.spark.sql.functions.{window, col}

case class processing(dataFrame: DataFrame)
    extends transactions.blueprint.flow.processing {

  override def ProcessingLogic(implicit spark: SparkSession): DataFrame = {

    import spark.implicits._

    val transactions = this.dataFrame.as[Transaction] .withColumn("window",window($"transactionTime", "30 minutes"))

    val groupedTransactions = transactions
      .groupBy($"customerId", $"window")
      .as[Group, Transaction]

    groupedTransactions
      .mapGroups {

        case (key, value) => {

          val lst = value.toList

          val highRiskData =
            lst.filter(x => highRiskCountries.contains(x.originCountry))

          AntiMoneyLaunderingStats(
            key.customerId,
            key.window.start,
            key.window.end,
            lst.length,
            lst.map(_.totalAmount).sum,
            highRiskData.length,
            highRiskData
              .groupBy(_.originCountry)
              .map { case (hrj, data) => (hrj, data.length) }
              .toMap,
            lst.filter(_.totalAmount > 1000000).length
          )

        }
      }
      .toDF()

  }
}
