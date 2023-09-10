package object transactions {

  case class Transaction(customerId: BigInt,
                         originAccountNumber: BigInt,
                         originBranch: String,
                         originCity: String,
                         originCountry: String,
                         destinationAccountNumber: BigInt,
                         destinationBranch: String,
                         destinationCity: String,
                         destinationCountry: String,
                         totalAmount: BigInt,
                         transactionTime: java.sql.Timestamp)

  case class Window(start: java.sql.Timestamp,
                    end: java.sql.Timestamp)

  case class Group(customerId: BigInt, window: Window)

  case class AntiMoneyLaunderingStats(customerId: BigInt,
                                  windowStartTime: java.sql.Timestamp,
                                  windowEndTime:java.sql.Timestamp,
                                  totalTransaction: Int,
                                  totalTransactionAmount: BigInt,
                                  totalTransactionToHRJ: Int,
                                  totalTransactionTOHRJMap: Map[String, Int],
                                  totalHighValueTransaction: Int)

  val highRiskCountries = List("iraq","pakistan")

}
