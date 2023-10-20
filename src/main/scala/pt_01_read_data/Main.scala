package com.oguzerdogan.sparkscala
package pt_01_read_data

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

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
    df.printSchema()

  }
}