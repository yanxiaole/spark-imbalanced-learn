import imblearn.undersampling.RandomUnderSampler
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by lucas on 8/7/16.
  */
object TestRandomUnderSampler {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().
      setAppName("Random Under Sampling Tester").
      setMaster("local[*]")
    val spark = new SparkSession(conf)

    val ds = spark.createDataset(
      (1 to 10000) map {_ => LabeledPoint(0, Vectors.dense(Array[Double]()))}
    ).union (spark.createDataset(
      (1 to 100) map {_ => LabeledPoint(1, Vectors.dense(Array[Double]()))}
    )).cache

    //ds.describe("label").show
    println(f"negative number: ${ds.filter(p => p.label == 0).count}")
    println(f"positive number: ${ds.filter(p => p.label == 1).count}")

    val rus = RandomUnderSampler(ds)
    val ds2 = rus.sample(ds).cache

    println("after sampling:")
    println(f"negative number: ${ds2.filter(p => p.label == 0).count}")
    println(f"positive number: ${ds2.filter(p => p.label == 1).count}")

    rus.sample(ds).describe("label").show

  }
}
