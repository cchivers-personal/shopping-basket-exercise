package shoppingcart.domain

sealed trait Error {
  def errorOutput: String
}

case class InvalidItems(invalidItems: List[String]) extends Error {
  override def errorOutput: String = s"Error: Invalid items as input arguments [${invalidItems.mkString(", ")}]"
}
