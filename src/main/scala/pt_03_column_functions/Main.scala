package com.oguzerdogan.sparkscala
package pt_03_column_functions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.functions.concat
import org.apache.spark.sql.types.StringType

object Main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("spark-scala")
      .master("local[*]")
      .getOrCreate

//    Read Data
    val df = spark.read
      .option("header", value=true)
      .csv("data/AAPL.csv")

    df.show()
    df.printSchema()

//    Functions I (cast, filter)

    val column = df("Open")
    val newColumn = (column + 2.0).as("OpenIncreasedBy2") // Alias column, we can still use variable name
    val columnString = column.cast(StringType).as("OpenAsString") // Alias column, we can still use variable name

    df.select(column, newColumn, columnString)
      .filter(newColumn > 2.0)
      .filter(newColumn > column)
      .filter(newColumn === column) // check equality
      .show()


//    Functions II (lit, concat)
    val litColumn = lit(2.0)
    val newColumnString = concat(columnString, lit("HelloWorld"))


    df.select(column, litColumn, newColumnString).show(truncate=false)


  }
}