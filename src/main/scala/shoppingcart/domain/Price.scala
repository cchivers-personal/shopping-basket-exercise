package shoppingcart.domain

case class Price(value: Int) {

  def +(that: Price) = Price(this.value + that.value)

  def format: String =
    s"£${value / 100}.${"%02d".format(value % 100)}"
}
