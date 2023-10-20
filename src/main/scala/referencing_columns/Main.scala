package com.oguzerdogan.sparkscala
package referencing_columns

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

    // Using Select Method
    df.select("Date", "Open", "Close")

    //   Using Object Columns:
    val column = df("Date")

    // Using Col Functions
    // import org.apache.spark.sql.functions
    col("Date")

    //  Using $ Operator
    import spark.implicits._
    $"Date"

    // sample 1
    df.select(col("Date"), $"Open", df("Close")).show()

    // sample 2
    df.select(column, $"Open", df("Close")).show()
  }
}