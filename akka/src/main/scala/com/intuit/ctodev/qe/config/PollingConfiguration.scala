package com.intuit.ctodev.qe.config

/**
  * Created by hyan on 3/22/17.
  */
object PollingConfiguration {
  trait RetryStrategy

  case class StaticPeriodRetryStrategy() extends RetryStrategy

  case class WeightRetryStrategy(weight: Double) extends RetryStrategy
}

class PollingConfiguration(val strategy: PollingConfiguration.RetryStrategy = PollingConfiguration.StaticPeriodRetryStrategy(),
                          val timeout: Option[Int] = None) {
}
