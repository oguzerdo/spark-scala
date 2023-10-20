package com.oguzerdogan.sparkscala
package read_data

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("spark-scala")
      .master("local[*]")
      .getOrCreate

    // Read Data
    val df = spark.read
      .option("header", value=true)
      .csv("data/AAPL.csv")

    df.show()
  }
}