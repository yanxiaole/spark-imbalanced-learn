package imblearn.ensemble

import imblearn.Sampler
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.sql.Dataset


class EasyEnsemble extends Sampler {
  override def sample(ds: Dataset[LabeledPoint],
                      seed: Option[Long]): Dataset[LabeledPoint] = ???
}

object EasyEnsemble {

}
