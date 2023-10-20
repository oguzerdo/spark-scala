package com.oguzerdogan.sparkscala
package pt_03_column_functions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StringType

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

//    df.show()
//    df.printSchema()


    val column = df("Open")
    val newColumn = column + 2.0
    val columnString = column.cast(StringType)

    df.select(column, newColumn, columnString)
      .filter(newColumn > 2.0)
      .filter(newColumn > column)
      .filter(newColumn === column) // check equality
      .show()

  }
}