package imblearn

import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.sql.Dataset

/**
  * Created by lucas on 8/9/16.
  */
trait Sampler {
  def sample(ds: Dataset[LabeledPoint], seed: Option[Long] = None): Dataset[LabeledPoint]
}
