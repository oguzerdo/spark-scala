package com.oguzerdogan.sparkscala
package pt_07_window_functions

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, row_number, year}


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

    val window = Window.partitionBy(year($"date").as("year")).orderBy($"close".desc)
    stockData
      .withColumn("rank", row_number().over(window))
      .filter($"rank" === 1)
      .sort($"close".desc)
      .show()


  }
}