package com.oguzerdogan.sparkscala
package pt_06_groupby_sort_aggregations

import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, year}


object Main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("spark-scala")
      .master("local[*]")
      .getOrCreate

    // Read Data
    val df = spark.read
      .option("header", value=true)
      .option("inferSchema", value=true)
      .csv("data/AAPL.csv")

    df.show()
    df.printSchema()

    val renameColumns = List(
      col("Date").as("date"),
      col("Open").as("open"),
      col("High").as("high"),
      col("Low").as("low"),
      col("Close").as("close"),
      col("Adj Close").as("adjClose"),
      col("Volume").as("volume")
    )

    val stockData = df.select(renameColumns: _*)

    import spark.implicits._

    stockData
      .groupBy(year($"date").as("year"))
      .agg(
        functions.max($"close").as("maxClosed"),
        functions.avg($"close").as("avgClose"))
      .sort($"maxClosed".desc)
      .show()

  // Another Way

    stockData
      .groupBy(year($"date").as("year"))
      .max("close", "high")
      .show()

  }
}