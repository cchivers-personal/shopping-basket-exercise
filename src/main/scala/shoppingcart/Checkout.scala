package shoppingcart

import shoppingcart.domain.{Item, Price}

trait Checkout {
  def calculateTotal(items: List[Item]): Price
}

object Checkout {
  def apply(): Checkout = new Checkout {
    override def calculateTotal(items: List[Item]): Price =
      items.foldLeft(Price(0))((acc, item) => Price(acc.value + item.price.value))
  }
}
