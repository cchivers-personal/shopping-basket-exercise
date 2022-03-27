package shoppingcart.domain

import enumeratum.EnumEntry.CapitalWords
import enumeratum._

sealed trait Item extends EnumEntry with CapitalWords {
  def price: Price
}
object Item       extends Enum[Item]                  {

  case object Apple  extends Item {
    override def price: Price = Price(60)
  }
  case object Orange extends Item {
    override def price: Price = Price(25)
  }

  override def values: IndexedSeq[Item] = findValues
}
