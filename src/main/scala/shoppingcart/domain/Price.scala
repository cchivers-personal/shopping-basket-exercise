package shoppingcart.domain

case class Price(value: Int) {
  def format: String =
    s"£${value / 100}.${"%02d".format(value % 100)}"
}
