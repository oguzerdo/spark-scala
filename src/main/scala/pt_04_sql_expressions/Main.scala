package com.oguzerdogan.sparkscala
package pt_04_sql_expressions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{current_timestamp, expr}
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

    df.show()
    df.printSchema()

    // Not recommended, Spark interpret it at the runtime
    // in Expression part if we do typo compiler doesn't complain so we don't get our error on IDE.
    val timestampFromExpression = expr("cast(current_timestamp() as string) as timestampExpression")

    // Recommended
    val timestampFromFunctions = current_timestamp().cast(StringType).as("timestamFunctions")


    df.select(timestampFromExpression, timestampFromFunctions).show()

    // Another expression, we can use on dataframe
    df.selectExpr("cast(Date as string)", "Open + 1.0", "current_timestamp()").show()

    // We can also run SQL Queries
    // thi̇s reference to our dataframe in scala doesn't work, therefore we need to register our dataframe as tempView
    df.createTempView("df")
    spark.sql("SELECT * FROM df").show() // Scala API is much more powerful, prefer that

  }
}