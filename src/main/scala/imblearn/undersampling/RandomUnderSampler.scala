package imblearn.undersampling


import imblearn.Sampler
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.sql.Dataset

import scala.util.Random


class RandomUnderSampler(fraction: Double, withReplacement: Boolean) extends Sampler {
  override def sample(ds: Dataset[LabeledPoint],
                      seed: Option[Long] = None): Dataset[LabeledPoint] = {
    ds.filter(p => p.label == 0).
      sample(withReplacement, fraction, seed.getOrElse(Random.nextLong)).
      union(ds.filter(p => p.label == 1))
  }
}

object RandomUnderSampler {
  def apply(ds: Dataset[LabeledPoint], withReplacement: Boolean = true): RandomUnderSampler = {
    ds.cache
    val numNeg = ds.filter(p => p.label == 0).count
    val numPos = ds.filter(p => p.label == 1).count

    val fraction = numPos.toDouble / numNeg
    new RandomUnderSampler(fraction, withReplacement)
  }
}
